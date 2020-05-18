package com.vova_cons.hundread_games.tds.screens.game_screen_2.game_renderer;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.Body;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.components.SpriteComponent;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameEntity;
import com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameWorld;
import com.vova_cons.hundread_games.tds.services.ServiceLocator;
import com.vova_cons.hundread_games.tds.services.assets_service.AssetsService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anbu on 17.05.20.
 **/
public class EntitiesRenderer extends Group {
    private com.vova_cons.hundread_games.tds.screens.game_screen_2.game_world.GameWorld world;
    private Map<SpriteType, Sprite> spritesMap = new HashMap<SpriteType, Sprite>();

    public EntitiesRenderer(GameWorld world) {
        this.world = world;
        AssetsService assetsService = ServiceLocator.getService(AssetsService.class);
        for(SpriteType type : SpriteType.values()) {
            spritesMap.put(type, new Sprite(assetsService.getTexture(type.path)));
        }
    }


    @Override
    protected void drawChildren(Batch batch, float parentAlpha) {
        super.drawChildren(batch, parentAlpha);
        for(GameEntity entity : world.entities) {
            drawEntity(entity, batch);
        }
    }

    private void drawEntity(GameEntity entity, Batch batch) {
        Body body = entity.get(Body.class);
        SpriteComponent spriteComponent = entity.get(SpriteComponent.class);
        if (spriteComponent != null && body != null) {
            Sprite sprite = spritesMap.get(spriteComponent.type);
            sprite.setPosition(body.x, body.y);
            sprite.setSize(body.w, body.h);
            sprite.setOrigin(sprite.getWidth()/2f, sprite.getHeight()/2f);
            sprite.setRotation(body.rotation);
            sprite.setScale(spriteComponent.scale);
            sprite.draw(batch);
        }
    }
}
