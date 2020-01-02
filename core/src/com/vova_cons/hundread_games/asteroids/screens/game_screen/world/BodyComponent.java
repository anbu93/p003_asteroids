package com.vova_cons.hundread_games.asteroids.screens.game_screen.world;

/**
 * Created by vova_cons on 02.01.2020.
 */
public class BodyComponent extends GameComponent {
    public float x = 0;
    public float y = 0;
    public float w = 0;
    public float h = 0;

    public BodyComponent() {
    }

    public BodyComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public BodyComponent(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void apply(float x, float y, float w, float h) {
        setPosition(x, y);
        setSize(w, h);
    }

    public void moveBy(float dx, float dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(float w, float h) {
        this.w = w;
        this.h = h;
    }
}
