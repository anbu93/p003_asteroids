package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic;

import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_input.GameInput;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntity;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntityType;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameWorld;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Body;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Movement;
import org.junit.Before;

/**
 * Created by anbu on 18.05.20.
 **/
public class PlayerGameTest extends GameTest {
    protected static final float PLAYER_SPEED = 100f;
    protected GameWorld world;
    protected GameEntity player;
    protected GameInput input;
    protected Body body;
    protected Movement move;

    @Before
    public void setUp() {
        world = new GameWorld();
        player = new GameEntity(GameEntityType.Player);
        player.add(Body.class, new Body(0, 0, 0, 0));
        body = player.get(Body.class);
        player.add(Movement.class, new Movement(PLAYER_SPEED));
        move = player.get(Movement.class);
        world.addEntity(player);
        input = new GameInput();
    }
}
