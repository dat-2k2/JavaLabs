package ru.spbstu.telematics.java;
import org.apache.commons.cli.*;
import ru.spbstu.telematics.java.lab1.*;

import java.io.File;
import java.io.FileNotFoundException;


public class App
{
	static CommandLineParser parser = new DefaultParser();
	static Options options = new Options();

	// main function
    public static void main(String[] args){

		// Lab 1, using the cli
		try{
			actionLab1(args);
		}
		catch (FileNotFoundException | ParseException e){
			System.out.println(e.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("Usage:", "", options, "", true);
		}
    }

	public static void actionLab1(String[] args) throws ParseException,FileNotFoundException {
		Option ow = new Option("ow", false,"Run lab 1: Overwrite File");
		options.addOption(ow);

		Option owFile = new Option("f", true, "File path to overwrite");
		owFile.setArgs(1);
		options.addOption(owFile);

		Option owData = new Option("d", true, "Data to overwrite");
		owData.setArgs(1);
		options.addOption(owData);


		CommandLine cmd = parser.parse(options,args);
		if (cmd.hasOption(ow)){
			String fileName = cmd.getOptionValue(owFile);
			String data = cmd.getOptionValue(owData);
			if (fileName == null || data == null)
				throw new ParseException("Argument error");
			System.out.println("Overwrite file "+ fileName);
			Lab1.overwriteFile(fileName, data);
		}
	}
}
