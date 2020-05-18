package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components;

import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_renderer.SpriteType;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameComponent;

/**
 * Created by anbu on 17.05.20.
 **/
public class SpriteComponent extends GameComponent {
    public SpriteType type;
    public float scale = 1f;
    public float alpha = 1f;

    public SpriteComponent(SpriteType type) {
        this.type = type;
    }

    public SpriteComponent(SpriteType type, float scale) {
        this.type = type;
        this.scale = scale;
    }

    public SpriteComponent(SpriteType type, float scale, float alpha) {
        this.type = type;
        this.scale = scale;
        this.alpha = alpha;
    }
}
