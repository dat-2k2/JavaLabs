package ru.spbstu.telematics.java.lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Lab1 {

    /**
     * Overwrite a specific file with a buffer
     *
     * @param pathName to the specific file
     * @param buffer   the string to overwrite
     * @throws FileNotFoundException throw the FileNotFoundException if there isn't any of specified file
     */
    public static void overwriteFile(String pathName, String buffer) throws FileNotFoundException {
        if (!new File(pathName).exists()) {
            throw new FileNotFoundException("File " + pathName + " not found");
        }

        try {
            FileWriter writer = new FileWriter(pathName, false);
            writer.write(buffer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Cannot open file " + pathName);
        }
    }
}