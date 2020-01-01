package com.vova_cons.hundread_games.asteroids.services.core_service;

import com.vova_cons.hundread_games.asteroids.AsteroidsGame;
import com.vova_cons.hundread_games.asteroids.screens.ScreenType;
import com.vova_cons.hundread_games.asteroids.screens.game_screen.GameScreen;
import com.vova_cons.hundread_games.asteroids.screens.loading_screen.LoadingScreen;
import com.vova_cons.hundread_games.asteroids.screens.menu_screen.MenuScreen;
import com.vova_cons.hundread_games.asteroids.services.ServiceLocator;
import com.vova_cons.hundread_games.asteroids.services.assets_service.AssetsService;
import com.vova_cons.hundread_games.asteroids.services.assets_service.AssetsServiceV1;
import com.vova_cons.hundread_games.asteroids.services.fonts_service.FontsService;
import com.vova_cons.hundread_games.asteroids.services.fonts_service.FontsServiceV1;
import com.vova_cons.hundread_games.asteroids.services.game_speed_service.GameSpeedService;
import com.vova_cons.hundread_games.asteroids.services.game_speed_service.GameSpeedServiceV1;
import com.vova_cons.hundread_games.asteroids.services.screens_service.ScreensService;
import com.vova_cons.hundread_games.asteroids.services.screens_service.ScreensServiceV1;

/**
 * Created by anbu on 19.11.19.
 **/
public class CoreServiceV1 implements CoreService {
    private final AsteroidsGame game;
    private GameSpeedService gameSpeedService;

    public CoreServiceV1(AsteroidsGame asteroidsGame) {
        this.game = asteroidsGame;
    }

    @Override
    public void registerCoreServices() {
        ServiceLocator.register(ScreensService.class, new ScreensServiceV1(game));
        ServiceLocator.register(AssetsService.class, new AssetsServiceV1());
        ServiceLocator.register(FontsService.class, new FontsServiceV1());
        gameSpeedService = new GameSpeedServiceV1(game);
        ServiceLocator.register(GameSpeedService.class, gameSpeedService);
    }

    @Override
    public void registerGameServices() {

    }

    @Override
    public void registerScreens() {
        ScreensService screensService = ServiceLocator.getService(ScreensService.class);
        screensService.registerScreen(new LoadingScreen());
        screensService.registerScreen(new MenuScreen());
        screensService.registerScreen(new GameScreen());
    }

    @Override
    public void startGame() {
        ScreensService screensService = ServiceLocator.getService(ScreensService.class);
        screensService.setScreen(ScreenType.LoadingScreen);
    }

    @Override
    public void update(float delta) {
        gameSpeedService.update(delta);
    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void resumeGame() {

    }
}
