package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class ImageSliderGame implements Screen {
    private SpriteBatch batch;
    private Stage stage;
    private Texture image1, image2, image3;
    private Image currentImage;


    private StretchViewport viewport;
    private Texture[] images;
    private int currentIndex;

    @Override
    public void show() {
        ImageSliderGame game = new ImageSliderGame();

//        Функция исходов
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        // Загрузка картинок
        image1 = new Texture("Comics Pictures/Easy 1.png");
        image1 = new Texture("Comics Pictures/Easy 2.png");
        image1 = new Texture("Comics Pictures/Easy 3.png");
        image1 = new Texture("Comics Pictures/Easy 4.png");

        image2 = new Texture("Comics Pictures/Norm 1.png");
        image2 = new Texture("Comics Pictures/Norm 2.png");
        image2 = new Texture("Comics Pictures/Norm 3.png");

        image3 = new Texture("Comics Pictures/Hard 1.png");
        image3 = new Texture("Comics Pictures/Hard 2.png");
        image3 = new Texture("Comics Pictures/Hard 3.png");
        image3 = new Texture("Comics Pictures/Hard 4.png");
        image3 = new Texture("Comics Pictures/Hard 5.png");

        currentImage = new Image();

        currentImage.setDrawable(new SpriteDrawable(new Sprite(image1))); // Устанавливаем начальное отображаемое изображение

        // Создание кнопок
        ImageButton button1 = new ImageButton(skin);
        button1.setPosition(100, 100);
        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentImage.setDrawable(new SpriteDrawable(new Sprite(image1)));// Обновляем текущее изображение
            }
        });

        ImageButton button2 = new ImageButton(skin);
        button2.setPosition(250, 100);
        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentImage.setDrawable(new SpriteDrawable(new Sprite(image2))); // Обновляем текущее изображение
            }
        });

        ImageButton button3 = new ImageButton(skin);
        button3.setPosition(400, 100);
        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                currentImage.setDrawable(new SpriteDrawable(new Sprite(image3))); // Обновляем текущее изображение
            }
        });

        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button3);

//        Комикс
        viewport = new StretchViewport(800, 480);
        stage = new Stage(viewport);

        images = new Texture[9];
        images[0] = new Texture("Comics Pictures/Photo 1.jpg");
        images[1] = new Texture("Comics Pictures/Photo 2.png");
        images[2] = new Texture("Comics Pictures/Photo 3.png");
        images[3] = new Texture("Comics Pictures/Photo 4.png");
        images[4] = new Texture("Comics Pictures/Photo 5.png");
        images[5] = new Texture("Comics Pictures/Photo 6.png");
        images[6] = new Texture("Comics Pictures/Photo 7.png");
        images[7] = new Texture("Comics Pictures/Photo 8.png");
        images[8] = new Texture("Comics Pictures/Photo 9.png");

        currentIndex = 0;
        currentImage = new Image(images[currentIndex]);
        currentImage.setPosition(Gdx.graphics.getWidth() / 2 - currentImage.getWidth() / 2, Gdx.graphics.getHeight() / 2 - currentImage.getHeight() / 2);

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
                    currentImage.setDrawable(new TextureRegionDrawable(new TextureRegion(images[currentIndex])));
                }
            }
        });

        TextButton btnNext = new TextButton("Next", style);
        btnNext.setPosition(Gdx.graphics.getWidth() - 120, 20);
        btnNext.setSize(100, 50);
        btnNext.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentIndex < images.length - 1) {
                    currentIndex++;
                    currentImage.setDrawable(new TextureRegionDrawable(new TextureRegion(images[currentIndex])));
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
//        batch.draw(currentImage, 0, 0);
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
        image1.dispose();
        image2.dispose();
        image3.dispose();

        for (Texture image : images) {
            image.dispose();
        }
    }
}


