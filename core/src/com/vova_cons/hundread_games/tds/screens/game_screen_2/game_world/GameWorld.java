package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by anbu on 17.05.20.
 **/
public class GameWorld {
    public List<GameEntity> entities = new LinkedList<GameEntity>();
    public Map<GameEntityType, List<GameEntity>> entitiesByType = new HashMap<GameEntityType, List<GameEntity>>();

    public GameWorld() {
        for(GameEntityType type : GameEntityType.values()) {
            entitiesByType.put(type, new LinkedList<GameEntity>());
        }
        entitiesByType.put(null, new LinkedList<GameEntity>());
    }

    public List<GameEntity> getEntitiesByType(GameEntityType type) {
        return entitiesByType.get(type);
    }

    public void addEntity(GameEntity entity) {
        entities.add(entity);
        getEntitiesByType(entity.getType()).add(entity);
    }

    public boolean removeEntity(GameEntity entity) {
        boolean value = entities.remove(entity);
        getEntitiesByType(entity.getType()).remove(entity);
        return value;
    }
}
