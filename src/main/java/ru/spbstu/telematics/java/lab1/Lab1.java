package ru.spbstu.telematics.java.lab1;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Lab1{
    	// overwrite a specific file with a buffer

    public static int overwriteFile(String pathName, String buffer){
        try {
            if (new File(pathName).exists() == false){
                throw new FileNotFoundException();
            }
            FileWriter writer = new FileWriter(pathName,false);
            writer.write(buffer);
            writer.close();
            return 0;
        } catch (FileNotFoundException e){
            System.out.println("File not found ");
            return 1;
        } catch (Exception e){
            System.out.println("An error occured");
            e.printStackTrace();
            return -1;
        }
    } 
}