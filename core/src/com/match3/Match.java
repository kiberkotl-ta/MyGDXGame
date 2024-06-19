package com.match3;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private int type;
    private List tiles;

    public Match(int type) {
        this.type = type;
        this.tiles = new ArrayList<>();
    }

    public void addTile(Vector2 position) {
        tiles.add(position);
    }

    public int getType() {
        return type;
    }

    public List getTiles() {
        return tiles;
    }
}
