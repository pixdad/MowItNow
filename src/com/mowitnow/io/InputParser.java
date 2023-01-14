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
        try {
            line = reader.readLine();
            String[] size = line.split(" ");
            xMax = Integer.parseInt(size[0]);
            yMax = Integer.parseInt(size[1]);

        } catch (IOException e) {
            throw new IOException("Input file does not follow the proper text format for the lawn size definition");
        }

        //2. For each couple of two lines, parse and create a Mow object
        String posLine, commandLine;
        try {
            while((posLine = reader.readLine()) != null && (commandLine = reader.readLine()) != null) {
                String[] pos = posLine.split(" ");
                Mow mow = new Mow(xMax, yMax, Integer.parseInt(pos[0]), Integer.parseInt(pos[1]), Direction.valueOf(pos[2]) );
                for(char commandStr : commandLine.toCharArray()) {
                    mow.addCommand(Command.valueOf(String.valueOf(commandStr)));
                }
                mows.add(mow);
            }
        } catch (IOException e) {
            throw new IOException("Input file does not follow the proper text format for the Mow configuration");
        }


        return mows;
    }

}
