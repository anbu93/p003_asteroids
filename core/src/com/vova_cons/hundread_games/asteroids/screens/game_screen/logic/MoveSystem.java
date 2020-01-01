package com.vova_cons.hundread_games.asteroids.screens.game_screen.logic;

import com.badlogic.gdx.math.Vector2;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameObject;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;

/**
 * Created by vova_cons on 02.01.2020.
 */
public class MoveSystem extends GameSystem {
    private Vector2 v = new Vector2();

    public MoveSystem(GameWorld world) {
        super(world);
    }

    @Override
    public void update(float delta) {
        moveObject(world.player, delta);
        world.player.speed -= 250 * delta;
        if (world.player.speed < 0) {
            world.player.speed = 0;
        }
        for(GameObject object : world.asteroids) {
            moveObject(object, delta);
        }
        for(GameObject bullet : world.bullets) {
            moveObject(bullet, delta);
        }
    }

    private void moveObject(GameObject object, float delta) {
        v.x = 1f;
        v.y = 1f;
        v.setAngle(object.body.rotate);
        v.setLength(object.speed * delta);
        object.body.x += v.x;
        object.body.y += v.y;
    }
}
