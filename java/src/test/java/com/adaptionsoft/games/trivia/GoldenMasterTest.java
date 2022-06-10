package com.adaptionsoft.games.trivia;


import com.adaptionsoft.games.trivia.runner.GameRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import static com.adaptionsoft.games.trivia.TestResourceUtils.readFile;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoldenMasterTest {

    @Test
    public void test_golden_master() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        PrintStream toRestore = System.out;
        System.setOut(stream);
        String expectedOutput = readFile("testinput");


        String[] arg = {"1"};

        GameRunner.main(arg);

        System.setOut(toRestore);


        Assertions.assertEquals(expectedOutput, outputStream.toString());
    }
}
