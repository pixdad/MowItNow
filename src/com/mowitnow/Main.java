package com.mowitnow;

import com.mowitnow.core.Mow;
import com.mowitnow.io.InputParser;
import com.mowitnow.io.OutputGenerator;

import java.io.IOException;
import java.util.List;


public class Main {

    /**
     * Show the final positions of all the mows defined in the configuration file provided.
     * @param args contains the path to the configuration file at index 0.
     */
    public static void main(String[] args) {
        // exit the app without error if no args provided
        if(args.length == 0) {
            System.out.println("No configuration file provided");
            System.out.println("First argument should be the path to the configuration file");
            return;
        }

        /*
        In order to loose coupling, the computation of the mows position is independent from the configuration file and
        the ouput format. The logic for parsing and rendering is contained in InputParser and OutputGenerator classes.
        We could have gone further by using interfaces for both these classes, in order to simply change the implementation.
         */

        List<Mow> mows = null;

        //1. Parse the file into memory :
        String filePath = args[0];
        try {
            mows = InputParser.parseFromPath(filePath);
        } catch (IOException e) {
            System.out.println("First argument should be the path to the configuration file");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            //if parsing error occurs, exit the app with a message
            System.out.println(e.getMessage());
            return;
        }

        //2. Process the commands of each mow, then render the new position
        for (Mow mow : mows) {
            mow.execute();
            OutputGenerator.render(mow);
        }

    }

}
