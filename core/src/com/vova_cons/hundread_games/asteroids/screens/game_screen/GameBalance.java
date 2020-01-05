package com.vova_cons.hundread_games.asteroids.screens.game_screen;

import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameEntity;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.AsteroidComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.BulletComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.PlayerComponent;

/**
 * Created by vova_cons on 02.01.2020.
 */
public class GameBalance {
    public static final float GAME_WIDTH = 1000f;
    public static final float GAME_HEIGHT = 1000f;
    public static final float PLAYER_WIDTH = 50f;
    public static final float PLAYER_HEIGHT = 50f;
    public static final float PLAYER_MAX_SPEED = 300f; //meters per seconds
    public static final float PLAYER_ROTATION_SPEED = 135f; //angle per seconds
    public static final float PLAYER_ACCELERATION = 250f; //meters per seconds^2
    public static final float PLAYER_DECELERATION = 150f; //meters per seconds^2
    public static final float BULLET_WIDTH = 10f;
    public static final float BULLET_HEIGHT = 20f;
    public static final float BULLET_SPEED = 500f; //meters per seconds
    public static final float ASTEROID_WIDTH = 75f;
    public static final float ASTEROID_HEIGHT = 75f;
    public static final float ASTEROID_SPEED = 250f; //meters per seconds
    public static final int TYPE_PLAYER = 0;
    public static final int TYPE_ASTEROID = 1;
    public static final int TYPE_BULLET = 2;
    public static final int TYPE_OTHER = 3;

    public static int detectEntityType(GameEntity entity) {
        if (entity.isComponentExists(PlayerComponent.class)) {
            return TYPE_PLAYER;
        }
        if (entity.isComponentExists(BulletComponent.class)) {
            return TYPE_BULLET;
        }
        if (entity.isComponentExists(AsteroidComponent.class)) {
            return TYPE_ASTEROID;
        }
        return TYPE_OTHER;
    }
}
