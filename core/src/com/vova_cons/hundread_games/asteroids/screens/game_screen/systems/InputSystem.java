package com.vova_cons.hundread_games.asteroids.screens.game_screen.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameEntity;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.BodyComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.BulletComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.GameComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.PlayerComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.RotationComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.ShotComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.SpriteComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.VelocityComponent;

import static com.vova_cons.hundread_games.asteroids.screens.game_screen.GameBalance.*;

/**
 * Created by vova_cons on 02.01.2020.
 */
public class InputSystem implements GameSystem {
    private int rotationDir = 0;
    private int moveDir = 0;
    private boolean isShot = false;
    private Vector2 vector = new Vector2();

    @Override
    public void update(float delta, GameWorld world) {
        updateInputs();
        for(GameEntity player : world.getEntitiesFilterAt(PlayerComponent.class)) {
            applyInputs(delta, player);
        }
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
        isShot = Gdx.input.isKeyJustPressed(Input.Keys.SPACE);
    }

    private boolean isPressedKey(int... keys) {
        for(int key : keys) {
            if (Gdx.input.isKeyPressed(key)) {
                return true;
            }
        }
        return false;
    }

    private void applyInputs(float delta, GameEntity player) {
        updateInputVector(delta, player);
        applyPlayerVelocity(player);
        if (isShot) {
            doPlayerShot(player);
        }
    }

    private void updateInputVector(float delta, GameEntity player) {
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
    }

    private void applyPlayerVelocity(GameEntity player) {
        VelocityComponent velocity = player.getComponent(VelocityComponent.class);
        velocity.addForce(vector.x, vector.y);
        vector.x = velocity.x;
        vector.y = velocity.y;
        if (vector.len() > PLAYER_MAX_SPEED) {
            vector.setLength(PLAYER_MAX_SPEED);
            velocity.apply(vector.x, vector.y);
        }
    }

    private void doPlayerShot(GameEntity player) {
        player.addComponent(ShotComponent.class, new ShotComponent());
    }
}
