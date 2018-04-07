package com.ottoszika.sokoban.entities;

import com.ottoszika.sokoban.utils.Position;

public abstract class GameEntity {
    /**
     * Entity position.
     */
    private Position position;

    /**
     * Get position.
     *
     * @return the entity position.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Set position.
     *
     * @param position the entity position to be set.
     */
    public void setPosition(Position position) {
        this.position = position;
    }
}
