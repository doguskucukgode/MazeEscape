package com.mex.mazeescape.main;

import com.mex.mazeescape.algorithm.DfsBacktrackingMazeEscapeAlgorithm;
import com.mex.mazeescape.input.MazeAnalyzer;
import com.mex.mazeescape.model.MazeIndex;
import com.mex.mazeescape.projector.PngCreator;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
        DfsBacktrackingMazeEscapeAlgorithm dfsBacktrackingMazeEscapeAlgorithm = new DfsBacktrackingMazeEscapeAlgorithm();
        List<MazeIndex> mazeIndices = dfsBacktrackingMazeEscapeAlgorithm.solveMaze(mazeAnalyzer.getMaze(),
                mazeAnalyzer.getStartX(), mazeAnalyzer.getStartY());
        PngCreator pngCreator = new PngCreator(image, mazeAnalyzer.convertToImageIndex(mazeIndices));
        pngCreator.exportImage();
    }
}
