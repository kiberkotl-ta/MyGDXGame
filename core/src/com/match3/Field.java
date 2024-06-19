package com.match3;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Field {
    private int width;
    private int height;
    private Tile[][] tiles;
    private Random random;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        this.random = new Random();
        generateField();
    }

    private void generateField() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile(x, y, random.nextInt(Tile.TILE_TYPES));
            }
        }
    }

    public void swapTiles(int x1, int y1, int x2, int y2) {
        Tile temp = tiles[x1][y1];
        tiles[x1][y1] = tiles[x2][y2];
        tiles[x2][y2] = temp;

        tiles[x1][y1].setGridPosition(x1, y1);
        tiles[x2][y2].setGridPosition(x2, y2);
    }

    public List findMatches() {
        List matches = new ArrayList<>();
        matches.addAll(findHorizontalMatches());
        matches.addAll(findVerticalMatches());
        return matches;
    }

    private List findHorizontalMatches() {
        List matches = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width - 2; x++) {
                if (tiles[x][y].getType() == tiles[x + 1][y].getType() &&
                        tiles[x][y].getType() == tiles[x + 2][y].getType()) {
                    Match match = new Match(tiles[x][y].getType());
                    match.addTile(new Vector2(x, y));
                    match.addTile(new Vector2(x + 1, y));
                    match.addTile(new Vector2(x + 2, y));
                    matches.add(match);
                }
            }
        }
        return matches;
    }

    private List findVerticalMatches() {
        List matches = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height - 2; y++) {
                if (tiles[x][y].getType() == tiles[x][y + 1].getType() &&
                        tiles[x][y].getType() == tiles[x][y + 2].getType()) {
                    Match match = new Match(tiles[x][y].getType());
                    match.addTile(new Vector2(x, y));
                    match.addTile(new Vector2(x, y + 1));
                    match.addTile(new Vector2(x, y + 2));
                    matches.add(match);
                }
            }
        }
        return matches;
    }

    public void removeMatches(List match) {
        for (Match  : matches) {
            for (Vector position : match.getTiles()) {
                tiles[(int) position.x][(int) position.y] = null;
            }
        }
    }

    public void fallDownTiles() {
        for (int x = 0; x < width; x++) {
            for (int y = height - 1; y > 0; y--) {
                if (tiles[x][y] == null) {
                    for (int yy = y - 1; yy >= 0; yy--) {
                        if (tiles[x][yy] != null) {
                            swapTiles(x, yy, x, y);
                            break;
                        }
                    }
                }
            }
        }
    }

    public void refillEmptyTiles() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tiles[x][y] == null) {
                    tiles[x][y] = new Tile(x, y, random.nextInt(Tile.TILE_TYPES));
                }
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
