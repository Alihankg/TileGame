package dev.alikhan.game.entities.statics;

import dev.alikhan.game.Handler;
import dev.alikhan.game.entities.Entity;

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }
}
