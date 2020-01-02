package com.vova_cons.hundread_games.asteroids.screens.game_screen.view;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.logic.GameSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.BodyComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameEntity;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.RotationComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.SpriteComponent;
import com.vova_cons.hundread_games.asteroids.services.ServiceLocator;
import com.vova_cons.hundread_games.asteroids.services.assets_service.AssetsService;

/**
 * Created by vova_cons on 01.01.2020.
 */
public class RendererSystem extends Group implements GameSystem {
    public static final int UNKNOWN = 0;
    public static final int ASTEROID = 1;
    public static final int PLAYER = 2;
    public static final int BULLET = 3;
    private final GameWorld world;
    private Sprite bg;
    private Sprite[] sprites = new Sprite[4];

    public RendererSystem(GameWorld world) {
        this.world = world;
        this.setSize(1000f, 1000f);
        AssetsService assetsService = ServiceLocator.getService(AssetsService.class);
        bg = new Sprite(assetsService.getTexture("textures/bg.jpg"));
        bg.setSize(1000f, 1000f);
        sprites[UNKNOWN] = new Sprite(assetsService.getTexture("badlogic.jpg"));
        sprites[ASTEROID] = new Sprite(assetsService.getTexture("textures/asteroid.png"));
        sprites[BULLET] = new Sprite(assetsService.getTexture("textures/bullet.png"));
        sprites[PLAYER] = new Sprite(assetsService.getTexture("textures/spaceship.png"));
    }

    //region logic
    @Override
    public void update(float delta, GameWorld world) {} //do nothing

    @Override
    protected void drawChildren(Batch batch, float parentAlpha) {
        super.drawChildren(batch, parentAlpha);
        bg.draw(batch);
        for(GameEntity entity : world.getEntitiesFilterAt(SpriteComponent.class)) {
            int spriteId = entity.getComponent(SpriteComponent.class).sprite;
            if (spriteId != -1 && entity.isComponentExists(BodyComponent.class)) {
                Sprite sprite = sprites[spriteId];
                BodyComponent body = entity.getComponent(BodyComponent.class);
                sprite.setPosition(body.x - body.w/2f, body.y - body.h/2f);
                sprite.setSize(body.w, body.h);
                if (entity.isComponentExists(RotationComponent.class)) {
                    float rotation = entity.getComponent(RotationComponent.class).rotation;
                    sprite.setOriginCenter();
                    sprite.setRotation(rotation);
                }
                sprite.draw(batch);
            }
        }
    }
    //endregion
}