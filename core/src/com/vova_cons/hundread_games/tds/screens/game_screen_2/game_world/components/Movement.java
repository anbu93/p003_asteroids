package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components;

import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameComponent;

/**
 * Created by anbu on 17.05.20.
 **/
public class Movement extends GameComponent {
    public float x;
    public float y;
    public float speed;

    public Movement(float speed) {
        this.speed = speed;
    }
}
