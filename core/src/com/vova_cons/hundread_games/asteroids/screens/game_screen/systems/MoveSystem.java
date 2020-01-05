package com.vova_cons.hundread_games.asteroids.screens.game_screen.systems;

import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameEntity;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.BodyComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.VelocityComponent;

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
    }
}
