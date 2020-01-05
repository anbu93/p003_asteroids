package com.vova_cons.hundread_games.asteroids.screens.game_screen.world;

import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.GameComponent;
import com.vova_cons.hundread_games.asteroids.utils.ListUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by vova_cons on 01.01.2020.
 */
public class GameWorld {
    private WorldState state = WorldState.GameProcess;
    private List<GameEntity> entities = new LinkedList<GameEntity>();
    private List<GameEntity> reserveList = new LinkedList<GameEntity>();
    private int level = 0;

    public WorldState getState() {
        return state;
    }

    public void setState(WorldState state) {
        this.state = state;
    }

    public List<GameEntity> getEntities() {
        return entities;
    }

    public GameEntity addEntity() {
        GameEntity entity = new GameEntity();
        entities.add(entity);
        return entity;
    }

    public void removeEntity(GameEntity entity) {
        entities.remove(entity);
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

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
