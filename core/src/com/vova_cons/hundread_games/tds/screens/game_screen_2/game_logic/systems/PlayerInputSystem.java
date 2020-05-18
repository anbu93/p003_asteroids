package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.systems;

import com.badlogic.gdx.math.Vector2;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Movement;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.GameSystem;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntity;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntityType;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Body;

/**
 * Created by anbu on 17.05.20.
 **/
public class PlayerInputSystem extends GameSystem {
    private Vector2 vector = new Vector2(0, 0);

    @Override
    public void update(float delta) {
        for(GameEntity entity : world.getEntitiesByType(GameEntityType.Player)) {
            applyPlayerMovement(entity);
        }
    }

    private void applyPlayerMovement(GameEntity entity) {
        Movement move = entity.get(Movement.class);
        move.x = input.movement.x;
        move.y = input.movement.y;
        Body body = entity.get(Body.class);
        vector.x = input.mouse.x - (body.x + body.w/2f);
        vector.y = input.mouse.y - (body.y + body.h/2f);
        body.rotation = vector.angle();
    }
}
