package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Alchemy implements Screen {
    Table table;
    Skin skin;
    Stage stage;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        TextureRegionDrawable texture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("purple_box.jpg"))));
        ImageButton imageButton1 = new ImageButton(texture);
        ImageButton imageButton2 = new ImageButton(texture);
        ImageButton imageButton3 = new ImageButton(texture);
        ImageButton imageButton4 = new ImageButton(texture);
        ImageButton imageButton5 = new ImageButton(texture);

        table = new Table();
        table.setDebug(true);
        table.setFillParent(true);

        table.add(imageButton1).width(100).height(100);
        table.add(imageButton2).width(100).height(100);
        table.row();
        table.add(imageButton3).width(100).height(100);
        table.add(imageButton4).width(100).height(100);
        table.add(imageButton5).width(100).height(100);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
