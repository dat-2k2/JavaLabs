package ru.spbstu.telematics.java;
import org.apache.commons.cli.*;
import ru.spbstu.telematics.java.lab1.*;

import java.io.FileNotFoundException;


public class App
{
	// main function
    public static void main(String[] args){

		// Lab 1, using the cli
		CommandLineParser parser = new BasicParser();
		Options options = new Options();

		options.addOption("ow",true,"Run lab 1: Overwrite File");

		try{
			CommandLine cmd = parser.parse(options,args);
			if (cmd.hasOption("ow")){
				try {
					System.out.println("Overwrite file "+ args[1]);
					Lab1.overwriteFile(args[1], args[2]);
				}
				catch (FileNotFoundException e){
					e.printStackTrace();
				}
			}
		}
		catch (ParseException e){
			e.printStackTrace();
		}
    }

}
