package com.badlogic.circledemo;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    Array<TextureRegion> frames;
    float maxFrameTime;
    float currentFrameTime;
    int frameCount;
    int frame;

    public Animation(TextureRegion region, int frameCount, float cycleTime){
        frames = new Array<TextureRegion>();
        TextureRegion temp;
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i = 0; i < frameCount; i++){
            temp = new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight());
            frames.add(temp);
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    // Novo construtor que aceita um array de TextureRegion
    public Animation(TextureRegion[] regions, float cycleTime) {
        frames = new Array<>(regions);
        this.frameCount = regions.length;
        this.maxFrameTime = cycleTime / frameCount;
        this.frame = 0;
    }

    public void update(float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount)
            frame = 0;
    }

    public void setFrameIni(){
        frame = 0;
    }

    public void flip(){
        for(TextureRegion region : frames)
            region.flip(true, false);
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }

    public int getFrameIndex() {
        return frame;
    }

    public TextureRegion getFrame(int index) {
        return frames.get(index);
    }
}