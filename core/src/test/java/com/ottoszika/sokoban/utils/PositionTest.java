package com.ottoszika.sokoban.utils;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class PositionTest {

    @Test
    public void testEquals() {
        Position positionOne = new Position(1, 2);
        Position positionTwo = new Position(2, 1);
        Position positionThree = new Position(2, 1);
        Position positionFour = new Position(0, 0);

        assertEquals(positionOne, positionOne);
        assertNotEquals(positionOne, positionTwo);
        assertNotEquals(positionOne, positionThree);
        assertNotEquals(positionOne, positionFour);
        assertEquals(positionTwo, positionTwo);
        assertEquals(positionTwo, positionThree);
        assertNotEquals(positionTwo, positionFour);
        assertEquals(positionThree, positionThree);
        assertNotEquals(positionThree, positionFour);
        assertEquals(positionFour, positionFour);
    }

    @Test
    public void testHashCode() {
        assertEquals(Objects.hash(1, 2), new Position(1, 2).hashCode());
        assertNotEquals(Objects.hash(2, 1), new Position(1, 2).hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("(1, 2)", new Position(1, 2).toString());
        assertEquals("(2, 1)", new Position(2, 1).toString());
    }
}