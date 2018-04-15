package com.ottoszika.sokoban.logic;

import com.ottoszika.sokoban.contracts.Collidable;
import com.ottoszika.sokoban.entities.Crate;
import com.ottoszika.sokoban.entities.GameEntity;
import com.ottoszika.sokoban.entities.Player;
import com.ottoszika.sokoban.entities.Storage;
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
     * Level width.
     */
    private int width;

    /**
     * Level height.
     */
    private int height;

    /**
     * Player reference.
     */
    private Player player;

    /**
     * Level constructor.
     */
    public Level(int width, int height) {
        map = new HashMap<Position, Set<GameEntity>>();
        this.width = width;
        this.height = height;
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
                nearbyPosition.setY(nearbyPosition.getY() + 1);
                break;
            case RIGHT:
                nearbyPosition.setX(nearbyPosition.getX() + 1);
                break;
            case DOWN:
                nearbyPosition.setY(nearbyPosition.getY() - 1);
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
        if (exists(entity.getGridPosition())) {
            map.get(entity.getGridPosition()).add(entity);
        } else {
            Set<GameEntity> gameEntitySet = new HashSet<GameEntity>();
            gameEntitySet.add(entity);
            map.put(entity.getGridPosition(), gameEntitySet);
        }

        // Store player separately for further reference
        if (entity instanceof Player) {
            player = (Player) entity;
        }
    }

    /**
     * Remove game entity.
     *
     * @param entity the entity to be removed.
     */
    public void remove(GameEntity entity) {
        if (exists(entity.getGridPosition())) {
            this.map.get(entity.getGridPosition()).remove(entity);
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
        if (!exists(entity.getGridPosition())) {
            return false;
        }

        remove(entity);
        entity.setGridPosition(position);
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
        return move(entity, getNearbyPosition(entity.getGridPosition(), direction));
    }

    /**
     * Check if the level is solved.
     *
     * @return the solved result.
     */
    public boolean isSolved() {
        // Check if there is an uncovered storage
        for (Set<GameEntity> entities : map.values()) {
            boolean hasCrates = false;
            boolean hasStorage = false;

            for (GameEntity entity : entities) {
                if (entity instanceof Crate) {
                    hasCrates = true;
                }

                if (entity instanceof Storage) {
                    hasStorage = true;
                }
            }

            if (hasStorage && !hasCrates) {
                return false;
            }
        }

        return true;
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

    /**
     * Get level width.
     *
     * @return the level width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set level width.
     *
     * @param width the level width to be set.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get level height.
     *
     * @return the level height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set level height.
     *
     * @param height the level height to be set.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get player.
     *
     * @return the player.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Set player.
     *
     * @param player the player to be set.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Get the string representation of the level.
     *
     * @return the string representation.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Set<GameEntity> entities = getEntitiesByPosition(new Position(j, i));

                // If there are not entities at the selected position
                // we well represent that with a space.
                if (entities == null) {
                    stringBuilder.append(' ');
                    continue;
                }

                // Dominant entity will be the first from the set
                GameEntity dominantEntity = entities.isEmpty() ? null : entities.iterator().next();

                // Collidable entities have higher priority
                // so we will search for them in the set
                for (GameEntity entity : entities) {
                    if (entity instanceof Collidable) {
                        dominantEntity = entity;
                        break;
                    }
                }

                // Append to the builder the first letter of the class name
                if (dominantEntity != null) {
                    stringBuilder.append(dominantEntity.getClass().getSimpleName().charAt(0));
                }
            }

            stringBuilder.append('\n');
        }

        return stringBuilder.toString();
    }
}
