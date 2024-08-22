package com.match3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

import java.util.Iterator;

public class Field extends Table implements Disposable {
    private int score = 0;
    private static final int RANK = 8;
    private final Array<TextureAtlas.AtlasRegion> entities;
    private final Array<Tile> activeTiles = new Array(64);
    private final Sound click;
    private final Sound swapWrong;
    private final Sound swapSuccess;
    private final ClickListener clickListener;
    private final Action afterMatch;

    public int getScore() {
        return this.score;
    }

    public Field(TextureRegion background, Array<TextureAtlas.AtlasRegion> sprites) {
        this.click = Gdx.audio.newSound(Gdx.files.internal("sound/touch_glass.ogg"));
        this.swapWrong = Gdx.audio.newSound(Gdx.files.internal("sound/swap_wrong.ogg"));
        this.swapSuccess = Gdx.audio.newSound(Gdx.files.internal("sound/swap_success.ogg"));
        this.clickListener = new ClickListener() {
            Tile firstClick;
            int count = 0;
            final Action afterSwap = new Action() {
                public boolean act(float delta) {
                    Field.this.findMatches();
                    return true;
                }
            };
            final Action afterClick = new Action() {
                public boolean act(float delta) {
                    Iterator var2 = Field.this.activeTiles.iterator();

                    while(var2.hasNext()) {
                        Tile tile = (Tile)var2.next();
                        tile.setTouchable(Touchable.enabled);
                    }

                    return true;
                }
            };

            public void clicked(InputEvent event, float x, float y) {
                Field.this.click.play(0.3F, 3.0F, 0.0F);
                Tile target = (Tile)event.getTarget();
                if (this.firstClick != null) {
                    target.clearActions();
                    this.firstClick.clearActions();
                    int tileIndex1 = Field.this.activeTiles.indexOf(this.firstClick, true);
                    int tileIndex2 = Field.this.activeTiles.indexOf(target, true);
                    Field.this.activeTiles.swap(tileIndex1, tileIndex2);
                    if ((tileIndex1 == tileIndex2 - 1 || tileIndex1 == tileIndex2 + 1 || tileIndex1 == tileIndex2 + 8 || tileIndex1 == tileIndex2 - 8) && Field.this.hasMatches()) {
                        Field.this.swapActor(tileIndex1, tileIndex2);
                        target.addAction(Actions.moveTo(this.firstClick.getX(), this.firstClick.getY(), 0.2F));
                        this.firstClick.addAction(Actions.sequence(Actions.moveTo(target.getX(), target.getY(), 0.2F), this.afterSwap));
                    } else {
                        Field.this.swapWrong.play(0.2F, 1.0F, 0.0F);
                        Field.this.activeTiles.swap(tileIndex1, tileIndex2);
                        target.addAction(Actions.sequence(Actions.moveTo(this.firstClick.getX(), this.firstClick.getY(), 0.1F), Actions.moveTo(target.getX(), target.getY(), 0.1F)));
                        this.firstClick.addAction(Actions.sequence(Actions.moveTo(target.getX(), target.getY(), 0.1F), Actions.moveTo(this.firstClick.getX(), this.firstClick.getY(), 0.1F), this.afterSwap));
                    }
                }

                ++this.count;
                this.firstClick = target;
                if (this.count == 2) {
                    this.firstClick = null;
                    this.count = 0;
                } else {
                    Iterator var7 = Field.this.activeTiles.iterator();

                    while(var7.hasNext()) {
                        Tile tile = (Tile)var7.next();
                        tile.setTouchable(Touchable.disabled);
                    }

                    target.setOrigin(1);
                    target.addAction(Actions.sequence(Actions.parallel(Actions.moveTo(target.getX(), target.getY() + 10.0F, 0.125F, Interpolation.swingOut), Actions.scaleBy(0.2F, 0.2F, 0.125F)), Actions.parallel(Actions.scaleBy(-0.2F, -0.2F, 0.125F), Actions.moveTo(target.getX(), target.getY(), 0.125F, Interpolation.swingIn)), this.afterClick));
                }

            }
        };
        this.afterMatch = new Action() {
            public boolean act(float delta) {
                Field.this.moveDown();
                Field.this.update();
                Field.this.findMatches();
                return true;
            }
        };
        this.defaults().width(80.0F).height(80.0F);
        this.setBounds(0.0F, 0.0F, 640.0F, 640.0F);
        this.setBackground(new TextureRegionDrawable(background));
        this.entities = sprites;

        for(int i = 0; i < 64; ++i) {
            Tile tile = new Tile();
            tile.addListener(this.clickListener);
            int num = MathUtils.random(0, 6);
            tile.init((TextureRegion)this.entities.get(num), num);
            this.activeTiles.add(tile);
            if (i % 8 == 0) {
                this.row();
            }

            this.add(tile);
        }

        this.findMatches();
    }

    private void swap(int index1, int index2) {
        this.activeTiles.swap(index1, index2);
        this.swapActor(index1, index2);
    }

