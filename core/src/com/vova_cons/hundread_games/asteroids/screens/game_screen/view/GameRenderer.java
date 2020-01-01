package com.vova_cons.hundread_games.asteroids.screens.game_screen.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.vova_cons.hundread_games.asteroids.screens.BaseScreen;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.Body;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameObject;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;
import com.vova_cons.hundread_games.asteroids.services.ServiceLocator;
import com.vova_cons.hundread_games.asteroids.services.assets_service.AssetsService;

/**
 * Created by vova_cons on 01.01.2020.
 */
public class GameRenderer extends Group {
    private GameWorld world;
    private Sprite bg;
    private Sprite player;
    private Sprite asteroid;
    private Sprite bullet;

    public GameRenderer(GameWorld world) {
        this.world = world;
        this.setSize(1000f, 1000f);
        AssetsService assetsService = ServiceLocator.getService(AssetsService.class);
        bg = new Sprite(assetsService.getTexture("textures/bg.jpg"));
        bg.setSize(this.getWidth(), this.getHeight());
        asteroid = new Sprite(assetsService.getTexture("textures/asteroid.png"));
        bullet = new Sprite(assetsService.getTexture("textures/bullet.png"));
        player = new Sprite(assetsService.getTexture("textures/spaceship.png"));
    }

    //region logic


    @Override
    protected void drawChildren(Batch batch, float parentAlpha) {
        super.drawChildren(batch, parentAlpha);
        drawBg(batch);
        drawPlayer(batch);
        drawAsteroids(batch);
        drawBullets(batch);
    }

    private void drawBg(Batch batch) {
        bg.draw(batch);
    }

    private void drawPlayer(Batch batch) {
        Body playerBody = world.player.body;
        player.setSize(playerBody.w, playerBody.h);
        player.setPosition(playerBody.x - playerBody.w/2f, playerBody.y - playerBody.h/2f);
        player.setOriginCenter();
        player.setRotation(playerBody.rotate);
        player.draw(batch);
    }

    private void drawAsteroids(Batch batch) {
        for(GameObject asteroidObj : world.asteroids) {
            Body asteroidBody = asteroidObj.body;
            asteroid.setSize(asteroidBody.w, asteroidBody.h);
            asteroid.setPosition(asteroidBody.x - asteroidBody.w/2f, asteroidBody.y - asteroidBody.h/2f);
            asteroid.setOriginCenter();
            asteroid.setRotation(asteroidBody.rotate);
            asteroid.draw(batch);
        }
    }

    private void drawBullets(Batch batch) {
        for(GameObject bulletObj : world.bullets) {
            Body bulletBody = bulletObj.body;
            bullet.setSize(bulletBody.w, bulletBody.h);
            bullet.setPosition(bulletBody.x - bulletBody.w/2f, bulletBody.y - bulletBody.h/2f);
            bullet.setOriginCenter();
            bullet.setRotation(bulletBody.rotate);
            bullet.draw(batch);
        }
    }
    //endregion
}
