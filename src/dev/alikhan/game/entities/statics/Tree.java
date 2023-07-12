package dev.alikhan.game.entities.statics;

import dev.alikhan.game.Handler;
import dev.alikhan.game.gfx.Assets;
import dev.alikhan.game.items.Item;

import java.awt.*;

public class Tree extends StaticEntity{

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Assets.tree.getWidth(), Assets.tree.getHeight());

        bounds.x = 19;
        bounds.y = 63;
        bounds.width = 28;
        bounds.height = 14;
    }

    @Override
    public void tick() {

    }

    @Override
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), null);
    }

}
