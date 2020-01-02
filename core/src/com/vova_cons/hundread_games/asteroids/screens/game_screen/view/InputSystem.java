package com.vova_cons.hundread_games.asteroids.screens.game_screen.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.logic.GameSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameEntity;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.PlayerComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.RotationComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.VelocityComponent;

import static com.vova_cons.hundread_games.asteroids.screens.game_screen.GameBalance.*;

/**
 * Created by vova_cons on 02.01.2020.
 */
public class InputSystem implements GameSystem {
    private int rotationDir = 0;
    private int moveDir = 0;
    private Vector2 vector = new Vector2();

    @Override
    public void update(float delta, GameWorld world) {
        updateInputs();
        for(GameEntity player : world.getEntitiesFilterAt(PlayerComponent.class)) {
            applyInputs(delta, player);
        }
    }

    private void applyInputs(float delta, GameEntity player) {
        RotationComponent rotation = player.getComponent(RotationComponent.class);
        rotation.angle += rotationDir * PLAYER_ROTATION_SPEED * delta;
        vector.x = 1f;
        vector.y = 1f;
        vector.setAngle(rotation.angle);
        switch (moveDir) {
            case 1:
                vector.setLength(PLAYER_ACCELERATION * delta);
                break;
            case 0:
                vector.setLength(0f);
                break;
            case -1:
                vector.setAngle(rotation.angle - 180f);
                vector.setLength(PLAYER_DECELERATION * delta);
                break;
        }
        VelocityComponent velocity = player.getComponent(VelocityComponent.class);
        velocity.addForce(vector.x, vector.y);
    }

    private void updateInputs() {
        moveDir = 0;
        if (isPressedKey(Input.Keys.W, Input.Keys.UP)) {
            moveDir += 1;
        }
        if (isPressedKey(Input.Keys.S, Input.Keys.DOWN)) {
            moveDir -= 1;
        }
        rotationDir = 0;
        if (isPressedKey(Input.Keys.A, Input.Keys.LEFT)) {
            rotationDir += 1;
        }
        if (isPressedKey(Input.Keys.D, Input.Keys.RIGHT)) {
            rotationDir -= 1;
        }
    }

    private boolean isPressedKey(int... keys) {
        for(int key : keys) {
            if (Gdx.input.isKeyPressed(key)) {
                return true;
            }
        }
        return false;
    }
}
