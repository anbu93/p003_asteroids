package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic;

import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_input.GameInput;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameWorld;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anbu on 17.05.20.
 **/
public class GameLogic {
    private final GameWorld world;
    private final GameInput input;
    private List<GameSystem> systems = new LinkedList<GameSystem>();

    public GameLogic(GameWorld world, GameInput input) {
        this.world = world;
        this.input = input;
    }

    public void addSystem(GameSystem system) {
        system.setInput(input);
        system.setWorld(world);
        systems.add(system);
    }

    public void update(float delta) {
        for(GameSystem system : systems) {
            system.update(delta);
        }
    }
}
