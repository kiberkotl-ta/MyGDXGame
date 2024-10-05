package com.match3;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.badlogic.gdx.utils.Scaling;

public class GameWindow extends Window {
    public GameWindow(String title, Skin skin) {
        super(title, skin);
        this.getTitleTable().clearChildren();
        this.getTitleTable().defaults().space(5.0F);
        Button button = new Button(skin, "close");
        this.getTitleTable().add(button);
        Image image = new Image(skin, "title-bar-bg");
        image.setScaling(Scaling.stretchX);
        this.getTitleTable().add(image).growX();
        Label label = new Label("Match Three Game", skin);
        label.setFontScale(1.15F);
        this.getTitleTable().add(label).padLeft(20.0F).padRight(20.0F);
        image = new Image(skin, "title-bar-bg");
        image.setScaling(Scaling.stretchX);
        this.getTitleTable().add(image).growX();
        button = new Button(skin, "restore");
        this.getTitleTable().add(button);
        button = new Button(skin, "minimize");
        this.getTitleTable().add(button);
    }

    public void setMovable(final Stage stage) {
        final Vector2 position = new Vector2();
        this.getTitleTable().addListener(new ClickListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                position.x = stage.stageToScreenCoordinates(new Vector2(x, y)).x;
                position.y = stage.stageToScreenCoordinates(new Vector2(x, y)).y;
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
}