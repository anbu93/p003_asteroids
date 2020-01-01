package com.vova_cons.hundread_games.asteroids.screens.game_screen.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameObject;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;

/**
 * Created by vova_cons on 01.01.2020.
 */
public class InputSystem extends GameSystem {
    public InputSystem(GameWorld world) {
        super(world);
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            world.player.body.rotate += 135 * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            world.player.body.rotate -= 135 * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            world.player.speed += 500f * delta;
            if (world.player.speed > 500f) {
                world.player.speed = 500f;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            world.player.speed -= 150f * delta;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            world.bullets.add(new GameObject(world.player.body.x, world.player.body.y, 50, 25,
                    world.player.body.rotate, 750f));
        }
    }
}
