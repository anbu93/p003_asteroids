package com.vova_cons.hundread_games.tds.services.screens_service;

import com.vova_cons.hundread_games.tds.GameCore;
import com.vova_cons.hundread_games.tds.screens.BaseScreen;
import com.vova_cons.hundread_games.tds.screens.ScreenType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anbu on 27.10.19.
 **/
public class ScreensServiceV1 implements ScreensService {
    private final GameCore game;
    private Map<ScreenType, BaseScreen> screensMap = new HashMap<ScreenType, BaseScreen>();

    public ScreensServiceV1(GameCore game) {
        this.game = game;
    }

    @Override
    public void registerScreen(BaseScreen screen) {
        screensMap.put(screen.getScreenType(), screen);
    }

    @Override
    public BaseScreen getScreen(ScreenType screenType) {
        return screensMap.get(screenType);
    }

    @Override
    public void setScreen(ScreenType screenType) {
        BaseScreen screen = screensMap.get(screenType);
        if (screen != null) {
            game.setScreen(screen);
        }
    }
}
