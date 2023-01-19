package com.mowitnow;

import com.mowitnow.core.Command;
import com.mowitnow.core.Direction;
import com.mowitnow.core.Mow;
import com.mowitnow.io.OutputGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MowTest {

    //In order to be able to test output messages
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    public Mow mow1, mow2;
    public List<Command> c1, c2;

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        mow1 = new Mow(5, 5, 2, 3, Direction.E);
        mow2 = new Mow(0, 0, 0, 0, Direction.N);

        c1 = Arrays.asList(Command.A, Command.D, Command.A, Command.G, Command.A);
        c2 = Arrays.asList(Command.A, Command.A, Command.A, Command.A, Command.G);
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void execute() {
        mow1.setCommands(c1);
        mow1.execute();

        mow1.setCommands(c2);
        mow2.execute();

        mow2.setCommands(c2);
        mow2.execute();

        OutputGenerator.render(mow1);
        assertEquals("4 2 E\n", outContent.toString());

        outContent.reset();

        OutputGenerator.render(mow2);
        assertEquals("0 0 W\n", outContent.toString());
    }
}