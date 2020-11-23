package com.mex.mazeescape.main;

import com.mex.mazeescape.config.AppConfig;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;

/**
 * Main class to be run
 */
@Command(
        subcommands = {CommandLine.HelpCommand.class},
        description = "Maze escape application based png image")
public class MazeEscapeApplication implements Runnable {

    @CommandLine.Option(names = {"-i", "--inputFile"},
            description = "Path to input file, default value is: " + AppConfig.DEFAULT_INPUT_FILE
                    + ", file should be in root folder",
            defaultValue = AppConfig.DEFAULT_INPUT_FILE)
    private File inputFile;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new MazeEscapeApplication()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
       MazeEscapeVisualizer mazeEscapeVisualizer = new MazeEscapeVisualizer(inputFile);
        try {
            mazeEscapeVisualizer.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
