package com.match3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MatchThree extends Game {
    private Skin skin;
    private Sound click;
    private Stage stage;
    private GameWindow window;
    private TextureAtlas textureAtlas;
    private FileHandle recordsFile;

    public MatchThree() {
    }

    public void create() {
        this.skin = new Skin(Gdx.files.internal("skin/OS Eight.json"));
        this.textureAtlas = new TextureAtlas("texture/Textures.atlas");
        this.click = Gdx.audio.newSound(Gdx.files.internal("sound/menu_click.ogg"));
        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);
        this.window = new GameWindow("", this.skin);
        this.window.setMovable(this.stage);
        this.window.setFillParent(true);
        this.stage.addActor(this.window);
        this.recordsFile = Gdx.files.local("Records");

    }

    public void playClick() {
        this.click.play(0.2F, 3.0F, 0.0F);

        try {
            Thread.sleep(100L);
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }

    public Skin getSkin() {
        return this.skin;
    }

    public GameWindow getWindow() {
        return this.window;
    }

    public TextureAtlas getTexture() {
        return this.textureAtlas;
    }

    public FileHandle getRecordsFile() {
        return this.recordsFile;
    }

    public void render() {
        super.render();
        Gdx.gl.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
        Gdx.gl.glClear(16384);
        this.stage.act(Gdx.graphics.getDeltaTime());
        this.stage.draw();
    }

    public void dispose() {
        super.dispose();
        this.textureAtlas.dispose();
        this.skin.dispose();
        this.stage.dispose();
        this.click.dispose();
    }
}
