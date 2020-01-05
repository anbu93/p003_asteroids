package com.vova_cons.hundread_games.asteroids.screens.game_screen.systems;

import com.badlogic.gdx.math.Vector2;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameEntity;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.WorldState;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.AsteroidComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.BodyComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.SpriteComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.VelocityComponent;

import java.util.Random;

import static com.vova_cons.hundread_games.asteroids.screens.game_screen.GameBalance.ASTEROID_HEIGHT;
import static com.vova_cons.hundread_games.asteroids.screens.game_screen.GameBalance.ASTEROID_SPEED;
import static com.vova_cons.hundread_games.asteroids.screens.game_screen.GameBalance.ASTEROID_WIDTH;
import static com.vova_cons.hundread_games.asteroids.screens.game_screen.GameBalance.GAME_HEIGHT;
import static com.vova_cons.hundread_games.asteroids.screens.game_screen.GameBalance.GAME_WIDTH;
import static com.vova_cons.hundread_games.asteroids.screens.game_screen.GameBalance.TYPE_ASTEROID;
import static com.vova_cons.hundread_games.asteroids.screens.game_screen.GameBalance.TYPE_PLAYER;
import static com.vova_cons.hundread_games.asteroids.screens.game_screen.GameBalance.detectEntityType;

/**
 * Created by vova_cons on 05.01.2020.
 */
public class LevelSystem implements GameSystem {
    private Vector2 vector = new Vector2();
    private Random random = new Random(System.currentTimeMillis());

    @Override
    public boolean isWorkingWithPause() {
        return false;
    }

    @Override
    public void update(float delta, GameWorld world) {
        if (isPlayerExists(world)) {
            if (!isAsteroidsExists(world)) {
                incrementLevel(world);
                spawnLevelAsteroids(world);
            }
        } else {
            world.setState(WorldState.GameOver);
        }
    }

    private boolean isPlayerExists(GameWorld world) {
        for(GameEntity entity : world.getEntities()) {
            if (detectEntityType(entity) == TYPE_PLAYER) {
                return true;
            }
        }
        return false;
    }

    private boolean isAsteroidsExists(GameWorld world) {
        for(GameEntity entity : world.getEntities()) {
            if (detectEntityType(entity) == TYPE_ASTEROID) {
                return true;
            }
        }
        return false;
    }

    private void incrementLevel(GameWorld world) {
        int level = world.getLevel();
        world.setLevel(level + 1);
    }

    private void spawnLevelAsteroids(GameWorld world) {
        switch (world.getLevel()) {
            case 1:
                spawnAsteroids(1, 0.5f, world);
                break;
            case 2:
                spawnAsteroids(2, 0.5f, world);
                break;
            case 3:
                spawnAsteroids(3, 0.5f, world);
                break;
            case 4:
                spawnAsteroids(3, 0.55f, world);
                break;
            case 5:
                spawnAsteroids(4, 0.65f, world);
                break;
            case 6:
                spawnAsteroids(4, 0.70f, world);
                break;
            case 7:
                spawnAsteroids(4,0.75f, world);
                break;
            case 8:
                spawnAsteroids(5, 0.75f, world);
                break;
            case 9:
                spawnAsteroids(5, 0.80f, world);
                break;
            case 10:
                spawnAsteroids(5, 0.85f, world);
                break;
            case 11:
                spawnAsteroids(5, 0.90f, world);
                break;
            case 12:
                spawnAsteroids(5, 95f, world);
                break;
            case 13:
                spawnAsteroids(6, 85f, world);
                break;
            case 14:
                spawnAsteroids(6, 90f, world);
                break;
            case 15:
                spawnAsteroids(6, 95f, world);
                break;
            case 16:
                spawnAsteroids(6, 95f, world);
                break;
            case 17:
                spawnAsteroids(6, 1f, world);
                break;
            default:
                spawnAsteroids(world.getLevel() - 17 + 6, 1f, world);
                break;
        }
    }

    private void spawnAsteroids(int count, float speedMod, GameWorld world) {
        for(int i = 0; i < count; i++) {
            float x = ASTEROID_WIDTH/2f + random.nextFloat() * (GAME_WIDTH - ASTEROID_WIDTH);
            float y = ASTEROID_HEIGHT/2f + random.nextFloat() * (GAME_HEIGHT - ASTEROID_HEIGHT);
            vector.x = 1f;
            vector.y = 1f;
            vector.setAngle(random.nextFloat() * 360f);
            vector.setLength(ASTEROID_SPEED * speedMod);
            world.addEntity()
                    .addComponent(AsteroidComponent.class, new AsteroidComponent())
                    .addComponent(SpriteComponent.class, new SpriteComponent(RendererSystem.ASTEROID))
                    .addComponent(BodyComponent.class, new BodyComponent(x, y, ASTEROID_WIDTH, ASTEROID_HEIGHT))
                    .addComponent(VelocityComponent.class, new VelocityComponent(vector.x, vector.y));
        }
    }
}
