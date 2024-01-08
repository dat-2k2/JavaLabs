package ru.spbstu.telematics.java;

import org.apache.commons.cli.ParseException;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Test main action for Lab 1 only
 */
public class AppTest {
    File testFile = new File("~tmp.txt");

    /**
     * Test if CLI was handled correctly
     */
    @Test
    public void testLab1Normal() {
        try {
            assert (testFile.createNewFile());
            App.actionLab1(new String[]{"-ow", "-f", testFile.getName(), "-d", "Hello world"});
            assert (true);
        } catch (FileNotFoundException e) {
            System.out.println("Error in overwrite function");
        } catch (IOException e) {
            System.out.println("Create test file " + testFile.getName() + " failed");
        } catch (ParseException e) {
            System.out.println("Error while execute CmdLine");
        } finally {
            assert (testFile.delete());
        }

    }

    @Test(expected = ParseException.class)
    public void testNoFileArg() throws ParseException {
        try {
            assert (testFile.createNewFile());
            App.actionLab1(new String[]{"-ow", "-d", "Hello world"});
        } catch (FileNotFoundException e) {
            System.out.println("Error in overwrite function");
        } catch (IOException e) {
            System.out.println("Create test file " + testFile.getName() + " failed");
        } finally {
            assert (testFile.delete());
        }

    }

    /**
     * Test for the wrong passing argument case
     * @throws ParseException expect to throw this exception
     */
    @Test(expected = ParseException.class)
    public void testNoDataArg() throws ParseException {
        try {
            assert (testFile.createNewFile());
            App.actionLab1(new String[]{"-ow", "-f", "abc.txt"});
        } catch (FileNotFoundException e) {
            System.out.println("Error in overwrite function");
        } catch (IOException e) {
            System.out.println("Create test file " + testFile.getName() + " failed");
        } finally {
            assert (testFile.delete());
        }
    }
}
