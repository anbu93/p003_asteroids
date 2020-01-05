package com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components;

/**
 * Created by vova_cons on 02.01.2020.
 */
public class RotationComponent extends GameComponent {
    public float angle = 0f;

    public RotationComponent() {
    }

    public RotationComponent(float angle) {
        this.angle = angle;
    }
}