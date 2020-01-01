package com.vova_cons.hundread_games.asteroids.screens.game_screen.world;

/**
 * Created by vova_cons on 01.01.2020.
 */
public class Body {
    public float x = 0;
    public float y = 0;
    public float w = 0;
    public float h = 0;
    public float rotate = 0;

    public Body() {
    }

    public Body(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Body(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Body(float x, float y, float w, float h, float rotate) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rotate = rotate;
    }
}
