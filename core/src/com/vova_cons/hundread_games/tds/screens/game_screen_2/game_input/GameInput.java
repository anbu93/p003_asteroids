package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.vova_cons.hundread_games.tds.screens.UI;

/**
 * Created by anbu on 17.05.20.
 **/
public class GameInput {
    public static int UP_KEY = Input.Keys.W;
    public static int DOWN_KEY = Input.Keys.S;
    public static int LEFT_KEY = Input.Keys.A;
    public static int RIGHT_KEY = Input.Keys.D;
    public Vector2 movement = new Vector2(0, 0);
    public Vector2 mouse = new Vector2(0, 0);

    public void update(float delta) {
        updateMovementInput();
        updateMouseInput();
    }

    private void updateMovementInput() {
        movement.y = 0;
        if (Gdx.input.isKeyPressed(UP_KEY)) {
            movement.y += 1f;
        }
        if (Gdx.input.isKeyPressed(DOWN_KEY)) {
            movement.y -= 1f;
        }
        movement.x = 0;
        if (Gdx.input.isKeyPressed(RIGHT_KEY)) {
            movement.x += 1f;
        }
        if (Gdx.input.isKeyPressed(LEFT_KEY)) {
            movement.x -= 1f;
        }
    }

    private void updateMouseInput() {
        mouse.x = Gdx.input.getX() / (float)Gdx.graphics.getWidth() * UI.SCENE_WIDE_WIDTH;
        mouse.y = (Gdx.graphics.getHeight() - Gdx.input.getY()) / (float)Gdx.graphics.getHeight() * UI.HEIGHT;
    }
}
