package ru.spbstu.telematics.java;
import ru.spbstu.telematics.java.lab1.*;

import java.io.FileNotFoundException;


public class App
{
	// main function
    public static void main(String[] args){
		if ("ow".equals(args[0])){
			try{
				Lab1.overwriteFile(args[1], args[2]);
			}
			catch (FileNotFoundException e) {
				System.out.println("File " + args[1] + " not found!");
			}
		}
    }

}
