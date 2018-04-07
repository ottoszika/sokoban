package com.ottoszika.sokoban.entities;

import com.ottoszika.sokoban.contracts.Collidable;

public class Block extends GameEntity implements Collidable {
    /**
     * Mass value.
     */
    private static final int MASS = 3;

    /**
     * Get mass value.
     *
     * @return the mass value.
     */
    @Override
    public int getMass() {
        return MASS;
    }
}
