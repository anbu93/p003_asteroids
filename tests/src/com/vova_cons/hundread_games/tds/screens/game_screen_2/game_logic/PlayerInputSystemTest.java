package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic;

import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.systems.PlayerInputSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anbu on 18.05.20.
 **/
public class PlayerInputSystemTest extends PlayerGameTest {
    private PlayerInputSystem system;

    @Before
    public void setUp() {
        super.setUp();
        system = new PlayerInputSystem();
        system.setWorld(world);
        system.setInput(input);
    }

    @Test
    public void testMoveUp() {
        input.movement.x = 0f;
        input.movement.y = 1f;
        system.update(1f);
        Assert.assertEquals(move.y, 1, PRECESSION);
    }

    @Test
    public void testMoveDown() {
        input.movement.x = 0f;
        input.movement.y = -1f;
        system.update(1f);
        Assert.assertEquals(move.y, -1, PRECESSION);
    }

    @Test
    public void testMoveRight() {
        input.movement.x = 1f;
        input.movement.y = 0f;
        system.update(1f);
        Assert.assertEquals(move.x, 1, PRECESSION);
    }

    @Test
    public void testMoveLeft() {
        input.movement.x = -1f;
        input.movement.y = 0f;
        system.update(1f);
        Assert.assertEquals(move.x, -1, PRECESSION);
    }

    @Test
    public void testRotation0Angle() {
        input.mouse.x = 100f;
        input.mouse.y = 0f;
        system.update(1f);
        Assert.assertEquals(body.rotation, 0f, PRECESSION);
    }

    @Test
    public void testRotation90Angle() {
        input.mouse.x = 0f;
        input.mouse.y = 100f;
        system.update(1f);
        Assert.assertEquals(body.rotation, 90f, PRECESSION);
    }

    @Test
    public void testRotation180Angle() {
        input.mouse.x = -100f;
        input.mouse.y = 0f;
        system.update(1f);
        Assert.assertEquals(body.rotation, 180f, PRECESSION);
    }

    @Test
    public void testRotation270Angle() {
        input.mouse.x = 0f;
        input.mouse.y = -100f;
        system.update(1f);
        Assert.assertEquals(body.rotation, 270f, PRECESSION);
    }

    @Test
    public void testRotation45Angle() {
        input.mouse.x = 100f;
        input.mouse.y = 100f;
        system.update(1f);
        Assert.assertEquals(body.rotation, 45f, PRECESSION);
    }
}
