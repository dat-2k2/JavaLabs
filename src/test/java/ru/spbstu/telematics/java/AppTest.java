package ru.spbstu.telematics.java;
import ru.spbstu.telematics.java.lab1.*;

import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;

/**
 * Unit test for simple App.
 */
public class AppTest {

	@Test //test the main function
	public void testOverWrite(){

		//create new file
		String testPath = new String("tmp.txt");
		File fileTest = new File(testPath);
		try{
			FileWriter writerTest = new FileWriter(testPath);
			//write a text to file
			writerTest.write("this is a test");
			writerTest.close();
		}
		catch(IOException e){
			System.out.println("An error occured. ");
			e.printStackTrace();
		}
		catch(Throwable t){
			System.out.println(t.getMessage());
		}

		//try overwrite
		String overwriteString = new String("this is the overwritten data");
		Lab1.overwriteFile(testPath,overwriteString);

		//prepare a buffer to read the new data
		File tmp = new File(testPath);
		char[] cbuff = new char[(int)tmp.length()];

		// read the new overwritten data
		try{
			FileReader readerTest = new FileReader(testPath); 
			//read text from the file
			readerTest.read(cbuff);
			readerTest.close();
		}
		catch(IOException e){
			System.out.println("An error occured. ");
			e.printStackTrace();
		}
		catch(Throwable t){
			System.out.println(t.getMessage());
		}

		//delete the file
		fileTest.delete();

		//check if the data is overwritten successfully
		assert(overwriteString.equals(new String(cbuff)));
	};


    @Test 	// test if the nonexisted File case is covered. 
    public void testFileNotFound(){
		Lab1.overwriteFile(new String(""), new String(""));
    }

}
