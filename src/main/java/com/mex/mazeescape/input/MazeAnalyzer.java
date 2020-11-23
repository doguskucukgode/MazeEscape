package com.mex.mazeescape.input;

import com.mex.mazeescape.model.ImageIndex;
import com.mex.mazeescape.model.MazeIndex;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class MazeAnalyzer {

    private BufferedImage image;
    private int arrayWidth;
    private int arrayHeight;
    private int startX;
    private int startY;
    private int boxSize;
    private int wallSize;
    private String[][] maze;

    public MazeAnalyzer(BufferedImage image) {
        this.image = image;
    }

    public void generateArrayFromEdges() {
        // Analyze start point and box size
        boxSize = 0;
        for (int x = 0; x < image.getHeight(); x++) {
            Color originalColor = getColor(image, x, 0);
            if (originalColor.equals(Color.WHITE)) {
                boxSize++;
            }
        }
        // Analyze wall size
        wallSize = 0;
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
        // locate start point
        for (int i = 0; i < maze[0].length; i++) {
            if(" ".equals(maze[0][i])) {
                startY = i;
                break;
            }
        }
    }

    public List<ImageIndex> convertToImageIndex(List<MazeIndex> list) {
        List<ImageIndex> result = new ArrayList<>();
        list.forEach(m -> result.add(calculateImageIndex(m)));
        return result;
    }

    public ImageIndex calculateImageIndex(MazeIndex mazeIndex) {

        int startY = mazeIndex.getY()%2 == 0
                ? (boxSize + wallSize) / 2 * mazeIndex.getY()
                : (boxSize + wallSize) / 2 * (mazeIndex.getY() - 1) + wallSize;
        int startX = mazeIndex.getX()%2 == 0
                ? (boxSize + wallSize) / 2 * mazeIndex.getX()
                : (boxSize + wallSize) / 2 * (mazeIndex.getX() - 1) + wallSize;
        int endY = mazeIndex.getY()%2 == 0
                ? startY + wallSize - 1
                : startY +  boxSize- 1;
        int endX = mazeIndex.getX()%2 == 0
                ? startX + wallSize - 1
                : startX + boxSize - 1;
        return new ImageIndex(startX, endX, startY, endY);

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

    public String[][] getMaze() {
        return maze;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
}
