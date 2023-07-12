package dev.alikhan.game.entities.creatures;

import dev.alikhan.game.Handler;
import dev.alikhan.game.entities.Entity;
import dev.alikhan.game.gfx.Animation;
import dev.alikhan.game.gfx.Assets;
import dev.alikhan.game.inventory.Inventory;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

    private Animation animUp, animLeft, animDown, animRight;
    private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
    private Inventory inventory;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 24;
        bounds.y = 48;
        bounds.width = 16;
        bounds.height = 26;

        animUp = new Animation(250, Assets.player_up);
        animLeft = new Animation(250, Assets.player_left);
        animDown = new Animation(250, Assets.player_down);
        animRight = new Animation(250, Assets.player_right);

        inventory = new Inventory(handler);

    }

    @Override
    public void tick() {

        animDown.tick();
        animUp.tick();
        animLeft.tick();
        animRight.tick();

        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

        checkAttacks();
        inventory.tick();
    }

    private void checkAttacks(){

        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown)
            return;

        if (inventory.isActive())
            return;

        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle ar = new Rectangle();
        int arSize = 20;
        ar.width = arSize;
        ar.height = arSize;

        if (handler.getKeyManager().aUp){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y - arSize;
        } else if (handler.getKeyManager().aDown){
            ar.x = cb.x + cb.width / 2 - arSize / 2;
            ar.y = cb.y + cb.height;
        } else if (handler.getKeyManager().aLeft){
            ar.x = cb.x - arSize;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else if (handler.getKeyManager().aLeft){
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()){
            if (e.equals(this))
                continue;
            if (e.getCollisionBounds(0, 0).intersects(ar)){
                e.hurt(1);
                return;
            }
        }
    }

    @Override
    public void die(){
        System.out.println("You Lose!");
    }

    private void getInput(){
        xMove = 0;
        yMove = 0;

        if(inventory.isActive())
            return;

        if (handler.getKeyManager().up)
            yMove -= speed;
        if (handler.getKeyManager().down)
            yMove += speed;
        if (handler.getKeyManager().left)
            xMove -= speed;
        if (handler.getKeyManager().right)
            xMove += speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), 64, 80,null);

//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()), (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);

    }

    public void postrender(Graphics g){
        inventory.render(g);
    }

    private BufferedImage getCurrentAnimationFrame(){
        if (xMove < 0){
            return animLeft.getCurrentFrame();
        } else if (xMove > 0){
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else if (yMove > 0){
            return animDown.getCurrentFrame();
        } else {
            return animDown.getFrames()[0];
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
