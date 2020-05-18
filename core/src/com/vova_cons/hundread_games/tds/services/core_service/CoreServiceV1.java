package com.vova_cons.hundread_games.tds.services.core_service;

import com.vova_cons.hundread_games.tds.GameCore;
import com.vova_cons.hundread_games.tds.screens.ScreenType;
import com.vova_cons.hundread_games.tds.screens.loading_screen.LoadingScreen;
import com.vova_cons.hundread_games.tds.screens.menu_screen.MenuScreen;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.GameScreen2;
import com.vova_cons.hundread_games.tds.services.ServiceLocator;
import com.vova_cons.hundread_games.tds.services.assets_service.AssetsService;
import com.vova_cons.hundread_games.tds.services.assets_service.AssetsServiceV1;
import com.vova_cons.hundread_games.tds.services.fonts_service.FontsService;
import com.vova_cons.hundread_games.tds.services.fonts_service.FontsServiceV1;
import com.vova_cons.hundread_games.tds.services.game_speed_service.GameSpeedService;
import com.vova_cons.hundread_games.tds.services.game_speed_service.GameSpeedServiceV1;
import com.vova_cons.hundread_games.tds.services.screens_service.ScreensService;
import com.vova_cons.hundread_games.tds.services.screens_service.ScreensServiceV1;

/**
 * Created by anbu on 19.11.19.
 **/
public class CoreServiceV1 implements CoreService {
    private final GameCore game;
    private com.vova_cons.hundread_games.tds.services.game_speed_service.GameSpeedService gameSpeedService;

    public CoreServiceV1(GameCore gameCore) {
        this.game = gameCore;
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
        screensService.registerScreen(new GameScreen2());
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
