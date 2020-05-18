package com.vova_cons.hundread_games.tds.screens.game_screen_2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.vova_cons.hundread_games.tds.screens.BaseScreen;
import com.vova_cons.hundread_games.tds.screens.ScreenType;
import com.vova_cons.hundread_games.tds.screens.UI;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_input.GameInput;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.GameLogic;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.systems.CollisionSystem;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.systems.MoveSystem;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_logic.systems.PlayerInputSystem;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_renderer.GameRenderer;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_renderer.SpriteType;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntity;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntityType;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameWorld;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Body;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Movement;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.SpriteComponent;
import com.vova_cons.hundread_games.tds.services.ServiceLocator;
import com.vova_cons.hundread_games.tds.services.screens_service.ScreensService;
import com.vova_cons.hundread_games.tds.utils.RandomUtils;

/**
 * Created by anbu on 17.05.20.
 **/
public class GameScreen2 extends BaseScreen {
    private GameWorld world;
    private GameInput input;
    private GameRenderer renderer;
    private GameLogic logic;

    @Override
    public ScreenType getScreenType() {
        return ScreenType.GameScreen;
    }

    @Override
    public void start() {
        createGameWorld();
        createGameInput();
        createGameRenderer();
        createGameLogic();
    }

    private void createGameWorld() {
        float tileSize = 150;
        world = new GameWorld();
        GameEntity player = new GameEntity(GameEntityType.Player);
        player.add(Body.class, new Body(300, 300, 100, 100));
        player.add(SpriteComponent.class, new SpriteComponent(SpriteType.PlayerSprite, 1.8f));
        player.add(Movement.class, new Movement(300f));
        world.addEntity(player);
        for(int i = 0; i < 10; i++) {
            float x = RandomUtils.random(0, (int)(UI.SCENE_WIDE_WIDTH/tileSize)) * tileSize;
            float y = RandomUtils.random(0, (int)(UI.SCENE_HEIGHT/tileSize)) * tileSize;
            GameEntity wall = new GameEntity(GameEntityType.Wall);
            wall.add(Body.class, new Body(x, y, tileSize, tileSize));
            wall.add(SpriteComponent.class, new SpriteComponent(SpriteType.WallSprite));
            world.addEntity(wall);
        }
    }

    private void createGameInput() {
        input = new GameInput();
    }

    private void createGameRenderer() {
        renderer = new GameRenderer(world, input);
        this.addActor(renderer);
    }

    private void createGameLogic() {
        logic = new GameLogic(world, input);
        logic.addSystem(new PlayerInputSystem());
        logic.addSystem(new CollisionSystem());
        logic.addSystem(new MoveSystem());
    }

    @Override
    public void update(float delta) {
        input.update(delta);
        logic.update(delta);
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            ScreensService screensService = ServiceLocator.getService(ScreensService.class);
            screensService.setScreen(ScreenType.MenuScreen);
        }
    }
}
