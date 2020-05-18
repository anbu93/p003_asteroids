package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.systems;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.GameSystem;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameBalance;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntity;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntityType;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Body;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Movement;
import com.vova_cons.hundread_games.tds.utils.RandomUtils;

/**
 * Created by anbu on 18.05.20.
 **/
public class ZombieAiSystem extends GameSystem {
    private Vector2 vector = new Vector2();
    @Override
    public void update(float delta) {
        for(GameEntity zombie : world.getEntitiesByType(GameEntityType.Zombie)) {
            updateLogic(zombie, delta);
        }
    }

    private void updateLogic(GameEntity zombie, float delta) {
        GameEntity player = RandomUtils.randomOne(world.getEntitiesByType(GameEntityType.Player));
        Body zombieBody = zombie.get(Body.class);
        Body playerBody = player.get(Body.class);
        vector.x = playerBody.getX(Align.center) - zombieBody.getX(Align.center);
        vector.y = playerBody.getY(Align.center) - zombieBody.getY(Align.center);
        float angleToPlayer = vector.angle();
        //angle
        zombieBody.rotation = angleToPlayer;
        Movement zombieMovement = zombie.get(Movement.class);
        vector.setLength(1f);
        zombieMovement.x = vector.x;
        zombieMovement.y = vector.y;
    }
}
