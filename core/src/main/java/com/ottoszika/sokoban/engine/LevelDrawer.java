package com.ottoszika.sokoban.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ottoszika.sokoban.contracts.MovementAnimation;
import com.ottoszika.sokoban.entities.*;
import com.ottoszika.sokoban.logic.Level;
import com.ottoszika.sokoban.utils.Assets;
import com.ottoszika.sokoban.utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LevelDrawer {
    /**
     * Level object.
     */
    private Level level;

    /**
     * Ordered game entity list.
     */
    private List<GameEntity> orderedGameEntities;

    /**
     * State time.
     */
    private float stateTime = 0;

    /**
     * Level drawer constructor.
     *
     * @param level the level to be drawn.
     */
    public LevelDrawer(Level level) {
        this.level = level;
        this.orderedGameEntities = generateOrderedGameEntities();
    }

    /**
     * Generate an ordered game entity list.
     * Using this ordered list we can handle sprites Z position.
     *
     * @return the ordered game entity list.
     */
    protected List<GameEntity> generateOrderedGameEntities() {
        // Create list for each game entity
        List<Block> blocks = new ArrayList<Block>();
        List<Crate> crates = new ArrayList<Crate>();
        List<Ground> grounds = new ArrayList<Ground>();
        List<Player> players = new ArrayList<Player>();
        List<Storage> storages = new ArrayList<Storage>();

        // Separate entities from level in the lists above by class
        for (Set<GameEntity> entities : level.getMap().values()) {
            for (GameEntity entity : entities) {
                if (entity instanceof Block) {
                    blocks.add((Block) entity);
                } else if (entity instanceof Crate) {
                    crates.add((Crate) entity);
                } else if (entity instanceof Ground) {
                    grounds.add((Ground) entity);
                } else if (entity instanceof Player) {
                    players.add((Player) entity);
                } else if (entity instanceof Storage) {
                    storages.add((Storage) entity);
                }
            }
        }

        // Create ordered entities list
        List<GameEntity> entities = new ArrayList<GameEntity>();
        entities.addAll(grounds);
        entities.addAll(storages);
        entities.addAll(crates);
        entities.addAll(players);
        entities.addAll(blocks);

        return entities;
    }

    /**
     * Draw the level.
     *
     * @param spriteBatch the sprite batch object.
     */
    public void draw(SpriteBatch spriteBatch) {
        stateTime += Gdx.graphics.getDeltaTime();

        for (GameEntity entity : orderedGameEntities) {
            // Entities with movement animations should be rendered by animation frame
            // Also we will draw the animation only if the entity was moved
            if (entity instanceof MovementAnimation && isEntityMoved(entity)) {
                spriteBatch.draw(((MovementAnimation) entity)
                        .getCurrentMovementAnimation()
                        .getKeyFrame(stateTime, true), entity.getX(), entity.getY());
            } else {
                // Normal entities will be drawn directly
                entity.draw(spriteBatch);
            }

            moveEntity(entity);
        }
    }

    /**
     * Check if the entity was moved from the grid position.
     * This is useful to check if an entity is moving.
     *
     * @param entity the entity to check.
     * @return the moved result.
     */
    public boolean isEntityMoved(GameEntity entity) {
        Position targetPosition = getTargetPosition(entity);
        return (int) entity.getX() != targetPosition.getX() || (int) entity.getY() != targetPosition.getY();
    }

    /**
     * Get game entity position based on position in the grid.
     * This represents the position where the game entity should be on the screen.
     *
     * @param entity the entity which target position needs to be calculated.
     * @return the target position.
     */
    private Position getTargetPosition(GameEntity entity) {
        return new Position(
                (int) (entity.getGridPosition().getX() * Assets.LEVEL_GRID_WIDTH
                        + (Assets.LEVEL_GRID_WIDTH - entity.getWidth()) / 2),
                (int) (entity.getGridPosition().getY() * Assets.LEVEL_GRID_HEIGHT
                        + (Assets.LEVEL_GRID_HEIGHT - entity.getHeight()) / 2)
        );
    }

    /**
     * Move entity on screen.
     *
     * @param entity the entity to be moved.
     */
    private void moveEntity(GameEntity entity) {
        // Default delta
        final int dt = 2;

        // Get entity position on screen
        int startX = (int) entity.getX();
        int startY = (int) entity.getY();

        // Get where the entity should be
        Position position = getTargetPosition(entity);

        // Direction deltas
        int dx = 0;
        int dy = 0;

        // Calculate horizontal delta
        if (startX < position.getX()) {
            dx += dt;
        } else if (startX > position.getX()) {
            dx -= dt;
        }

        // Calculate vertical delta
        if (startY < position.getY()) {
            dy += dt;
        } else if (startY > position.getY()) {
            dy -= dt;
        }

        // Set the newly obtained position
        entity.setPosition(startX + dx, startY + dy);
    }

    /**
     * Get level.
     *
     * @return the level.
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Set level.
     *
     * @param level the level to be set.
     */
    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     * Get ordered game entities.
     *
     * @return the ordered game entity list.
     */
    public List<GameEntity> getOrderedGameEntities() {
        return orderedGameEntities;
    }

    /**
     * Set ordered game entities.
     *
     * @param orderedGameEntities the ordered game entity list.
     */
    public void setOrderedGameEntities(List<GameEntity> orderedGameEntities) {
        this.orderedGameEntities = orderedGameEntities;
    }
}
