package com.vova_cons.hundread_games.asteroids;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.vova_cons.hundread_games.asteroids.services.ServiceLocator;
import com.vova_cons.hundread_games.asteroids.services.core_service.CoreService;
import com.vova_cons.hundread_games.asteroids.services.core_service.CoreServiceV1;

public class AsteroidsGame extends Game {
	private CoreService coreService;
	private Screen nextScreen = null;
	private float speed = 1f;

	@Override
	public void create () {
		coreService = new CoreServiceV1(this);
		ServiceLocator.register(CoreService.class, coreService);
		coreService.registerCoreServices();
		coreService.registerGameServices();
		coreService.registerScreens();
		coreService.startGame();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		float delta = Gdx.graphics.getDeltaTime() * speed;
		update(delta);
		render(delta);
		performChangeScreen();
	}

	private void update(float delta) {
		coreService.update(delta);
	}

	private void render(float delta) {
		if (screen != null) {
			screen.render(delta);
		}
	}

	private void performChangeScreen() {
		if (nextScreen != null) {
			super.setScreen(nextScreen);
			nextScreen = null;
		}
	}

	@Override
	public void setScreen(Screen screen) {
		this.nextScreen = screen;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}
}
