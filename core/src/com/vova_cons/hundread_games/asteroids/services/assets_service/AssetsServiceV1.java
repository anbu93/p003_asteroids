package com.vova_cons.hundread_games.asteroids.services.assets_service;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by anbu on 19.11.19.
 **/
public class AssetsServiceV1 implements AssetsService {
    private AssetManager assetManager;

    public AssetsServiceV1() {
        assetManager = new AssetManager();
    }

    @Override
    public void loadTextures() {
        assetManager.load("badlogic.jpg", Texture.class);
        assetManager.load("textures/asteroid.png", Texture.class);
        assetManager.load("textures/bg.jpg", Texture.class);
        assetManager.load("textures/bullet.png", Texture.class);
        assetManager.load("textures/play.png", Texture.class);
        assetManager.load("textures/spaceship.png", Texture.class);
    }

    @Override
    public void update(float delta) {
        assetManager.update();
    }

    @Override
    public boolean isFinishLoading() {
        return assetManager.isFinished();
    }

    @Override
    public Texture getTexture(String path) {
        return assetManager.get(path);
    }

    @Override
    public TextureRegion getTextureRegion(String atlasPath, String regionId) {
        TextureAtlas atlas = assetManager.get(atlasPath);
        return atlas.findRegion(regionId);
    }
}
