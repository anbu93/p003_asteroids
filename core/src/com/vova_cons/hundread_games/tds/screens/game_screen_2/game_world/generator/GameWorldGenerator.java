package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.generator;

import com.badlogic.gdx.math.Vector2;
import com.vova_cons.hundread_games.tds.screens.UI;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_renderer.SpriteType;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntity;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntityType;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameWorld;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Body;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.CollisionComponent;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Movement;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.SpriteComponent;
import com.vova_cons.hundread_games.tds.utils.RandomUtils;

import static com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameBalance.*;

/**
 * Created by anbu on 18.05.20.
 **/
public class GameWorldGenerator {
    private static final int EMPTY = 0;
    private static final int PLAYER = 1;
    private static final int WALL = 2;
    private static final int ZOMBIE = 3;
    private static final int TREE = 4;
    private int w, h;
    private int[][] map;
    private int x, y;

    public GameWorld generate() {
        prepareMapSize();
        generateMap();
        printMap();
        return generateWorld();
    }

    private void prepareMapSize() {
        w = (int)(UI.SCENE_WIDE_WIDTH/TILE);
        h = (int)(UI.SCENE_HEIGHT/TILE);
        map = new int[w][h];
    }

    private void generateMap() {
        generateBorders();
        generateEntities(PLAYER, 1);
        generateEntities(WALL, 30);
        generateEntities(ZOMBIE, 5);
        generateEntities(TREE, 10);
    }

    private void generateBorders() {
        for(int x = 0; x < w; x++) {
            map[x][0] = WALL;
            map[x][h-1] = WALL;
        }
        for(int y = 0; y < h; y++) {
            map[0][y] = WALL;
            map[w-1][y] = WALL;
        }
    }

    private void generateEntities(int entity, int count) {
        for(int i = 0; i < count; i++) {
            selectRandomEmptyCell();
            map[x][y] = entity;
        }
    }

    private void selectRandomEmptyCell() {
        while(true) {
            x = RandomUtils.random(1, w-2);
            y = RandomUtils.random(1, h-2);
            if (map[x][y] == EMPTY) {
                return;
            }
        }
    }

    private void printMap() {
        for (int y = h-1; y >= 0; y--) {
            for(int x = 0; x < w; x++) {
                switch(map[x][y]) {
                    case PLAYER: System.out.print("@ "); break;
                    case ZOMBIE: System.out.print("Z "); break;
                    case TREE: System.out.print("* "); break;
                    case WALL: System.out.print("# "); break;
                    case EMPTY: System.out.print("  "); break;
                }
            }
            System.out.println();
        }
    }

    private GameWorld generateWorld() {
        GameWorld world = new GameWorld();
        SpriteType[] treeSprites = new SpriteType[] {SpriteType.TreeSprite1, SpriteType.TreeSprite2, SpriteType.TreeSprite3 };
        Vector2 vector = new Vector2(0, 0);
        for(int x = 0; x < w; x++) {
            for(int y = 0; y < h; y++) {
                switch(map[x][y]) {
                    case PLAYER:
                        GameEntity player = new GameEntity(GameEntityType.Player);
                        player.add(SpriteComponent.class, new SpriteComponent(SpriteType.PlayerSprite, PLAYER_SPRITE_SCALE));
                        player.add(Body.class, new Body(x * TILE + (TILE - UNIT_SIZE)/2f, y * TILE + (TILE - UNIT_SIZE)/2f, UNIT_SIZE, UNIT_SIZE));
                        player.add(Movement.class, new Movement(PLAYER_SPEED));
                        player.add(CollisionComponent.class, new CollisionComponent());
                        world.addEntity(player);
                        break;
                    case ZOMBIE:
                        GameEntity zombie = new GameEntity(GameEntityType.Zombie);
                        zombie.add(SpriteComponent.class, new SpriteComponent(SpriteType.ZombieSprite, ZOMBIE_SPRITE_SCALE));
                        Body zombieBody = new Body(x * TILE + (TILE - UNIT_SIZE) / 2f, y * TILE + (TILE - UNIT_SIZE) / 2f, UNIT_SIZE, UNIT_SIZE);
                        zombieBody.rotation = RandomUtils.random.nextFloat() * 360f;
                        zombie.add(Body.class, zombieBody);
                        Movement zombieMovement = new Movement(ZOMBIE_SPEED);
                        vector.x = 1f;
                        vector.y = 0;
                        vector.setAngle(zombieBody.rotation);
                        zombieMovement.x = vector.x;
                        zombieMovement.y = vector.y;
                        zombie.add(Movement.class, zombieMovement);
                        zombie.add(CollisionComponent.class, new CollisionComponent());
                        world.addEntity(zombie);
                        break;
                    case WALL:
                        GameEntity wall = new GameEntity(GameEntityType.Wall);
                        wall.add(SpriteComponent.class, new SpriteComponent(SpriteType.WallSprite));
                        wall.add(Body.class, new Body(x * TILE, y * TILE, TILE, TILE));
                        wall.add(CollisionComponent.class, new CollisionComponent());
                        world.addEntity(wall);
                        break;
                    case TREE:
                        GameEntity tree = new GameEntity(GameEntityType.Tree);
                        tree.add(SpriteComponent.class, new SpriteComponent(RandomUtils.randomOne(treeSprites), TREE_SPRITE_SCALE, TREE_SPRITE_ALPHA));
                        tree.add(Body.class, new Body(x * TILE, y * TILE, TILE, TILE));
                        world.addEntity(tree);
                        break;
                }
            }
        }
        return world;
    }
}
