package com.match3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MainMenu;
import com.mygdx.game.MyGdxGame;

public class GameWindow extends MyGdxGame {
    public SpriteBatch batch;
    private Game game;
    private MainMenu menuScreen;
    private GameScreen gameScreen;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new GameScreen(this));
//        setScreen(new GameWindow());
    }

    public void startGame() {
        game.setScreen(gameScreen);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
