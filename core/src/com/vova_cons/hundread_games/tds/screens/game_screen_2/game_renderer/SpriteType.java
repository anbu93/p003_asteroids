package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_renderer;

/**
 * Created by anbu on 17.05.20.
 **/
public enum SpriteType {
    PlayerSprite("textures/player.png"),
    WallSprite("textures/wall.jpg");

    public String path;

    SpriteType(String path) {
        this.path = path;
    }
}
