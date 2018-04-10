package com.ottoszika.sokoban.loader.definitions;

import java.util.List;

public class LevelDefinition {
    /**
     * Level width.
     */
    private int width;

    /**
     * Level height.
     */
    private int height;

    /**
     * List of all game entities.
     */
    private List<GameEntityDefinition> gameEntityDefinitions;

    /**
     * Get width.
     *
     * @return the level width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set width.
     *
     * @param width the level width to be set.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get height.
     *
     * @return the level height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set height.
     *
     * @param height the level height to be set.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get game entity definitions.
     *
     * @return the list of game entity definitions.
     */
    public List<GameEntityDefinition> getGameEntityDefinitions() {
        return gameEntityDefinitions;
    }

    /**
     * Set game entity definitions.
     *
     * @param gameEntityDefinitions the list of game entity definitions to be set.
     */
    public void setGameEntityDefinitions(List<GameEntityDefinition> gameEntityDefinitions) {
        this.gameEntityDefinitions = gameEntityDefinitions;
    }
}
