package com.match3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MenuScreen extends ScreenAdapter {
    private final MatchThree game;

    public MenuScreen(MatchThree game) {
        this.game = game;
    }

    public void show() {
        final GameWindow window = this.game.getWindow();
        final Table table = new Table();
        window.add(table);
        Image clouds = new Image(this.game.getTexture().findRegion("clouds"));
        clouds.setFillParent(true);
        clouds.addAction(Actions.forever(Actions.sequence(Actions.moveBy(20.0F, 0.0F, 3.0F), Actions.moveBy(-20.0F, 0.0F, 3.0F))));
        AnimatedImage gemAnimation = new AnimatedImage(new Animation(0.05F, this.game.getTexture().findRegions("gem")));
        gemAnimation.setSize(200.0F, 200.0F);
        gemAnimation.setPosition(450.0F, 180.0F, 1);
        Group backSplash = new Group();
        backSplash.addActor(clouds);
        backSplash.addActor(gemAnimation);
        table.add(backSplash).prefSize(900.0F, 360.0F).padBottom(30.0F);
        table.row();
        TextButton playBtn = new TextButton("Play", this.game.getSkin());
        table.defaults().space(15.0F).width(playBtn.getWidth() * 4.0F).height(playBtn.getHeight() * 2.0F);
        playBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                window.removeActor(table);
                MenuScreen.this.game.setScreen(new GameScreen(MenuScreen.this.game));
            }
        });
        table.add(playBtn);
        table.row();
        TextButton recordsBtn = new TextButton("Records", this.game.getSkin());
        recordsBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                window.removeActor(table);
                MenuScreen.this.game.setScreen(new RecordsScreen(MenuScreen.this.game));
            }
        });
        table.add(recordsBtn);
        table.row();
        TextButton exitBtn = new TextButton("Exit", this.game.getSkin());
        exitBtn.addListener(new ChangeListener() {
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        table.add(exitBtn);
    }
}
