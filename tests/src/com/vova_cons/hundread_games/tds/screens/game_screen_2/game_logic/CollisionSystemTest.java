package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic;

import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.systems.CollisionSystem;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.systems.MoveSystem;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntity;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntityType;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Body;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anbu on 18.05.20.
 **/
public class CollisionSystemTest extends PlayerGameTest {
    private static final float PLAYER_SIZE = 100f;
    private static final float WALL_SIZE = 500f;
    private GameEntity wall;
    private Body wallBody;
    private CollisionSystem system;
    private MoveSystem moveSystem;

    @Before
    public void setUp() {
        super.setUp();
        body.w = PLAYER_SIZE;
        body.h = PLAYER_SIZE;
        wall = new GameEntity(GameEntityType.Wall);
        wall.add(Body.class, new Body(0, 0, WALL_SIZE, WALL_SIZE));
        wallBody = wall.get(Body.class);
        world.addEntity(wall);
        system = new CollisionSystem();
        system.setInput(input);
        system.setWorld(world);
        moveSystem = new MoveSystem();
        moveSystem.setInput(input);
        moveSystem.setWorld(world);
    }

    @Test
    public void testCollisionRight() {
        body.x = -body.w - 50;
        move.x = 1f;
        system.update(1f);
        moveSystem.update(1f);
        Assert.assertEquals(-body.w, body.x, BIG_ESTIMATION_PRECESSION);
    }

    @Test
    public void testCollisionLeft() {
        body.x = wallBody.w + 50;
        move.x = -1f;
        system.update(1f);
        moveSystem.update(1f);
        Assert.assertEquals(wallBody.w, body.x, BIG_ESTIMATION_PRECESSION);
    }

    @Test
    public void testCollisionUp() {
        body.y = -body.h - 50;
        move.y = 1f;
        system.update(1f);
        moveSystem.update(1f);
        Assert.assertEquals(-body.h, body.y, BIG_ESTIMATION_PRECESSION);
    }

    @Test
    public void testCollisionDown() {
        body.y = wallBody.h + 50;
        move.y = -1f;
        system.update(1f);
        moveSystem.update(1f);
        Assert.assertEquals(wallBody.h, body.y, BIG_ESTIMATION_PRECESSION);
    }

    @Test
    public void testNotCollisionNearLeft() {
        body.x = -body.w;
        move.x = -1f;
        system.update(1f);
        moveSystem.update(1f);
        Assert.assertEquals(-body.w - PLAYER_SPEED, body.x, BIG_ESTIMATION_PRECESSION);
    }

    @Test
    public void testNotCollisionNearRight() {
        body.x = wallBody.w;
        move.x = 1f;
        system.update(1f);
        moveSystem.update(1f);
        Assert.assertEquals(wallBody.w + PLAYER_SPEED, body.x, BIG_ESTIMATION_PRECESSION);
    }

    @Test
    public void testNotCollisionNearDown() {
        body.y = -body.h;
        move.y = -1f;
        system.update(1f);
        moveSystem.update(1f);
        Assert.assertEquals(-body.h - PLAYER_SPEED, body.y, BIG_ESTIMATION_PRECESSION);
    }

    @Test
    public void testNotCollisionNearUp() {
        body.y = wallBody.h;
        move.y = 1f;
        system.update(1f);
        moveSystem.update(1f);
        Assert.assertEquals(wallBody.h + PLAYER_SPEED, body.y, BIG_ESTIMATION_PRECESSION);
    }

    @Test
    public void testCollisionRightStrafing() {
        body.x = -body.w - 50;
        move.y = 1f;
        move.x = 1f;
        system.update(1f);
        moveSystem.update(1f);
        Assert.assertEquals(PLAYER_SPEED, body.y, BIG_ESTIMATION_PRECESSION);
    }
}
