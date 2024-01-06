package ru.spbstu.telematics.java;

import org.apache.commons.cli.*;
import ru.spbstu.telematics.java.lab1.Lab1;
import ru.spbstu.telematics.java.lab3.*;

import java.io.FileNotFoundException;
import java.util.Random;


public class App {
    static CommandLineParser parser = new DefaultParser();
    static Options options = new Options();

    // main function
    public static void main(String[] args) {
        // Lab 1, using the cli
//        try {
//            actionLab1(args);
//        } catch (FileNotFoundException | ParseException e) {
//            System.out.println(e.getMessage());
//            HelpFormatter formatter = new HelpFormatter();
//            formatter.printHelp("Usage:", "", options, "", true);
//        }

        //Lab3
        actionLab3();

    }

    public static void actionLab1(String[] args) throws ParseException, FileNotFoundException {
        Option ow = new Option("ow", false, "Run lab 1: Overwrite File");
        options.addOption(ow);

        Option owFile = new Option("f", true, "File path to overwrite");
        owFile.setArgs(1);
        options.addOption(owFile);

        Option owData = new Option("d", true, "Data to overwrite");
        owData.setArgs(1);
        options.addOption(owData);

        Option help = new Option("h", false, "Instruction");
        options.addOption(help);

        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption(help)) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("Usage:", "", options, "", true);
        } else if (cmd.hasOption(ow)) {
            String fileName = cmd.getOptionValue(owFile);
            String data = cmd.getOptionValue(owData);
            if (fileName == null || data == null)
                throw new ParseException("Argument error");
            System.out.println("Overwrite file " + fileName);
            Lab1.overwriteFile(fileName, data);
        }
    }

    public static void actionLab3(){
        Cashier cashier = new Cashier();
        cashier.start();
        Random rand = new Random(31);
        //continuously add new customer
        Thread buyerFactory = new Thread() {
            @Override
            public void run() {
                int c = 0;
                while (true){
                    int id = rand.nextInt();
                    if (id > 0){
                        new BraveBuyer("Brave "+id%50, cashier).start();
                        new BraveBuyer("Brave "+(id+1)%50, cashier).start();
                    }
                    else {
                        new HumbleBuyer("Humble "+ (-id)%50, cashier).start();
                        new HumbleBuyer("Humble "+ (-id+1)%50, cashier).start();

                    }
                    synchronized (this) {
                        try {
                            wait(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };

        buyerFactory.start();
    }
}
