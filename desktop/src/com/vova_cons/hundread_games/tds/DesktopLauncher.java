package com.vova_cons.hundread_games.tds;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.resizable = false;
		config.samples = 8;
		config.title = "TDS " + CoreConfig.version;
		new LwjglApplication(new GameCore(), config);
	}
}
