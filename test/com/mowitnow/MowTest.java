package com.mowitnow;

import com.mowitnow.core.Command;
import com.mowitnow.core.Direction;
import com.mowitnow.core.Mow;
import com.mowitnow.io.OutputGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class MowTest {

    public Mow mow1, mow2, mow3;
    public List<Command> c1, c2, c3;

    @BeforeEach
    public void setup() {
        mow1 = new Mow(5, 5, 0, 0, Direction.N);
        mow2 = new Mow(5, 5, 2, 3, Direction.E);
        mow3 = new Mow(0, 0, 0, 0, Direction.N);

        c1 = Arrays.asList(Command.A, Command.D, Command.A, Command.G);
        c2 = Arrays.asList(Command.A, Command.A, Command.A, Command.A, Command.G);
    }

    @Test
    void execute() {
        mow1.setCommands(c1);
        mow1.execute();

        mow2.setCommands(c2);
        mow2.execute();

        mow3.setCommands(c2);
        mow3.execute();

        OutputGenerator.render(mow1);
        OutputGenerator.render(mow2);
        OutputGenerator.render(mow3);
    }
}