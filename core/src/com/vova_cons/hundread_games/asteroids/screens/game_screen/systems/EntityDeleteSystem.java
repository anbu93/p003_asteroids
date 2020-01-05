package com.vova_cons.hundread_games.asteroids.screens.game_screen.systems;

import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameEntity;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.DeleteEntityComponent;

/**
 * Created by vova_cons on 05.01.2020.
 */
public class EntityDeleteSystem implements GameSystem {
    @Override
    public void update(float delta, GameWorld world) {
        for(GameEntity entity : world.getEntitiesFilterAt(DeleteEntityComponent.class)) {
            world.removeEntity(entity);
        }
    }
}
