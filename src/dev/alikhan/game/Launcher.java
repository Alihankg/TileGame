package dev.alikhan.game;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Tile Game!", 1920, 1080);
        game.start();
    }
}
