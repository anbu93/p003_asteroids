package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components;

import com.badlogic.gdx.utils.Align;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameComponent;
import com.vova_cons.hundread_games.tds.utils.LogUtils;

/**
 * Created by anbu on 17.05.20.
 **/
public class Body extends GameComponent {
    public float x;
    public float y;
    public float w;
    public float h;
    public float rotation;

    public Body() {
        this(0, 0, 0, 0);
    }

    public Body(float x, float y) {
        this(x, y, 0, 0);
    }

    public Body(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public float getX(int align) {
        if (Align.isLeft(align)) {
            return x;
        }
        if (Align.isRight(align)) {
            return x + w;
        }
        if (Align.isCenterHorizontal(align)) {
            return x + w/2f;
        }
        LogUtils.error("Body", "getX not found correct align=" + align);
        return x;
    }

    public float getY(int align) {
        if (Align.isBottom(align)) {
            return y;
        }
        if (Align.isTop(align)) {
            return y + h;
        }
        if (Align.isCenterVertical(align)) {
            return y + h/2f;
        }
        LogUtils.error("Body", "getY not found correct align=" + align);
        return y;
    }

    public boolean collision(Body other) {
        return Math.abs(getX(Align.center) - other.getX(Align.center)) < w/2f + other.w/2f
                && Math.abs(getY(Align.center) - other.getY(Align.center)) < h/2f + other.h/2f;
    }
}
