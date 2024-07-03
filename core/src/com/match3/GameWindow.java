package com.match3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MainMenu;

public class GameWindow extends Game{
    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new GameScreen(this));

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
