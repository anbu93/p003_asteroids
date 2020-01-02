package com.vova_cons.hundread_games.asteroids.screens.game_screen;

import com.vova_cons.hundread_games.asteroids.screens.BaseScreen;
import com.vova_cons.hundread_games.asteroids.screens.ScreenType;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.logic.GameSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.view.RendererSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.BodyComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.RotationComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.SpriteComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.VelocityComponent;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by anbu on 19.11.19.
 **/
public class GameScreen extends BaseScreen {
    private GameWorld world;
    private List<GameSystem> systems = new LinkedList<GameSystem>();
    private RendererSystem renderer;

    //region interface
    @Override
    public ScreenType getScreenType() {
        return ScreenType.GameScreen;
    }

    @Override
    public void start() {
        world = new GameWorld();
        world.addEntity()
                .addComponent(BodyComponent.class, new BodyComponent(500,500, 75, 75))
                .addComponent(RotationComponent.class, new RotationComponent(45f))
                .addComponent(VelocityComponent.class, new VelocityComponent())
                .addComponent(SpriteComponent.class, new SpriteComponent(RendererSystem.PLAYER));
        world.addEntity()
                .addComponent(BodyComponent.class, new BodyComponent(100,100, 100, 100))
                .addComponent(VelocityComponent.class, new VelocityComponent())
                .addComponent(SpriteComponent.class, new SpriteComponent(RendererSystem.ASTEROID));
        //input system
        //move system
        //collision system
        //level system
        //render system
        renderer = new RendererSystem(world);
        systems.add(renderer);
        float scale = BaseScreen.HEIGHT / renderer.getHeight();
        renderer.setScale(scale);
        renderer.setPosition(
                BaseScreen.WIDTH/2f - renderer.getWidth()/2f * scale,
                BaseScreen.HEIGHT/2f - renderer.getHeight()/2f * scale);
        this.addActor(renderer);
    }

    @Override
    public void update(float delta) {
        for(GameSystem gameSystem : systems) {
            gameSystem.update(delta, world);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        systems.clear();
    }

    //endregion
}
