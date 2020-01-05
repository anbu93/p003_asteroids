package com.vova_cons.hundread_games.asteroids.screens.game_screen.systems;

import com.badlogic.gdx.math.Vector2;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameEntity;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.BodyComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.BulletComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.PlayerComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.RotationComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.ShotComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.SpriteComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.VelocityComponent;

import static com.vova_cons.hundread_games.asteroids.screens.game_screen.GameBalance.*;

/**
 * Created by vova_cons on 05.01.2020.
 */
public class ShotSystem implements GameSystem {
    private Vector2 vector = new Vector2();

    @Override
    public void update(float delta, GameWorld world) {
        for(GameEntity playerEntity : world.getEntitiesFilterAt(PlayerComponent.class)) {
            if (playerEntity.isComponentExists(ShotComponent.class)) {
                doShot(playerEntity, world);
            }
        }
    }

    private void doShot(GameEntity player, GameWorld world) {
        BodyComponent body = player.getComponent(BodyComponent.class);
        RotationComponent rotation = player.getComponent(RotationComponent.class);
        vector.x = 1f;
        vector.y = 1f;
        vector.setAngle(rotation.angle);
        vector.setLength(BULLET_SPEED);
        world.addEntity()
                .addComponent(BulletComponent.class, new BulletComponent())
                .addComponent(SpriteComponent.class, new SpriteComponent(RendererSystem.BULLET))
                .addComponent(BodyComponent.class, new BodyComponent(body.x, body.y, BULLET_WIDTH, BULLET_HEIGHT))
                .addComponent(RotationComponent.class, new RotationComponent(rotation.angle))
                .addComponent(VelocityComponent.class, new VelocityComponent(vector.x, vector.y));
        player.removeComponent(ShotComponent.class);
    }
}
