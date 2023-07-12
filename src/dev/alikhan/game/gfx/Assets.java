package dev.alikhan.game.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width = 64, height = 64;

    public static Font font56;

    public static BufferedImage grass, dirt, stone, cobble, tree, planks;
    public static BufferedImage wood;
    public static BufferedImage[] player_up, player_down, player_right, player_left;
    public static BufferedImage[] startButton;
    public static BufferedImage inventoryScreen;

    public static void init(){
        font56 = FontLoader.loadFont("res/ui/fonts/slkscr.ttf", 56);

        // Players
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/players/player2.png"));

        player_up = getPlayerAnimationAtRow(playerSheet, 9, 8);
        player_left = getPlayerAnimationAtRow(playerSheet, 10, 8);
        player_down = getPlayerAnimationAtRow(playerSheet, 11, 8);
        player_right = getPlayerAnimationAtRow(playerSheet, 12, 8);

        // Objects
        tree = ImageLoader.loadImage("/objects/pine-tree.png");

        // Items
        wood = ImageLoader.loadImage("/objects/Wood.png");

        // UI
        startButton = new BufferedImage[2];
        startButton[0] = ImageLoader.loadImage("/ui/buttons/startButton.png");
        startButton[1] = ImageLoader.loadImage("/ui/buttons/startButton_hover.png");

        inventoryScreen = ImageLoader.loadImage("/ui/inventoryScreen.png");

        // Textures
        dirt = ImageLoader.loadImage("/textures/dirt.png");
        grass = ImageLoader.loadImage("/textures/grass.png");
        stone = ImageLoader.loadImage("/textures/stone.png");
        cobble = ImageLoader.loadImage("/textures/cobble.png");
        planks = ImageLoader.loadImage("/textures/planks.png");

    }

    private static BufferedImage[] getPlayerAnimationAtRow(SpriteSheet sheet, int row, int frames){
        BufferedImage[] anim = new BufferedImage[frames];
        for (int i = 0; i < frames; i++) {
            anim[i] = sheet.crop(width * i, height * (row - 1), width, height);
        }
        return anim;
    }

}
