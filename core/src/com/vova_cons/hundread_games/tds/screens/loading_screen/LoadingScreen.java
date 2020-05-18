package com.vova_cons.hundread_games.tds.screens.loading_screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.vova_cons.hundread_games.tds.screens.ScreenType;
import com.vova_cons.hundread_games.tds.utils.ViewUtils;
import com.vova_cons.hundread_games.tds.screens.BaseScreen;
import com.vova_cons.hundread_games.tds.services.ServiceLocator;
import com.vova_cons.hundread_games.tds.services.assets_service.AssetsService;
import com.vova_cons.hundread_games.tds.services.fonts_service.FontsService;
import com.vova_cons.hundread_games.tds.services.screens_service.ScreensService;

/**
 * Created by anbu on 19.11.19.
 **/
public class LoadingScreen extends BaseScreen {
    private AssetsService assetsService;

    @Override
    public ScreenType getScreenType() {
        return ScreenType.LoadingScreen;
    }

    @Override
    public void start() {
        assetsService = ServiceLocator.getService(AssetsService.class);
        assetsService.loadTextures();
        Label label = ViewUtils.createLabel("Идет загрузка...", FontsService.Size.Big, Color.WHITE);
        label.setPosition(WIDTH/2f, HEIGHT/2f, Align.center);
        this.addActor(label);
    }

    @Override
    public void update(float delta) {
        assetsService.update(delta);
        if (assetsService.isFinishLoading()) {
            ScreensService screensService = ServiceLocator.getService(ScreensService.class);
            screensService.setScreen(ScreenType.MenuScreen);
        }
    }
}
