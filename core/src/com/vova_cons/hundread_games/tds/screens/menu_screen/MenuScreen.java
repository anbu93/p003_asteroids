package com.vova_cons.hundread_games.tds.screens.menu_screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
public class MenuScreen extends BaseScreen {
    @Override
    public ScreenType getScreenType() {
        return ScreenType.MenuScreen;
    }

    @Override
    public void start() {
        AssetsService assetsService = ServiceLocator.getService(AssetsService.class);
        Image image = new Image(assetsService.getTexture("textures/play.png"));
        image.setColor(Color.WHITE);
        image.setPosition(WIDTH/2f, HEIGHT/2f, Align.center);
        this.addActor(image);
        image.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ScreensService screensService = ServiceLocator.getService(ScreensService.class);
                screensService.setScreen(ScreenType.GameScreen);
            }
        });
        Label gameNameLabel = ViewUtils.createLabel("ECS архитектура в TDS", FontsService.Size.Big, Color.WHITE);
        float h = HEIGHT - image.getY(Align.top);
        gameNameLabel.setPosition(WIDTH/2f, image.getY(Align.top) + h/2f, Align.center);
        this.addActor(gameNameLabel);
    }

    @Override
    public void update(float delta) {}
}
