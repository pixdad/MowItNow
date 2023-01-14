package com.mowitnow.core;

public enum Direction {
    N, E, S, W;

    /**
     * @param quarter the number of quarter turns to rotate. Positive value means clockwise turn, whereas negative means anticlockwise
     * @return the direction rotated by a number of quarter turns.
     */
    public Direction rotate(final int quarter) {
        final Direction[] values = Direction.values();
        //use Math.floorMod instead of % because we need a positive index
        int indexNewDir = Math.floorMod(this.ordinal() + quarter, values.length);
        return values[indexNewDir];
    }

}
