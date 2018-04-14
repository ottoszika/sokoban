package com.ottoszika.sokoban.engine.input;

import com.badlogic.gdx.input.GestureDetector;
import com.ottoszika.sokoban.engine.GameEngine;
import com.ottoszika.sokoban.utils.Direction;

public class TouchInputProcessor extends GestureDetector.GestureAdapter {
    /**
     * Game engine reference.
     */
    private GameEngine gameEngine;

    /**
     * Keyboard input processor.
     *
     * @param gameEngine the game engine.
     */
    public TouchInputProcessor(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (Math.abs(velocityX) > Math.abs(velocityY)) {
            if (velocityX > 0) {
                gameEngine.movePlayer(Direction.RIGHT);
            } else {
                gameEngine.movePlayer(Direction.LEFT);
            }
        } else {
            if (velocityY > 0) {
                gameEngine.movePlayer(Direction.DOWN);
            } else {
                gameEngine.movePlayer(Direction.UP);
            }
        }

        return true;
    }
}
