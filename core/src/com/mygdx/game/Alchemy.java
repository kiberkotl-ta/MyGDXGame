package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Alchemy implements Screen {
    private SpriteBatch batch;
    private Texture image1, image2;
    private Table table1, table2;
    private Skin skin;


    @Override
    public void show() {
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("skin/uiskin.json")); //

        image1 = new Texture(Gdx.files.internal("purple_box.jpg"));
        image2 = new Texture(Gdx.files.internal("purple_box.jpg"));

        table1 = new Table();
        table1.add(new Label("Element 1", skin)).row();
        table1.add(new Image(image1)).row();

        table2 = new Table();
        table2.add(new Label("Element 2", skin)).row();
        table2.add(new Image(image2)).row();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        table1.draw(batch, 1);
        table2.draw(batch, 1);
        batch.end();

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
        batch.dispose();
        image1.dispose();
        image2.dispose();
        skin.dispose();
    }
}
