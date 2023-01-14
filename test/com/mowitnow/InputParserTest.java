package com.mowitnow;

import com.mowitnow.core.Mow;
import com.mowitnow.io.InputParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InputParserTest {

    @TempDir
    public Path tmpFolder;

    private static List<String> CONTENT_1 = Arrays.asList(
            "5 5",
            "1 2 N",
            "GAGAGAGAA",
            "3 3 E",
            "AADAADADDA"
    );

    @Test
    void testParseFromPath() throws IOException {
        Path filePath = tmpFolder.resolve("mowConfig.txt");
        Files.write(filePath, CONTENT_1);

        List<Mow> mows = InputParser.parseFromPath(filePath.toString());
        assertEquals(2, mows.size());
    }
}