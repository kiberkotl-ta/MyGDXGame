package com.match3;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.util.Iterator;

public class GameScreen extends ScreenAdapter {
    private final MatchThree game;
    private Field field;
    private Label scoreCounter;
    private ProgressBar timeBar;
    private float time = 0.0F;

    public GameScreen(MatchThree game) {
        this.game = game;
    }

    public void show() {
        setScreen(new MatchThree(this));

        final GameWindow window = this.game.getWindow();
        final Table mainTable = new Table();
        mainTable.addAction(Actions.sequence(Actions.fadeOut(0.01F), Actions.fadeIn(1.0F)));
        Table table = new Table();
        table.padLeft(50.0F);
        mainTable.setBackground(new TextureRegionDrawable(this.game.getTexture().findRegion("table")));
        table.defaults().align(1).space(5.0F);
        mainTable.add(table);
        this.field = new Field(this.game.getTexture().findRegion("background"), this.game.getTexture().findRegions("entity"));
        mainTable.add(this.field).padLeft(40.0F);
        Label label = new Label("SCORE", this.game.getSkin());
        table.add(label);
        table.row().padBottom(160.0F);
        this.scoreCounter = new Label("0", this.game.getSkin());
        this.scoreCounter.setFontScale(1.5F);
        table.add(this.scoreCounter);
        table.row();
        label = new Label("TIME", this.game.getSkin());
        table.add(label);
        table.row().padBottom(170.0F);
        this.timeBar = new ProgressBar(0.0F, 60.0F, 1.0F, false, this.game.getSkin(), "startup");
        this.timeBar.setValue(60.0F);
        this.timeBar.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                if (GameScreen.this.timeBar.getValue() <= GameScreen.this.timeBar.getMinValue()) {
                    Iterator var3 = GameScreen.this.field.getChildren().iterator();

                    while(var3.hasNext()) {
                        Actor tile = (Actor)var3.next();
                        tile.setTouchable(Touchable.disabled);
                        float distance = (float)(-Math.random()) * 1000.0F;
                        tile.addAction(Actions.moveBy(0.0F, distance, 1.0F));
                    }

                    GameScreen.this.field.addAction(Actions.sequence(Actions.fadeOut(1.0F), new Action() {
                        public boolean act(float delta) {
                            Table table = new Table();
                            table.setSize(GameScreen.this.field.getWidth(), GameScreen.this.field.getHeight());
                            table.setOrigin(GameScreen.this.field.getOriginX(), GameScreen.this.field.getOriginY());
                            table.setBounds(GameScreen.this.field.getX(), GameScreen.this.field.getY(), GameScreen.this.field.getWidth(), GameScreen.this.field.getHeight());
                            table.defaults().align(1).space(10.0F);
                            Label label = new Label("Game over, save your score!", GameScreen.this.game.getSkin());
                            table.add(label).align(1);
                            table.row();
                            HorizontalGroup group = new HorizontalGroup();
                            group.setBounds(table.getX(), table.getY(), 200.0F, 100.0F);
                            group.setOrigin(1);
                            final TextField textField = new TextField("Enter your name", GameScreen.this.game.getSkin());
                            textField.setSize(180.0F, 30.0F);
                            group.addActor(textField);
                            TextButton saveBtn = new TextButton("OK", GameScreen.this.game.getSkin());
                            saveBtn.setSize(60.0F, 30.0F);
                            saveBtn.addListener(new ChangeListener() {
                                public void changed(ChangeListener.ChangeEvent event, Actor actor) {

                                    window.removeActor(mainTable);
                                    GameScreen.this.writeNewRecord(textField.getText(), GameScreen.this.field.getScore());

                                }
                            });
                            group.addActor(saveBtn);
                            group.space(10.0F);
                            table.add(group).align(1);
                            mainTable.removeActor(GameScreen.this.field);
                            mainTable.add(table).size(table.getWidth(), table.getHeight());
                            return true;
                        }
                    }));
                }

            }
        });
        table.add(this.timeBar);
        table.row();
        TextButton menuBtn = new TextButton("Menu", this.game.getSkin());
        menuBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {

                window.removeActor(mainTable);

            }
        });
        table.add(menuBtn).size(menuBtn.getWidth() * 4.0F).height(menuBtn.getHeight() * 2.0F).align(4);
        window.add(mainTable);
    }

    private void writeNewRecord(String name, int time) {
        if (!name.isEmpty()) {
            this.game.getRecordsFile().writeString(time + "\n", true);
            this.game.getRecordsFile().writeString(name + "\n", true);
        }

    }

    public void render(float delta) {
        this.time += delta;
        if (this.time >= 1.0F) {
            this.timeBar.setValue(this.timeBar.getValue() - 1.0F);
            this.time = 0.0F;
        }

        this.scoreCounter.setText(this.field.getScore());
    }

    public void hide() {
        this.dispose();
    }

    public void dispose() {
        this.field.dispose();
    }
}
