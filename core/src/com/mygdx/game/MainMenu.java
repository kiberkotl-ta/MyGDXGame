package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenu  implements Screen {
    private static final String TAG = MainMenu.class.getSimpleName();
    private Stage stage;
    private Skin skin;

    private MainMenu intro;
    Game game;

    public MainMenu(Game game) {
        this.game = game;
        Gdx.app.log(TAG, "new MyGdxGame ");
        Camera camera = new OrthographicCamera();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin();
    }

    @Override
    public void show() {
        Gdx.app.log(TAG, "Showing screen");
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

        TextureRegionDrawable texture = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("Play.png"))));

        ImageButton imageButton1 = new ImageButton(texture);
        imageButton1.setSize(192,52);
        imageButton1.setPosition(10,Gdx.graphics.getHeight()-10);

        imageButton1.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new ImageSliderGame(1));
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(imageButton1);

        //        2 кнопка
        TextureRegionDrawable texture2 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("skin/Easy.png"))));

        ImageButton imageButton2 = new ImageButton(texture2);
        imageButton2.setSize(192,52);
        imageButton2.setPosition(10, Gdx.graphics.getHeight()-20*7);
        imageButton2.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new ImageSliderGame(2));
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(imageButton2);

        //        3 кнопка
        TextureRegionDrawable texture3 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("skin/Norm.png"))));

        ImageButton imageButton3 = new ImageButton(texture3);
        imageButton3.setSize(192,52);
        imageButton3.setPosition(10, Gdx.graphics.getHeight()-30*7);
        imageButton3.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new ImageSliderGame(3));
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(imageButton3);

        //        4 кнопка
        TextureRegionDrawable texture4 = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("skin/Hard.png"))));

        ImageButton imageButton4 = new ImageButton(texture4);
        imageButton4.setSize(192,52);
        imageButton4.setPosition(10, Gdx.graphics.getHeight()-40*7);
        imageButton4.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                game.setScreen(new ImageSliderGame(4));
            }

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

// 4 кнопка на сцену
        stage.addActor(imageButton4);


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default-font");
        skin.add("default", textButtonStyle);
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        Gdx.app.log(TAG, "Pausing screen.");
    }

    @Override
    public void resume() {
        Gdx.app.log(TAG, "Resuming screen.");
    }

    @Override
    public void hide() {
        Gdx.app.log(TAG, "Hiding screen.");
        stage.clear();
    }
    @Override
    public void dispose() {
        Gdx.app.log(TAG, "Disposing screen.");
        stage.dispose();
        skin.dispose();
    }
}
