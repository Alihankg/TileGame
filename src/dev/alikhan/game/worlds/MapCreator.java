package dev.alikhan.game.worlds;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MapCreator {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        int width = 30, height = 30;
        int spawnX = 100, spawnY = 100;
        int grass = 0, dirt = 1, stone = 2, cobble = 3, planks = 4;

        sb.append(width).append(" ").append(height).append("\n");
        sb.append(spawnX).append(" ").append(spawnY).append("\n");

        for (int i = 0; i < width-1; i++) {
            sb.append(cobble).append(" ");
        }
        sb.append(cobble).append("\n");
        for (int i = 0; i < width - 2; i++) {
            sb.append(cobble).append(" ");
            for (int j = 0; j < width - 2; j++) {
                sb.append(grass).append(" ");
            }
            sb.append(cobble).append("\n");
        }
        for (int i = 0; i < width-1; i++) {
            sb.append(cobble).append(" ");
        }
        sb.append(cobble).append("\n");
        try {
            FileWriter fw = new FileWriter("res/worlds/world1.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sb.toString());
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
