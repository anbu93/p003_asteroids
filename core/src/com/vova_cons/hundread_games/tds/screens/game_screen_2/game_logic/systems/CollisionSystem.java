package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.systems;

import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.GameSystem;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntity;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntityType;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Body;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Movement;

/**
 * Created by anbu on 18.05.20.
 **/
public class CollisionSystem extends GameSystem {
    private static final float COLLISION_CHECK_ESTIMATION_STEP = 0.1f;
    private static final float COLLISION_CHECK_ACCURATE_STEP = 0.005f;
    private static final int COLLISION_NOT_FOUND = -1;

    @Override
    public void update(float delta) {
        for(GameEntity entity : world.getEntitiesByType(GameEntityType.Player)) {
            checkCollision(entity, delta);
        }
        for(GameEntity entity : world.getEntitiesByType(GameEntityType.Zombie)) {
            checkCollision(entity, delta);
        }
    }

    private void checkCollision(GameEntity entity, float delta) {
        for(GameEntity wall : world.getEntitiesByType(GameEntityType.Wall)) {
            checkCollision(entity, wall, delta);
        }
    }

    private void checkCollision(GameEntity entity, GameEntity wall, float delta) {
        Body body = entity.get(Body.class);
        Movement move = entity.get(Movement.class);
        Body wallBody = wall.get(Body.class);
        float x = body.x;
        float y = body.y;
        int estimationStep = extractCollisionStep(body, x, y, move, wallBody, COLLISION_CHECK_ESTIMATION_STEP, 0f, delta);
        if (estimationStep != COLLISION_NOT_FOUND) {
            float beginDelta = estimationStep * COLLISION_CHECK_ESTIMATION_STEP;
            int accurateStep = extractCollisionStep(body, x, y, move, wallBody, COLLISION_CHECK_ACCURATE_STEP, beginDelta, delta);
            if (accurateStep != COLLISION_NOT_FOUND) {
                float finishDelta = beginDelta + accurateStep * COLLISION_CHECK_ACCURATE_STEP;
                float progress = finishDelta / delta;
                if (isXAxisCollision(body, x, y, move, wallBody, finishDelta)) {
                    move.x = move.x * progress;
                }
                if (isYAxisCollision(body, x, y, move, wallBody, finishDelta)) {
                    move.y = move.y * progress;
                }
                //System.out.println("Collision step=" + estimationStep + " " + accurateStep);
            }
        }
        body.x = x;
        body.y = y;
    }

    private int extractCollisionStep(Body body, float x, float y, Movement move, Body wall, float stepDelta, float begin, float delta) {
        float currentDelta = begin;
        int step = 0;
        while(currentDelta < delta) {
            step++;
            currentDelta = Math.min(begin +  step * stepDelta, delta);
            body.x = x + move.x * move.speed * currentDelta;
            body.y = y + move.y * move.speed * currentDelta;
            if (body.collision(wall)) {
                return step-1;
            }
        }
        return COLLISION_NOT_FOUND;
    }

    private boolean isXAxisCollision(Body body, float x, float y, Movement move, Body wall, float progressBefore) {
        body.x = x + move.x * move.speed * (progressBefore + COLLISION_CHECK_ACCURATE_STEP);
        body.y = y + move.y * move.speed * progressBefore;
        return body.collision(wall);
    }

    private boolean isYAxisCollision(Body body, float x, float y, Movement move, Body wall, float progressBefore) {
        body.x = x + move.x * move.speed * progressBefore;
        body.y = y + move.y * move.speed * (progressBefore + COLLISION_CHECK_ACCURATE_STEP);
        return body.collision(wall);
    }
}
