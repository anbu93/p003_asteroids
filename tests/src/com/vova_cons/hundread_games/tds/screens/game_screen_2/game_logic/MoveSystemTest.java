package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic;

import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.systems.MoveSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anbu on 18.05.20.
 **/
public class MoveSystemTest extends PlayerGameTest {
    private MoveSystem system;

    @Before
    public void setUp() {
        super.setUp();
        system = new MoveSystem();
        system.setWorld(world);
    }

    @Test
    public void testMoveUp() {
        move.x = 0f;
        move.y = 1f;
        system.update(1f);
        Assert.assertEquals(body.y, PLAYER_SPEED, PRECESSION);
    }

    @Test
    public void testMoveDown() {
        move.x = 0f;
        move.y = -1f;
        system.update(1f);
        Assert.assertEquals(body.y, -PLAYER_SPEED, PRECESSION);
    }

    @Test
    public void testMoveRight() {
        move.x = 1f;
        move.y = 0f;
        system.update(1f);
        Assert.assertEquals(body.x, PLAYER_SPEED, PRECESSION);
    }

    @Test
    public void testMoveLeft() {
        move.x = -1f;
        move.y = 0f;
        system.update(1f);
        Assert.assertEquals(body.x, -PLAYER_SPEED, PRECESSION);
    }

    @Test
    public void testNotMove() {
        move.x = 0f;
        move.y = 0f;
        system.update(1f);
        Assert.assertEquals(body.x, 0f, PRECESSION);
        Assert.assertEquals(body.y, 0f, PRECESSION);
    }
}
