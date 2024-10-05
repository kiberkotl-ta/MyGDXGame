package com.match3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.game.MainMenu;

public class MatchThree extends Game {
    private Skin skin;
    private Stage stage;
    private GameWindow window;
    private TextureAtlas textureAtlas;
    private FileHandle recordsFile;

    public MatchThree() {
    }

    public void create() {
        this.textureAtlas = new TextureAtlas("skin/red.png");
        this.textureAtlas = new TextureAtlas("skin/Playing area.png");
        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);
        this.window = new GameWindow("", this.skin);
        this.window.setMovable(this.stage);
        this.window.setFillParent(true);
        this.stage.addActor(this.window);
        this.recordsFile = Gdx.files.local("Records");

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

    }
}
