package com.vova_cons.hundread_games.asteroids.screens.game_screen.world;

import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.GameComponent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vova_cons on 02.01.2020.
 */
public class GameEntity {
    private Map<Class<? extends GameComponent>, GameComponent> componentMap = new HashMap<Class<? extends GameComponent>, GameComponent>();

    public <T extends GameComponent> T getComponent(Class<T> type) {
        GameComponent value = componentMap.get(type);
        return (T) value;
    }

    public boolean isComponentExists(Class<? extends GameComponent> type) {
        return componentMap.containsKey(type) && componentMap.get(type) != null;
    }

    public <T extends GameComponent> GameEntity addComponent(Class<T> type, T component) {
        componentMap.put(type, component);
        return this;
    }

    public GameEntity removeComponent(Class<? extends GameComponent> type) {
        componentMap.remove(type);
        return this;
    }
}
