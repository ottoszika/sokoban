package com.ottoszika.sokoban.logic;

import com.ottoszika.sokoban.entities.GameEntity;
import com.ottoszika.sokoban.utils.Direction;
import com.ottoszika.sokoban.utils.Position;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Heterogeneous level layer containing multiple game element types at a single position.
 */
public class Level {
    /**
     * Level map object.
     */
    private Map<Position, Set<GameEntity>> map;

    /**
     * Level constructor.
     */
    public Level() {
        map = new HashMap<Position, Set<GameEntity>>();
    }

    /**
     * Get the entity by position.
     *
     * @param position the position to get the entity from.
     * @return the entity at the given position.
     */
    public Set<GameEntity> getEntitiesByPosition(Position position) {
        return map.get(position);
    }

    /**
     * Get the nearby position by direction.
     *
     * @param position the reference position.
     * @param direction the direction to go.
     * @return the newly obtained position.
     */
    public static Position getNearbyPosition(Position position, Direction direction) {
        Position nearbyPosition = new Position(position);

        // Check for each direction
        switch (direction) {
            case LEFT:
                nearbyPosition.setX(nearbyPosition.getX() - 1);
                break;
            case UP:
                nearbyPosition.setY(nearbyPosition.getY() - 1);
                break;
            case RIGHT:
                nearbyPosition.setX(nearbyPosition.getX() + 1);
                break;
            case DOWN:
                nearbyPosition.setY(nearbyPosition.getY() + 1);
                break;
        }

        return nearbyPosition;
    }

    /**
     * Get nearby entity by direction.
     *
     * @param position the reference position.
     * @param direction the direction to go.
     * @return the entity located at the specified direction from the reference position.
     */
    public Set<GameEntity> getNearbyEntities(Position position, Direction direction) {
        return getEntitiesByPosition(getNearbyPosition(position, direction));
    }

    /**
     * Check if the layer contains a position.
     *
     * @param position the position to look for.
     * @return the existence of the position.
     */
    public boolean exists(Position position) {
        return map.containsKey(position);
    }

    /**
     * Clear the layer.
     */
    public void clear() {
        this.map.clear();
    }

    /**
     * Clear entities from a specific position.
     *
     * @param position the position to clear.
     */
    public void clear(Position position) {
        this.map.remove(position);
    }

    /**
     * Add an entity to layer.
     *
     * @param entity the entity to be added.
     */
    public void add(GameEntity entity) {
        if (exists(entity.getPosition())) {
            map.get(entity.getPosition()).add(entity);
        } else {
            Set<GameEntity> gameEntitySet = new HashSet<GameEntity>();
            gameEntitySet.add(entity);
            map.put(entity.getPosition(), gameEntitySet);
        }
    }

    /**
     * Remove game entity.
     *
     * @param entity the entity to be removed.
     */
    public void remove(GameEntity entity) {
        if (exists(entity.getPosition())) {
            this.map.get(entity.getPosition()).remove(entity);
        }
    }

    /**
     * Move an entity to a position.
     *
     * @param entity the entity to be moved.
     * @param position the position where to move the entity.
     * @return the movement success.
     */
    public boolean move(GameEntity entity, Position position) {
        if (!exists(entity.getPosition())) {
            return false;
        }

        remove(entity);
        entity.setPosition(position);
        add(entity);

        return true;
    }

    /**
     * Move an entity to a direction.
     *
     * @param entity the entity to be moved.
     * @param direction the direction where to move the entity.
     * @return the movement success.
     */
    public boolean move(GameEntity entity, Direction direction) {
        return move(entity, getNearbyPosition(entity.getPosition(), direction));
    }

    /**
     * Get map.
     *
     * @return the map.
     */
    public Map<Position, Set<GameEntity>> getMap() {
        return map;
    }

    /**
     * Set map.
     *
     * @param map the map to be set.
     */
    public void setMap(Map<Position, Set<GameEntity>> map) {
        this.map = map;
    }
}
