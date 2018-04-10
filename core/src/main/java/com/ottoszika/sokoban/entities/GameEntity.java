package com.ottoszika.sokoban.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ottoszika.sokoban.utils.Position;

public abstract class GameEntity extends Sprite {
    /**
     * Entity position.
     */
    private Position position;

    /**
     * Get grid position.
     *
     * @return the entity position.
     */
    public Position getGridPosition() {
        return position;
    }

    /**
     * Set grid position.
     *
     * @param position the entity position to be set.
     */
    public void setGridPosition(Position position) {
        this.position = position;
    }
}
