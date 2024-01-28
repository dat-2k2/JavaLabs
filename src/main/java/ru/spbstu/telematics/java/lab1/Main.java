package ru.spbstu.telematics.java.lab1;

import org.apache.commons.cli.*;

import java.io.FileNotFoundException;

/**
 * Main class to demonstrates result of labs.
 */
public class Main {
    static CommandLineParser parser = new DefaultParser();
    static Options options = new Options();
    static Option owFile = new Option("f", false, "File to be overwritten");
    static Option owData = new Option("d", true, "Data to overwrite");
    static Option help = new Option("h", false, "Help");

    static {
        options.addOption(owFile);
        options.addOption(owData);
        options.addOption(help);
    }

    /**
     * Main method for program, which demonstrates MyFileUtility: Overwrite file.
     *
     * @param args main args
     */
    public static void main(String[] args) {
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption(help)) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("Usage:", "", options, "", true);
            } else if (cmd.hasOption(owFile)) {
                String fileName = cmd.getOptionValue(owFile);
                String data = cmd.getOptionValue(owData);

                if (fileName == null || data == null)
                    throw new ParseException("Argument error");

                System.out.println("Overwrite file " + fileName);
                MyFileUtility.overwriteFile(fileName, data);
            } else {
                throw new ParseException("");
            }
        } catch (FileNotFoundException | ParseException e) {
            System.out.println(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Usage:", "", options, "", true);
        }
    }
}
