package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Image;



import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.ArrayList;


public class ImageSliderGame implements Screen {
    private SpriteBatch batch;
    private Stage stage;
    private Image currentImage;


    private StretchViewport viewport;
    private ArrayList<Texture> images;
    private int currentIndex;

    int numberButton;

    public ImageSliderGame(int numberButton) {
        this.numberButton = numberButton;
    }

    @Override
    public void show() {
//        Функция исходов
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        batch = new SpriteBatch();
        viewport = new StretchViewport(800, 480);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

//        Комикс
        images = new ArrayList<>();
        images.add(new Texture("Comics Pictures/Photo 1.jpg"));
        images.add(new Texture("Comics Pictures/Photo 2.png"));
        images.add(new Texture("Comics Pictures/Photo 3.png"));
        images.add(new Texture("Comics Pictures/Photo 4.png"));
        images.add(new Texture("Comics Pictures/Photo 5.png"));
        images.add(new Texture("Comics Pictures/Photo 6.png"));
        images.add(new Texture("Comics Pictures/Photo 7.png"));
        images.add(new Texture("Comics Pictures/Photo 8.png"));
        images.add(new Texture("Comics Pictures/Photo 9.png"));

        currentImage = new Image();

         switch (numberButton) {

            case 3:
                images.add(new Texture("Comics Pictures/Easy 1.png"));
                images.add(new Texture("Comics Pictures/Easy 2.png"));
                images.add(new Texture("Comics Pictures/Easy 3.png"));
                images.add(new Texture("Comics Pictures/Easy 4.png"));
                break;


            case 4:
                images.add(new Texture("Comics Pictures/Hard 1.png"));
                images.add(new Texture("Comics Pictures/Hard 2.png"));
                images.add(new Texture("Comics Pictures/Hard 3.png"));
                images.add(new Texture("Comics Pictures/Hard 4.png"));
                images.add(new Texture("Comics Pictures/Hard 5.png"));



            case 2:
                images.add(new Texture("Comics Pictures/Norm 1.png"));
                images.add(new Texture("Comics Pictures/Norm 2.png"));
                images.add(new Texture("Comics Pictures/Norm 3.png"));
                break;

             case 1:
                 break;
        }


        currentIndex = 0;
        currentImage = new Image(images.get(currentIndex));
        currentImage.setPosition((float) Gdx.graphics.getWidth() / 2 - currentImage.getWidth() / 2, (float) Gdx.graphics.getHeight() / 2 - currentImage.getHeight() / 2);

        // кнопки для переключения изображений
        TextButtonStyle style = new TextButtonStyle();
        style.up = skin.newDrawable("white", Color.DARK_GRAY);
        style.down = skin.newDrawable("white", Color.LIGHT_GRAY);
        style.font = skin.getFont("default-font");
        TextButton btnPrev = new TextButton("Prev", style);
        btnPrev.setPosition(20, 20);
        btnPrev.setSize(100, 50);
        btnPrev.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentIndex > 0) {
                    currentIndex--;
                    currentImage.setDrawable(new TextureRegionDrawable(new TextureRegion(images.get(currentIndex))));
                }
            }
        });

        TextButton btnNext = new TextButton("Next", style);
        btnNext.setPosition(Gdx.graphics.getWidth() - 120, 20);
        btnNext.setSize(100, 50);
        btnNext.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentIndex < images.size() - 1) {
                    currentIndex++;
                    currentImage.setDrawable(new TextureRegionDrawable(new TextureRegion(images.get(currentIndex))));
                }
            }
        });

        stage.addActor(currentImage);
        stage.addActor(btnPrev);
        stage.addActor(btnNext);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        currentImage.draw(batch, 1);
        batch.end();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
//        Исходы
        batch.dispose();
        stage.dispose();

        for (Texture image : images) {
            image.dispose();
        }
    }
}