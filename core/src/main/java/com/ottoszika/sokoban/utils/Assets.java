package com.ottoszika.sokoban.utils;

public class Assets {
    public static final String SPRITESHEET_ATLAS = "spritesheets/sokoban_spritesheet.atlas";
    public static final String LEVEL_PREFIX = "levels/level_";
    public static final String LEVEL_SUFFIX = ".json";

    public static String getLevel(int levelNumber) {
        return LEVEL_PREFIX + levelNumber + LEVEL_SUFFIX;
    }
}
