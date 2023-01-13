package com.mowitnow;

import com.mowitnow.objects.MowConfigObject;

public class Main {

    public static void main(String[] args) {
        String filePath = args[0];

        //1. Parse the file into memory :
        MowConfigObject input = InputParser.parseFromPath(filePath);

        //2. Compute the mows' positions
        MowResultObject result = MowingCalculator.process(input);

        //3. Show the results
        OutputGenerator.render(result);

    }

}
