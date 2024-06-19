package com.match3;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimatedImage {
    private Animation animation;
    private float stateTime;
    private boolean isPlaying;

    public AnimatedImage(Animation animation) {
        this.animation = animation;
        this.stateTime = 0f;
        this.isPlaying = false;
    }

    public void update(float delta) {
        if (isPlaying) {
            stateTime += delta;
        }
    }

    public void draw(SpriteBatch batch, float x, float y) {
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, x, y);
    }

    public void play() {
        isPlaying = true;
    }

    public void stop() {
        isPlaying = false;
        stateTime = 0f;
    }
}
