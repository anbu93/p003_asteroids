package com.vova_cons.hundread_games.asteroids.screens.game_screen.systems;

import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;

/**
 * Created by vova_cons on 01.01.2020.
 */
public interface GameSystem {
    boolean isWorkingWithPause();
    void update(float delta, GameWorld world);
}
