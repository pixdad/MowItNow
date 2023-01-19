package com.mowitnow;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple end-to-end tests, to see if no exception is thrown and check output messages.
 */
class MainTest {


    @TempDir
    public Path tmpFolder;

    private static List<String> CONTENT_OK = Arrays.asList(
            "5 5",
            "1 2 N",
            "GAGAGAGAA",
            "3 3 E",
            "AADAADADDA"
    );

    private static List<String> CONTENT_KO = Arrays.asList(
            "5 5",
            "1 2 N",
            "GAGAGAXGAA",
            "3 3 E",
            "AADAADADDA"
    );

    //In order to be able to test output messages
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void mainOK() throws IOException {
        Path filePath = tmpFolder.resolve("mowConfig.txt");
        Files.write(filePath, CONTENT_OK);

        Main.main(new String[] {filePath.toString()});
        assertDoesNotThrow( ()->{} );
        assertEquals("1 3 N\n" + "5 1 E\n", outContent.toString());
    }

    @Test
    @DisplayName("Providing wrong format should not throw any exception")
    void mainKO() throws IOException {
        Path filePath = tmpFolder.resolve("mowConfig.txt");
        Files.write(filePath, CONTENT_KO);

        Main.main(new String[] {filePath.toString()});
        assertDoesNotThrow( ()->{} );
        assertEquals("Formating error on mow commands configuration line : \"GAGAGAXGAA\"\n", outContent.toString());
    }

    @Test
    @DisplayName("Not providing file should not throw any exception")
    void mainNoFile() {
        Main.main(new String[] {});
        assertDoesNotThrow( ()->{} );
        assertEquals(
                "No configuration file provided\n"+ "First argument should be the path to the configuration file\n",
                outContent.toString()
        );
    }
}