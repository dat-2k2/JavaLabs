package ru.spbstu.telematics.java.lab1;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Lab1{
    	// overwrite a specific file with a buffer

    public static void overwriteFile(String pathName, String buffer){
        try {
            if (new File(pathName).exists() == false){
                throw new FileNotFoundException();
            }
            FileWriter writer = new FileWriter(pathName,false);
            writer.write(buffer);
            writer.close();
        } catch (FileNotFoundException e){
            System.out.println("File not found ");
        } catch (Exception e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    } 
}