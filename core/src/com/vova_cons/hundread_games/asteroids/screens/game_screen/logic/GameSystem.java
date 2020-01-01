package com.vova_cons.hundread_games.asteroids.screens.game_screen.logic;

import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;

/**
 * Created by vova_cons on 01.01.2020.
 */
public abstract class GameSystem {
    protected GameWorld world;

    public GameSystem(GameWorld world) {
        this.world = world;
    }

    public abstract void update(float delta);
}
