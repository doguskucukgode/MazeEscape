package com.mex.mazeescape.main;

import com.mex.mazeescape.input.MazeAnalyzer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MazeEscapeVisualizer {

    private File inputFile;

    public MazeEscapeVisualizer(File inputFile) {
        this.inputFile = inputFile;
    }

    public void run() throws IOException {
        BufferedImage image = ImageIO.read(inputFile);
        MazeAnalyzer mazeAnalyzer = new MazeAnalyzer(image);
        mazeAnalyzer.generateArrayFromEdges();
        mazeAnalyzer.printMaze();
    }
}
