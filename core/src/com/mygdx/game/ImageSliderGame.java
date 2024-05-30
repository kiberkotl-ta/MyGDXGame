package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class ImageSliderGame implements Screen {
    SpriteBatch batch;
    Texture img1, img2, img3, currentImg;

    private StretchViewport viewport;
    private Skin skin;
    private Stage stage;
    private Texture[] images;
    private Image currentImage;
    private int currentIndex;

    @Override
    public void show() {
        batch = new SpriteBatch();
        img1 = new Texture("Comics Pictures/Norm.png");
        img2 = new Texture("Comics Pictures/You lose.png");
        img3 = new Texture("Comics Pictures/Win.png");
        currentImg = img1;

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);

        final ScrollPane scrollPane = new ScrollPane(new Image(currentImg));
        table.add(scrollPane).expand().fill();

        TextButton button = new TextButton("Switch Image", skin);


        viewport = new StretchViewport(800, 480);
        stage = new Stage(viewport);

        images = new Texture[6];
        images[0] = new Texture("Comics Pictures/Photo 1.jpg");
        images[1] = new Texture("Comics Pictures/Photo 2.png");
        images[2] = new Texture("Comics Pictures/Photo 3.png");
        images[3] = new Texture("Comics Pictures/Photo 4.png");
        images[4] = new Texture("Comics Pictures/Photo 5.png");
        images[5] = new Texture("Comics Pictures/Photo 6.png");

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
                if (currentImg == img1) {
                    currentImg = img2;
                    currentImg = img3;
                } else {
                    currentImg = img1;
                }
                scrollPane.setWidget(new Image(currentImg));

                if (currentIndex < images.length - 1) {
                    currentIndex++;
                    currentImage.setDrawable(new TextureRegionDrawable(new TextureRegion(images[currentIndex])));
                }
            }
        });

        table.row();
        table.add(button).expandX().bottom();

        stage.addActor(table);

        stage.addActor(currentImage);
        stage.addActor(btnPrev);
        stage.addActor(btnNext);

        Gdx.input.setInputProcessor(stage);



    }

    @Override
    public void render(float delta) {
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
        img1.dispose();
        img2.dispose();
        img3.dispose();
        batch.dispose();
        stage.dispose();
        skin.dispose();

        stage.dispose();
        for (Texture image : images) {
            image.dispose();
        }
    }
}


