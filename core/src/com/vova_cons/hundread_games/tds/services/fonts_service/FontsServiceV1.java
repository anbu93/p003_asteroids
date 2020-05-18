package com.vova_cons.hundread_games.tds.services.fonts_service;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anbu on 21.10.19.
 **/
public class FontsServiceV1 implements FontsService {
    private static final String chars = "1234567890" + "/!@#$%^&*()\\\"'’…,.{}[];:?-+=<>«»_`~–" +
            "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm" +
            "йцукенгшщзхъфывапролджэёячсмитьбюЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЁЯЧСМИТЬБЮ";
    private Map<Size, BitmapFont> fontsMap;

    public FontsServiceV1() {
        fontsMap = generateTypeface("cuprum_regular.ttf");
    }

    private Map<Size, BitmapFont> generateTypeface(String fontFile) {
        Map<Size, BitmapFont> fonts = new HashMap<Size, BitmapFont>();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/" + fontFile));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = chars;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.minFilter = Texture.TextureFilter.Linear;
        for(Size size : Size.values()) {
            parameter.size = size.size;
            fonts.put(size, generator.generateFont(parameter));
        }
        generator.dispose();
        return fonts;
    }

    @Override
    public BitmapFont getFont() {
        return fontsMap.get(Size.Normal);
    }

    @Override
    public BitmapFont getFont(Size sizeName) {
        return fontsMap.get(sizeName);
    }

    @Override
    public void dispose() {
        for(BitmapFont font : fontsMap.values()) {
            font.dispose();
        }
        fontsMap.clear();
    }
}
