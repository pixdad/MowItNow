package com.mowitnow;

import com.mowitnow.core.Direction;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {

    @org.junit.jupiter.api.Test
    void testRotate() {
        assertEquals(Direction.E, Direction.W.rotate(2), "W rotated by 2 should be E");
        assertEquals(Direction.N, Direction.W.rotate(-3), "W rotated by -3 should be N");
    }
}