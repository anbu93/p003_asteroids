package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic;

import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_input.GameInput;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameWorld;

/**
 * Created by anbu on 17.05.20.
 **/
public abstract class GameSystem {
    protected GameWorld world;
    protected GameInput input;

    public void setWorld(GameWorld world) {
        this.world = world;
    }

    public void setInput(GameInput input) {
        this.input = input;
    }

    public abstract void update(float delta);
}
