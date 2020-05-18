package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.systems;

import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.GameSystem;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Movement;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntity;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Body;

/**
 * Created by anbu on 18.05.20.
 **/
public class MoveSystem extends GameSystem {
    @Override
    public void update(float delta) {
        for(GameEntity entity : world.entities) {
            if (entity.isExists(Movement.class)) {
                applyMovement(entity, delta);
            }
        }
    }

    private void applyMovement(GameEntity entity, float delta) {
        Body body = entity.get(Body.class);
        Movement move = entity.get(Movement.class);
        body.x += move.x * move.speed * delta;
        body.y += move.y * move.speed * delta; // TODO: 18.05.20 убрать стрейфингф (диагональные движения быстрее)
    }
}
