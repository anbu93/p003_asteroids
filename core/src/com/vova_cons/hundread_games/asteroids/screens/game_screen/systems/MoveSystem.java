package com.vova_cons.hundread_games.asteroids.screens.game_screen.systems;

import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameEntity;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.BodyComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.DeleteEntityComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.VelocityComponent;

import static com.vova_cons.hundread_games.asteroids.screens.game_screen.GameBalance.*;

/**
 * Created by vova_cons on 02.01.2020.
 */
public class MoveSystem implements GameSystem {
    @Override
    public void update(float delta, GameWorld world) {
        for(GameEntity entity : world.getEntitiesFilterAt(VelocityComponent.class)) {
            moveEntity(entity, delta);
        }
    }

    private void moveEntity(GameEntity entity, float delta) {
        VelocityComponent velocity = entity.getComponent(VelocityComponent.class);
        BodyComponent body = entity.getComponent(BodyComponent.class);
        body.moveBy(velocity.x * delta, velocity.y * delta);
        switch(detectEntityType(entity)) {
            case TYPE_PLAYER:
                processPlayerEdges(body);
                break;
            case TYPE_BULLET:
                processBulletEdges(entity, body);
                break;
            case TYPE_ASTEROID:
                processAsteroidsEdges(body, velocity);
                break;
        }
    }

    private void processPlayerEdges(BodyComponent body) {
        if (body.x < 0) {
            body.x = GAME_WIDTH + body.x;
        }
        if (body.x > GAME_WIDTH) {
            body.x = body.x - GAME_WIDTH;
        }
        if (body.y < 0) {
            body.y = GAME_HEIGHT + body.y;
        }
        if (body.y > GAME_HEIGHT) {
            body.y = body.y - GAME_HEIGHT;
        }
    }

    private void processBulletEdges(GameEntity entity, BodyComponent body) {
        if (body.x < 0 || body.x > GAME_WIDTH || body.y < 0 || body.y > GAME_HEIGHT) {
            entity.addComponent(DeleteEntityComponent.class, new DeleteEntityComponent());
        }
    }

    private void processAsteroidsEdges(BodyComponent body, VelocityComponent velocity) {
        if (body.x - body.w/2f < 0 || body.x + body.w/2f > GAME_WIDTH) {
            velocity.x = -velocity.x;
        }
        if (body.y - body.h/2f < 0 || body.y + body.h/2f > GAME_HEIGHT) {
            velocity.y = -velocity.y;
        }
    }
}
