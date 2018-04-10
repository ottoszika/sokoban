package com.ottoszika.sokoban.factories;

import com.ottoszika.sokoban.entities.*;

public class GameEntityFactory {
    /**
     * Create a game entity by type.
     *
     * @param type the type to reference a game entity.
     * @return the created game entity.
     */
    public static GameEntity create(String type) {
        if (type.equals("block")) {
            return new Block();
        } else if (type.equals("crate")) {
            return new Crate();
        } else if (type.equals("ground")) {
            return new Ground();
        } else if (type.equals("player")) {
            return new Player();
        } else if (type.equals("storage")) {
            return new Storage();
        }

        return null;
    }
}
