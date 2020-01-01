package com.vova_cons.hundread_games.asteroids.services.game_speed_service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.vova_cons.hundread_games.asteroids.AsteroidsGame;

/**
 * Created by anbu on 27.10.19.
 **/
public class GameSpeedServiceV1 implements GameSpeedService {
    private final AsteroidsGame core;

    public GameSpeedServiceV1(AsteroidsGame core) {
        this.core = core;
    }

    @Override
    public void update(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F1)) {
            System.out.println("SPEED 1");
            core.setSpeed(1f);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F2)) {
            System.out.println("SPEED 1/2");
            core.setSpeed(0.5f);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F3)) {
            System.out.println("SPEED 1/10");
            core.setSpeed(0.1f);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F4)) {
            System.out.println("SPEED 1/20");
            core.setSpeed(0.05f);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F5)) {
            System.out.println("SPEED 0");
            core.setSpeed(0f);
        }
    }
}
