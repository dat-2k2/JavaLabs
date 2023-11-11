package ru.spbstu.telematics.java;
import ru.spbstu.telematics.java.lab1.*;


public class App
{
	// main function
    public static void main(String[] args){
		if ("ow".equals(args[0])){
			Lab1.overwriteFile(args[1], args[2]);		
		}
    }

}
