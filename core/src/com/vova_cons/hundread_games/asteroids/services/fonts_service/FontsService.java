package com.vova_cons.hundread_games.asteroids.services.fonts_service;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.vova_cons.hundread_games.asteroids.services.Service;

/**
 * Created by anbu on 23.01.19.
 * Старый класс созданный алексеем.
 */
public interface FontsService extends Service {
    BitmapFont getFont();
    BitmapFont getFont(Size sizeName);
    void dispose();

    enum Size {
        Big(95), Normal(70), Small(50);
        int size;
        Size(int size) {
            this.size = size;
        }
    }
}
