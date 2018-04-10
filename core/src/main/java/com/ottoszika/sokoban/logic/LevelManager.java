package com.ottoszika.sokoban.logic;

import com.ottoszika.sokoban.entities.GameEntity;
import com.ottoszika.sokoban.contracts.Collidable;
import com.ottoszika.sokoban.contracts.LevelEventListener;
import com.ottoszika.sokoban.utils.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles level's business logic.
 */
public class LevelManager {
    /**
     * Game level.
     */
    private Level level;

    /**
     * Level listeners.
     */
    private List<LevelEventListener> levelEventListeners;

    /**
     * Game manager constructor.
     *
     * @param level the level to manage.
     */
    public LevelManager(Level level) {
        this.levelEventListeners = new ArrayList<LevelEventListener>();
        this.level = level;
    }

    /**
     * Check if an entity can be moved.
     *
     * @param entity the entity to be checked.
     * @param direction the direction where to check the movement.
     * @return the movement possibility.
     */
    public boolean canMove(GameEntity entity, Direction direction) {
        // Only Collidable objects can be moved
        if (!(entity instanceof Collidable)) {
            return false;
        }

        GameEntity nearByEntity = null;
        Set<GameEntity> nearByEntities = level.getNearbyEntities(entity.getGridPosition(), direction);

        // If there were no nearby entities (no obstacle)
        // then the entity can be moved
        if (nearByEntities == null) {
            return true;
        }

        // Looking for another collidable entity
        for (GameEntity gameEntity : nearByEntities) {
            if (gameEntity instanceof Collidable) {
                nearByEntity = gameEntity;
                break;
            }
        }

        // Check if we found a nearby collidable entity
        if (nearByEntity == null) {
            return true;
        }

        // We can move the object only if the obstacle's mass
        // is lower than our object we want to move
        if (((Collidable) nearByEntity).getMass() >= ((Collidable) entity).getMass()) {
            return false;
        }

        // Checking forward for the obstacle's obstacle,
        // so we will call this method recursively
        return canMove(nearByEntity, direction);
    }

    /**
     * Move an entity to a direction.
     *
     * @param entity the entity to be moved.
     * @param direction the direction where to move the entity.
     * @return the movement success.
     */
    public boolean move(GameEntity entity, Direction direction) {
        // Check if the entity can be moved
        if (!canMove(entity, direction)) {
            return false;
        }

        // Move the collidable
        GameEntity nearByEntity = entity;
        boolean foundNearByEntities = false;
        do {
            // Get nearby entities
            Set<GameEntity> nearByEntities = level.getNearbyEntities(nearByEntity.getGridPosition(), direction);

            // Exit when there were no nearby entities
            if (nearByEntities == null) {
                break;
            }

            // Get nearby entity
            for (GameEntity gameEntity : nearByEntities) {
                if (gameEntity instanceof Collidable) {
                    nearByEntity = gameEntity;
                    foundNearByEntities = true;
                    break;
                }
            }

            // Move nearby entity
            if (foundNearByEntities) {
                level.move(nearByEntity, direction);
                notifyListenersForEntityMoved(nearByEntity, direction);
            }
        } while (!foundNearByEntities);

        // Move the initial entity
        level.move(entity, direction);
        notifyListenersForEntityMoved(entity, direction);

        return true;
    }

    /**
     * Add level event listener.
     *
     * @param levelEventListener the level listener to be added.
     */
    public void addLevelEventListener(LevelEventListener levelEventListener) {
        levelEventListeners.add(levelEventListener);
    }

    /**
     * Notify listeners with 'onEntityMoved' event.
     *
     * @param entity the entity which was moved.
     * @param direction the direction where the entity was moved.
     */
    private void notifyListenersForEntityMoved(GameEntity entity, Direction direction) {
        for (LevelEventListener levelEventListener : levelEventListeners) {
            levelEventListener.onEntityMoved(entity, direction);
        }
    }
}
