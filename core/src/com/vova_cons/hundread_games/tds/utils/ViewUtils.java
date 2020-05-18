package com.vova_cons.hundread_games.tds.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.vova_cons.hundread_games.tds.services.ServiceLocator;
import com.vova_cons.hundread_games.tds.services.fonts_service.FontsService;

/**
 * Created by anbu on 19.11.19.
 **/
public class ViewUtils {
    public static Label createLabel(String text, FontsService.Size size, Color color) {
        FontsService fontsService = ServiceLocator.getService(FontsService.class);
        BitmapFont bitmapFont = fontsService.getFont(size);
        Label.LabelStyle style = new Label.LabelStyle(bitmapFont, color);
        return new Label(text, style);
    }
}
