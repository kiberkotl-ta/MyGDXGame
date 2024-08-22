package com.match3;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AnimatedImage extends Image {
    protected Animation<TextureRegion> animation;
    private float stateTime = 0.0F;

    public AnimatedImage(Animation<TextureRegion> animation) {
        super((TextureRegion)animation.getKeyFrame(0.0F));
        this.animation = animation;
    }

    public void act(float delta) {
        ((TextureRegionDrawable)this.getDrawable()).setRegion((TextureRegion)this.animation.getKeyFrame(this.stateTime += delta, true));
        super.act(delta);
    }
}
