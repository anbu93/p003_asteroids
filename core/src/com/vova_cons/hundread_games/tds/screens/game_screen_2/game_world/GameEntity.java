package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world;

import com.vova_cons.hundread_games.tds.utils.LogUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anbu on 17.05.20.
 **/
public class GameEntity {
    private static long uidCounter = 0;
    private final long uid;
    private GameEntityType type;
    private Map<Class<? extends GameComponent>, GameComponent> componentMap = new HashMap<Class<? extends GameComponent>, GameComponent>();

    public GameEntity() {
        uid = uidCounter++;
    }

    public GameEntity(GameEntityType type) {
        this();
        this.type = type;
    }

    public long getUid() {
        return uid;
    }

    public GameEntityType getType() {
        return type;
    }

    public void setType(GameEntityType type) {
        this.type = type;
    }

    public <T extends GameComponent> boolean isExists(Class<T> type) {
        return componentMap.containsKey(type);
    }

    public <T extends GameComponent> void add(Class<T> type, T value) {
        componentMap.put(type, value);
    }

    public <T extends GameComponent> T get(Class<T> type) {
        if (componentMap.containsKey(type)) {
            return (T) componentMap.get(type); // TODO: 17.05.20 добавить обработку исключения при неправильном касте
        }
        LogUtils.error("GameEntity", "not found component " + type.getSimpleName() + " for uid=" + uid + " type=" + this.type);
        return null;
    }

    public <T extends GameComponent> T remove(Class<T> type) {
        if (componentMap.containsKey(type)) {
            T value = get(type);
            componentMap.remove(type);
            return value;
        }
        return null;
    }
}
