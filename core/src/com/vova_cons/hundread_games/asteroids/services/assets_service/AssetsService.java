package com.vova_cons.hundread_games.asteroids.services.assets_service;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.vova_cons.hundread_games.asteroids.services.Service;

/**
 * Created by anbu on 19.11.19.
 **/
public interface AssetsService extends Service {
    void loadTextures();
    void update(float delta);
    boolean isFinishLoading();

    Texture getTexture(String path);
    TextureRegion getTextureRegion(String atlasPath, String regionId);
}
