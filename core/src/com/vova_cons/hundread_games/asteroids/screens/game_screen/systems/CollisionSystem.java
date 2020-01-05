package com.vova_cons.hundread_games.asteroids.screens.game_screen.systems;

import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameEntity;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.BodyComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.BulletComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.DeleteEntityComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.PlayerComponent;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vova_cons on 03.01.2020.
 */
public class CollisionSystem implements GameSystem {
    private static final int TYPE_PLAYER = 0;
    private static final int TYPE_ASTEROID = 1;
    private static final int TYPE_BULLET = 2;
    private List<GameEntity> entityList = new LinkedList<GameEntity>();

    //region logic
    @Override
    public void update(float delta, GameWorld world) {
        entityList.addAll(world.getEntitiesFilterAt(BodyComponent.class));
        for(GameEntity entity : entityList) {
            processEntity(entity);
        }
        entityList.clear();
    }

    private void processEntity(GameEntity entity) {
        int entityType = detectType(entity);
        switch(entityType) {
            case TYPE_PLAYER:
                processPlayer(entity);
                break;
            case TYPE_BULLET:
                processBullet(entity);
                break;
            case TYPE_ASTEROID:
                break;
        }
    }

    private void processPlayer(GameEntity playerEntity) {
        for(GameEntity otherEntity : entityList) {
            if (otherEntity != playerEntity) {
                int otherEntityType = detectType(otherEntity);
                if (otherEntityType == TYPE_ASTEROID && isEntitiesCollide(playerEntity, otherEntity)) {
                    playerEntity.addComponent(DeleteEntityComponent.class, new DeleteEntityComponent());
                }
            }
        }
    }

    private void processBullet(GameEntity bulletEntity) {
        for(GameEntity otherEntity : entityList) {
            if (otherEntity != bulletEntity) {
                int otherEntityType = detectType(otherEntity);
                if (otherEntityType == TYPE_ASTEROID && isEntitiesCollide(bulletEntity, otherEntity)) {
                    bulletEntity.addComponent(DeleteEntityComponent.class, new DeleteEntityComponent());
                    otherEntity.addComponent(DeleteEntityComponent.class, new DeleteEntityComponent());
                }
            }
        }
    }
    //endregion


    //region util methods
    private int detectType(GameEntity entity) {
        if (entity.isComponentExists(PlayerComponent.class)) {
            return TYPE_PLAYER;
        }
        if (entity.isComponentExists(BulletComponent.class)) {
            return TYPE_BULLET;
        }
        return TYPE_ASTEROID;
    }

    private boolean isEntitiesCollide(GameEntity a, GameEntity b) {
        BodyComponent aBody = a.getComponent(BodyComponent.class);
        BodyComponent bBody = b.getComponent(BodyComponent.class);
        float dx = Math.abs(aBody.x - bBody.x);
        float dy = Math.abs(aBody.y - bBody.y);
        return dx < (aBody.w/2f + bBody.w/2f) && dy < (aBody.h/2f + bBody.h/2f);
    }
    //endregion
}
