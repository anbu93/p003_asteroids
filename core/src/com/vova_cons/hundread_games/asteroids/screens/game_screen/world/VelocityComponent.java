package com.vova_cons.hundread_games.asteroids.screens.game_screen.world;

/**
 * Created by vova_cons on 02.01.2020.
 */
public class VelocityComponent extends GameComponent {
    public float x = 0f;
    public float y = 0f;

    public VelocityComponent() {
    }

    public VelocityComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public VelocityComponent apply(float x, float y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public VelocityComponent addForce(float dx, float dy) {
        this.x += dx;
        this.y += dy;
        return this;
    }
}
