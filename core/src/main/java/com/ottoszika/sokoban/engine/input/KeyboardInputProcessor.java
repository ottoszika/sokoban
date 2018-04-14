package com.ottoszika.sokoban.engine.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.ottoszika.sokoban.engine.GameEngine;
import com.ottoszika.sokoban.utils.Direction;

public class KeyboardInputProcessor extends InputAdapter {
    /**
     * Game engine reference.
     */
    private GameEngine gameEngine;

    /**
     * Keyboard input processor.
     *
     * @param gameEngine the game engine.
     */
    public KeyboardInputProcessor(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    /**
     * Key down event.
     *
     * @param keycode the key code.
     * @return the key down success result.
     */
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                gameEngine.movePlayer(Direction.LEFT);
                return true;
            case Input.Keys.UP:
                gameEngine.movePlayer(Direction.UP);
                return true;
            case Input.Keys.RIGHT:
                gameEngine.movePlayer(Direction.RIGHT);
                return true;
            case Input.Keys.DOWN:
                gameEngine.movePlayer(Direction.DOWN);
                return true;
        }

        return false;
    }
}
