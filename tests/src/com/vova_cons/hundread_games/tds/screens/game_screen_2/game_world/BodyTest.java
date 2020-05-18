package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world;

import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Body;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by anbu on 18.05.20.
 **/
public class BodyTest {
    private Body body;
    private Body wall;

    @Before
    public void setUp() {
        body = new Body(0, 0, 100, 100);
        wall = new Body(0, 0, 500, 500);
    }

    @Test
    public void collisionHalfLeftTest() {
        body.x = -body.w/2f;
        body.y = wall.h/2f - body.h/2f;
        Assert.assertTrue(body.collision(wall));
    }

    @Test
    public void collisionFullLeftTest() {
        body.x = 0;
        body.y = wall.h/2f - body.h/2f;
        Assert.assertTrue(body.collision(wall));
    }

    @Test
    public void collisionEdgeLeftTest() {
        body.x = -body.w;
        body.y = wall.h/2f - body.h/2f;
        Assert.assertFalse(body.collision(wall));
    }

    @Test
    public void collisionCornerUpLeftTest() {
        body.x = -body.w;
        body.y = wall.h;
        Assert.assertFalse(body.collision(wall));
    }

    @Test
    public void notCollisionLeftTest() {
        body.x = -body.w - 1f;
        body.y = wall.h/2f - body.h/2f;
        Assert.assertFalse(body.collision(wall));
    }

    @Test
    public void collisionInsideTest() {
        body.x = wall.h/2f - body.w/2f;
        body.y = wall.h/2f - body.h/2f;
        Assert.assertTrue(body.collision(wall));
    }
}
