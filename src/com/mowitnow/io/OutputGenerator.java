package com.mowitnow.io;

import com.mowitnow.core.Mow;

public class OutputGenerator {
    public static void render(Mow mow) {
        String output = mow.getX() + " " + mow.getY() + " " + mow.getDirection();
        System.out.println(output);
    }
}
