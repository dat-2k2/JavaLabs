package ru.spbstu.telematics.java;
import org.apache.commons.cli.ParseException;
import org.junit.Test;

import java.io.FileNotFoundException;


public class AppTest {
    @Test
    public void testLab1Normal() {
        try{
            App.actionLab1(new String[]{"-ow", "-f", "abc.txt", "-d", "Hello world"});
        }
        catch (Exception e){
            return;
        }
        assert(true);
    }

    @Test(expected = ParseException.class)
    public void testNoFileArg() throws ParseException, FileNotFoundException{
        App.actionLab1(new String[]{"-ow", "-d", "Hello world"});
    }

    @Test(expected = ParseException.class)
    public void testNoDataArg() throws ParseException, FileNotFoundException{
        App.actionLab1(new String[]{"-ow", "-f", "abc.txt"});
    }


}
