package dev.alikhan.game.states;

import dev.alikhan.game.Handler;
import dev.alikhan.game.gfx.Assets;
import dev.alikhan.game.ui.ClickListener;
import dev.alikhan.game.ui.UIImageButton;
import dev.alikhan.game.ui.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        uiManager.addObject(new UIImageButton((float) handler.getWidth() / 2 - 350, (float) handler.getHeight() / 2 - 150, 700, 300, Assets.startButton, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
        g.setColor(Color.red);
        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(), 16, 16);
    }
}
