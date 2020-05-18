package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.vova_cons.hundread_games.tds.screens.UI;
import com.vova_cons.hundread_games.tds.utils.ViewUtils;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_input.GameInput;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameWorld;
import com.vova_cons.hundread_games.tds.services.fonts_service.FontsService;

/**
 * Created by anbu on 17.05.20.
 **/
public class GameRenderer extends Group {
    private final GameWorld world;
    private final GameInput input;

    public GameRenderer(GameWorld world, GameInput input) {
        this.world = world;
        this.input = input;
        createEntitesRenderer();
        createUi();
    }

    private void createEntitesRenderer() {
        EntitiesRenderer entitiesRenderer = new EntitiesRenderer(world);
        this.addActor(entitiesRenderer);
    }

    private void createUi() {
        Label label = ViewUtils.createLabel("Test", FontsService.Size.Normal, Color.RED);
        label.setPosition(UI.SCENE_WIDE_WIDTH/2f, UI.SCENE_HEIGHT, Align.top);
        this.addActor(label);
    }
}
