package com.vova_cons.hundread_games.asteroids.screens.game_screen;

import com.vova_cons.hundread_games.asteroids.screens.BaseScreen;
import com.vova_cons.hundread_games.asteroids.screens.ScreenType;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.systems.EntityDeleteSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.systems.GameSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.systems.InputSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.systems.RendererSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.systems.ShotSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.BodyComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.systems.CollisionSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.GameWorld;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.systems.MoveSystem;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.PlayerComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.RotationComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.SpriteComponent;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.world.components.VelocityComponent;

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
                .addComponent(PlayerComponent.class, new PlayerComponent())
                .addComponent(BodyComponent.class, new BodyComponent(500,500, GameBalance.PLAYER_WIDTH, GameBalance.PLAYER_HEIGHT))
                .addComponent(RotationComponent.class, new RotationComponent(45f))
                .addComponent(VelocityComponent.class, new VelocityComponent())
                .addComponent(SpriteComponent.class, new SpriteComponent(RendererSystem.PLAYER));
        world.addEntity()
                .addComponent(BodyComponent.class, new BodyComponent(100,100, 100, 100))
                .addComponent(VelocityComponent.class, new VelocityComponent())
                .addComponent(SpriteComponent.class, new SpriteComponent(RendererSystem.ASTEROID));
        systems.add(new InputSystem());
        systems.add(new ShotSystem());
        systems.add(new MoveSystem());
        systems.add(new CollisionSystem());
        systems.add(new EntityDeleteSystem());
        //level system
        renderer = new RendererSystem(world);
        float scale = BaseScreen.HEIGHT / renderer.getHeight();
        renderer.setScale(scale);
        renderer.setPosition(
                BaseScreen.WIDTH/2f - renderer.getWidth()/2f * scale,
                BaseScreen.HEIGHT/2f - renderer.getHeight()/2f * scale);
        this.addActor(renderer);
        systems.add(renderer);
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