    private void update() {
        this.clearChildren();
        int i = 0;

        for(Iterator var2 = this.activeTiles.iterator(); var2.hasNext(); ++i) {
            Tile tile = (Tile)var2.next();
            if (tile.type == -1) {
                ++this.score;
                int num = MathUtils.random(0, 6);
                tile.init((TextureRegion)this.entities.get(num), num);
                tile.addAction(Actions.sequence(Actions.fadeIn(0.25F)));
            }

            if (i % 8 == 0) {
                this.row();
            }

            this.add(tile);
        }

    }

    private void moveDown() {
        for(int i = 7; i >= 0; --i) {
            for(int j = 0; j < 8; ++j) {
                if (((Tile)this.activeTiles.get(j + i * 8)).type == -1) {
                    for(int n = i; n >= 0; --n) {
                        if (((Tile)this.activeTiles.get(j + n * 8)).type != -1) {
                            this.swap(j + n * 8, j + i * 8);
                            break;
                        }
                    }
                }
            }
        }

    }

    private void findMatches() {
        boolean hasMatch = false;

        int matches;
        int colorToMatch;
        int j;
        int i;
        int i2;
        for(j = 0; j < 8; ++j) {
            colorToMatch = ((Tile)this.activeTiles.get(j * 8)).type;
            matches = 1;

            for(i = 1; i < 8; ++i) {
                if (((Tile)this.activeTiles.get(i + j * 8)).type == colorToMatch) {
                    ++matches;
                } else {
                    colorToMatch = ((Tile)this.activeTiles.get(i + j * 8)).type;
                    if (matches >= 3) {
                        hasMatch = true;

                        for(i2 = i - 1; i2 >= i - matches; --i2) {
                            ((Tile)this.activeTiles.get(i2 + j * 8)).type = -1;
                        }
                    }

                    matches = 1;
                }
            }

            if (matches >= 3) {
                hasMatch = true;

                for(i = 7; i >= 8 - matches; --i) {
                    ((Tile)this.activeTiles.get(i + j * 8)).type = -1;
                }
            }
        }

        for(j = 0; j < 8; ++j) {
            colorToMatch = ((Tile)this.activeTiles.get(j)).type;
            matches = 1;

            for(i = 1; i < 8; ++i) {
                if (((Tile)this.activeTiles.get(j + i * 8)).type == colorToMatch) {
                    ++matches;
                } else {
                    colorToMatch = ((Tile)this.activeTiles.get(j + i * 8)).type;
                    if (matches >= 3) {
                        hasMatch = true;

                        for(i2 = i - 1; i2 >= i - matches; --i2) {
                            ((Tile)this.activeTiles.get(j + i2 * 8)).type = -1;
                        }
                    }

                    matches = 1;
                }
            }

            if (matches >= 3) {
                hasMatch = true;

                for(i = 7; i >= 8 - matches; --i) {
                    ((Tile)this.activeTiles.get(j + i * 8)).type = -1;
                }
            }
        }

        if (hasMatch) {
            this.swapSuccess.play(0.2F, 1.0F, 0.0F);
            j = 1;

            for(Iterator var8 = this.activeTiles.select((tilex) -> {
                return tilex.type == -1;
            }).iterator(); var8.hasNext(); ++j) {
                Tile tile = (Tile)var8.next();
                if (j % 3 == 0) {
                    tile.addAction(Actions.sequence(Actions.fadeOut(0.25F), this.afterMatch));
                } else {
                    tile.addAction(Actions.fadeOut(0.25F));
                }
            }
        }

    }

    private boolean hasMatches() {
        boolean hasMatch = false;

        int matches;
        int colorToMatch;
        int j;
        int i;
        for(j = 0; j < 8; ++j) {
            colorToMatch = ((Tile)this.activeTiles.get(j * 8)).type;
            matches = 1;

            for(i = 1; i < 8; ++i) {
                if (((Tile)this.activeTiles.get(i + j * 8)).type == colorToMatch) {
                    ++matches;
                } else {
                    colorToMatch = ((Tile)this.activeTiles.get(i + j * 8)).type;
                    if (matches >= 3) {
                        hasMatch = true;
                    }

                    matches = 1;
                }
            }

            if (matches >= 3) {
                hasMatch = true;
            }
        }

        for(j = 0; j < 8; ++j) {
            colorToMatch = ((Tile)this.activeTiles.get(j)).type;
            matches = 1;

            for(i = 1; i < 8; ++i) {
                if (((Tile)this.activeTiles.get(j + i * 8)).type == colorToMatch) {
                    ++matches;
                } else {
                    colorToMatch = ((Tile)this.activeTiles.get(j + i * 8)).type;
                    if (matches >= 3) {
                        hasMatch = true;
                    }

                    matches = 1;
                }
            }

            if (matches >= 3) {
                hasMatch = true;
            }
        }

        return hasMatch;
    }

    public void dispose() {
        this.click.dispose();
        this.swapWrong.dispose();
        this.swapSuccess.dispose();
    }
}
