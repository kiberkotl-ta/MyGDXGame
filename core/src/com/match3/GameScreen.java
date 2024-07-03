package com.match3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

public class GameScreen extends ScreenAdapter {
    private static final int VIRTUAL_WIDTH = 800;
    private static final int VIRTUAL_HEIGHT = 480;

    private GameWindow game;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Field field;
    private int tileSize;
    private Vector2 selectedTile;

    public GameScreen(GameWindow game) {
        this.game = game;
        this.batch = game.batch;
        this.camera = new OrthographicCamera();
        this.viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);

        this.field = new Field(8, 8);
        this.tileSize = VIRTUAL_WIDTH / field.getWidth();
        this.selectedTile = null;
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    private void update(float delta) {
        handleInput();

        List matches = field.findMatches();
        if (!matches.isEmpty()) {
//            field.removeMatches(matches);
            field.fallDownTiles();
            field.refillEmptyTiles();
        }
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            int x = (int) (touchPos.x / tileSize);
            int y = (int) (touchPos.y / tileSize);

            if (x >= 0 && x < field.getWidth() && y >= 0 && y < field.getHeight()) {
                if (selectedTile == null) {
                    selectedTile = new Vector2(x, y);
                } else {
                    if (isAdjacent(x, y, (int) selectedTile.x, (int) selectedTile.y)) {
                        field.swapTiles(x, y, (int) selectedTile.x, (int) selectedTile.y);
                    }
                    selectedTile = null;
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            Gdx.app.exit();
        }
    }

    private boolean isAdjacent(int x1, int y1, int x2, int y2) {
        return (Math.abs(x1 - x2) == 1 && y1 == y2) || (Math.abs(y1 - y2) == 1 && x1 == x2);
    }

    private void draw() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int x = 0; x < field.getWidth(); x++) {
            for (int y = 0; y < field.getHeight(); y++) {
                Tile tile = field.getTile(x, y);
                if (tile != null) {
                    tile.draw(batch, tileSize);
                }
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
