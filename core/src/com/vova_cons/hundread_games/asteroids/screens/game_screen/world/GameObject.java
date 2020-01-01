package com.vova_cons.hundread_games.asteroids.screens.game_screen.world;

/**
 * Created by vova_cons on 01.01.2020.
 */
public class GameObject {
    public Body body;
    public float speed;

    public GameObject() {
        body = new Body();
        speed = 0f;
    }

    public GameObject(float x, float y, float w, float h) {
        body = new Body(x, y, w, h);
        speed = 0f;
    }

    public GameObject(float x, float y, float w, float h, float speed) {
        this.body = new Body(x, y, w, h);
        this.speed = speed;
    }

    public GameObject(float x, float y, float w, float h, float rotation, float speed) {
        this.body = new Body(x, y, w, h, rotation);
        this.speed = speed;
    }
}
