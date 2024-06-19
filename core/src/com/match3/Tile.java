package com.match3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {
    public static final int TILE_TYPES = 8;

    private int x;
    private int y;
    private int type;
    private Texture texture;

    public Tile(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.texture = new Texture(Gdx.files.internal("красный" + type + ".png"));
    }

    public void draw(SpriteBatch batch, int tileSize) {
        batch.draw(texture, x * tileSize, y * tileSize, tileSize, tileSize);
    }

    public void setGridPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getType() {
        return type;
    }
}
