package com.mowitnow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

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
    void main() throws IOException {
        Path filePath = tmpFolder.resolve("mowConfig.txt");
        Files.write(filePath, CONTENT_1);

        Main.main(new String[] {filePath.toString()});


    }
}