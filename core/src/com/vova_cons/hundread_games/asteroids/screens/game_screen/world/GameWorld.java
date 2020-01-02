package com.vova_cons.hundread_games.asteroids.screens.game_screen.world;

import com.vova_cons.hundread_games.asteroids.utils.ListUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vova_cons on 01.01.2020.
 */
public class GameWorld {
    private List<GameEntity> entities = new LinkedList<GameEntity>();
    private List<GameEntity> reserveList = new LinkedList<GameEntity>();

    public List<GameEntity> getEntities() {
        return entities;
    }

    public GameEntity addEntity() {
        GameEntity entity = new GameEntity();
        entities.add(entity);
        return entity;
    }

    public void removeEntity(GameEntity entity) {
        entities.add(entity);
    }

    public List<GameEntity> getEntitiesFilterAt(final Class<? extends GameComponent> type) {
        reserveList.clear();
        return ListUtils.filter(entities, new ListUtils.Filter<GameEntity>() {
            @Override
            public boolean isApply(GameEntity value) {
                return value.isComponentExists(type);
            }
        }, reserveList);
    }
}
