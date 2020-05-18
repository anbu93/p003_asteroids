package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_renderer;

/**
 * Created by anbu on 17.05.20.
 **/
public enum SpriteType {
    PlayerSprite("textures/player.png"),
    WallSprite("textures/wall.jpg"),
    ZombieSprite("textures/zombie.png"),
    TreeSprite1("textures/tree_1.png"),
    TreeSprite2("textures/tree_2.png"),
    TreeSprite3("textures/tree_3.png"),
    ;

    public String path;

    SpriteType(String path) {
        this.path = path;
    }
}
