package com.ottoszika.sokoban.loader.definitions;

public class GameEntityDefinition {
    /**
     * Game entity type name.
     */
    private String type;

    /**
     * X position in grid.
     */
    private int x;

    /**
     * Y position in grid.
     */
    private int y;

    /**
     * Region name.
     * Used to identify which sprite sheet region texture to apply.
     */
    private String regionName;

    /**
     * Get type.
     *
     * @return the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Set type.
     *
     * @param type the type to be set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get X grid position.
     *
     * @return the X position in grid.
     */
    public int getX() {
        return x;
    }

    /**
     * Set X grid position.
     *
     * @param x the X position to be set in grid.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get Y grid position.
     *
     * @return the Y position in grid.
     */
    public int getY() {
        return y;
    }

    /**
     * Set Y grid position.
     *
     * @param y the Y position to be set in grid.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get atlas region name.
     *
     * @return the atlas region name.
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * Set atlas region name.
     *
     * @param regionName the atlas region name to be set.
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
