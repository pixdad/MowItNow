package com.mowitnow;

import com.mowitnow.core.Mow;
import com.mowitnow.io.InputParser;
import com.mowitnow.io.OutputGenerator;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if(args.length == 0) {
            System.out.println("No configuration file provided");
            System.out.println("First argument should be the path to the configuration file");
            return;
        }

        List<Mow> mows = null;

        //1. Parse the file into memory :
        String filePath = args[0];
        try {
            mows = InputParser.parseFromPath(filePath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //2. Process the commands of each mow, then render the new position
        for (Mow mow : mows) {
            mow.execute();
            OutputGenerator.render(mow);
        }

    }

}
