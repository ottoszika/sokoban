package com.ottoszika.sokoban.utils;

public class Assets {
    public static final int LEVEL_GRID_WIDTH = 64;
    public static final int LEVEL_GRID_HEIGHT = 64;
    public static final String SPRITESHEET_ATLAS = "spritesheets/sokoban_spritesheet.atlas";
    public static final String MOVEMENT_ANIMATIONS = "spritesheets/movement_animations.json";
    public static final String LEVEL_PREFIX = "levels/level_";
    public static final String LEVEL_SUFFIX = ".json";

    public static String getLevel(int levelNumber) {
        return LEVEL_PREFIX + levelNumber + LEVEL_SUFFIX;
    }
}
