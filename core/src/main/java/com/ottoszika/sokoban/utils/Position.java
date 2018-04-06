package com.ottoszika.sokoban.utils;

import java.util.Objects;

/**
 * Cartesian coordinate system position.
 */
public class Position {
    /**
     * X coordinate.
     */
    private int x;

    /**
     * Y coordinate.
     */
    private int y;

    /**
     * Default constructor.
     */
    public Position() {
    }

    /**
     * Copy constructor.
     *
     * @param position the position to be cloned.
     */
    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
    }

    /**
     * Parametrized constructor.
     *
     * @param x the X position.
     * @param y the Y position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Get X position.
     *
     * @return the X position.
     */
    public int getX() {
        return x;
    }

    /**
     * Set X position.
     *
     * @param x the X position to be set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get Y position.
     *
     * @return the Y position.
     */
    public int getY() {
        return y;
    }

    /**
     * Set Y position.
     *
     * @param y the Y position to be set.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Check if two positions are equals.
     *
     * @param o the other position object.
     * @return the result of equals comparision.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    /**
     * Get hash code.
     *
     * @return the hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Get string representation of the position.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
