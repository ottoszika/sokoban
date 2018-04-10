package com.ottoszika.sokoban.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ottoszika.sokoban.entities.*;
import com.ottoszika.sokoban.logic.Level;

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
        for (GameEntity entity : orderedGameEntities) {
            entity.draw(spriteBatch);
        }
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
