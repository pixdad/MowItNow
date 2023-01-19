package com.mowitnow.io;

import com.mowitnow.core.Command;
import com.mowitnow.core.Direction;
import com.mowitnow.core.Mow;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InputParser {

    public static List<Mow> parseFromPath(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line ;
        List<Mow> mows = new ArrayList<>();

        int xMax, yMax;

        //1. Retrieve the size of the lawn
        line = reader.readLine();
        String[] size = line.split(" ");
        try {
            xMax = Integer.parseInt(size[0]);
            yMax = Integer.parseInt(size[1]);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Formating error on lawn size configuration line : \""  + line + "\"");
        }

        //2. For each couple of two lines, parse and create a Mow object
        String posLine, commandLine;
        while((posLine = reader.readLine()) != null && (commandLine = reader.readLine()) != null) {
            String[] pos = posLine.split(" ");
            Mow mow;

            try {
                mow = new Mow(xMax, yMax, Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), Direction.valueOf(pos[2]) );
            }
            catch (IllegalArgumentException | IndexOutOfBoundsException e) {
                throw new IllegalArgumentException("Formating error on mow position configuration line : \""  + posLine + "\"");
            }

            try {
                for (char commandStr : commandLine.toCharArray()) {
                    mow.addCommand(Command.valueOf(String.valueOf(commandStr)));
                }
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Formating error on mow commands configuration line : \""  + commandLine + "\"");
            }

            mows.add(mow);
        }
        return mows;
    }

}
