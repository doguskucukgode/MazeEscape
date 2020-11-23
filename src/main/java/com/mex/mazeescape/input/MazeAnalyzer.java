package com.mex.mazeescape.input;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MazeAnalyzer {

    private BufferedImage image;
    private int arrayWidth;
    private int arrayHeight;
    private String[][] maze;

    public MazeAnalyzer(BufferedImage image) {
        this.image = image;
    }

    public void generateArrayFromEdges() {
        // Analyze start point and box size
        int boxSize = 0;
        for (int x = 0; x < image.getHeight(); x++) {
            Color originalColor = getColor(image, x, 0);
            if (originalColor.equals(Color.WHITE)) {
                boxSize++;
            }
        }
        // Analyze wall size
        int wallSize = 0;
        for (int x = 0; x < boxSize; x++) {
            Color originalColor = getColor(image,boxSize, x);
            if (originalColor.equals(Color.BLACK)) {
                wallSize++;
            }
        }
        // gathering array width and height
        arrayWidth = ((image.getWidth() - wallSize) / (wallSize + boxSize)) * 2 + 1;
        arrayHeight = ((image.getHeight() - wallSize) / (wallSize + boxSize)) * 2 + 1;
        maze = new String[arrayWidth][arrayHeight];

        boolean horizontalBoxFlip = false;
        boolean verticalBoxFlip = false;
        // constructing the maze array
        for (int y=0, i=0; y<image.getWidth(); i++) {
            for (int x = 0, j = 0; x < image.getHeight(); j++) {
                Color originalColor = new Color(image.getRGB(x, y));
                if(originalColor.equals(Color.BLACK)) {
                    maze[i][j] = "@";
                } else {
                    maze[i][j] = " ";
                }
                if(horizontalBoxFlip) {
                    x += wallSize;
                } else {
                    x += boxSize;
                }
                horizontalBoxFlip = !horizontalBoxFlip;
            }
            if(verticalBoxFlip) {
                y += wallSize;
            } else {
                y += boxSize;
            }
            verticalBoxFlip = !verticalBoxFlip;
        }
    }

    public void printMaze() {
        for (String[] strings : maze) {
            for (String element : strings) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public Color getColor(BufferedImage image, int x, int y) {
        return new Color(image.getRGB(x,y));
    }
}
