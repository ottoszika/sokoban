package com.ottoszika.sokoban.contracts;

import com.ottoszika.sokoban.entities.GameEntity;
import com.ottoszika.sokoban.utils.Direction;

/**
 * Classes implementing this can handle events from the level manager.
 */
public interface LevelEventListener {
    /**
     * On entity was moved event.
     *
     * @param entity the entity which was moved.
     * @param direction the direction where the entity was moved.
     */
    void onEntityMoved(GameEntity entity, Direction direction);
}
