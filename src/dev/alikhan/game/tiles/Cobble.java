package dev.alikhan.game.tiles;

import dev.alikhan.game.gfx.Assets;

public class Cobble extends Tile{

    public Cobble(int id) {
        super(Assets.cobble, id);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
}
