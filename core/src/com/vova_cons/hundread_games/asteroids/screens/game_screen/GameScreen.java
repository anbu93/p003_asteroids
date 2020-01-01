package com.vova_cons.hundread_games.asteroids.screens.game_screen;

import com.badlogic.gdx.utils.Align;
import com.vova_cons.hundread_games.asteroids.screens.BaseScreen;
import com.vova_cons.hundread_games.asteroids.screens.ScreenType;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.logic.GameSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.logic.InputSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.logic.MoveSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.view.GameRenderer;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameObject;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anbu on 19.11.19.
 **/
public class GameScreen extends BaseScreen {
    private GameWorld world;
    private List<GameSystem> systems = new LinkedList<GameSystem>();
    private GameRenderer renderer;

    //region interface
    @Override
    public ScreenType getScreenType() {
        return ScreenType.GameScreen;
    }

    @Override
    public void start() {
        world = new GameWorld();
        world.player.body.w = 75;
        world.player.body.h = 75;
        world.player.body.x = 500;
        world.player.body.y = 500;
        world.asteroids.add(new GameObject(100, 100, 100, 100));
        world.asteroids.add(new GameObject(750, 750, 75, 75));
        systems.add(new InputSystem(world));
        systems.add(new MoveSystem(world));
        //collision system
        //level system
        renderer = new GameRenderer(world);
        renderer.setScale(BaseScreen.HEIGHT / 1000f);
        renderer.setPosition(BaseScreen.WIDTH/2f - renderer.getWidth() * renderer.getScaleX() * 0.5f, 0);
        this.addActor(renderer);
    }

    @Override
    public void update(float delta) {
        for(GameSystem gameSystem : systems) {
            gameSystem.update(delta);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        systems.clear();
    }

    //endregion
}
