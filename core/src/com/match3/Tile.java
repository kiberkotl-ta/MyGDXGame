package com.match3;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Tile extends Image {
    public int type = -1;

    public Tile() {
    }

    public void init(TextureRegion sprite, int index) {
        this.setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.setDrawable(new TextureRegionDrawable(sprite));
        this.type = index;
    }
}
