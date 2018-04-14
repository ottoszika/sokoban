package com.ottoszika.sokoban.engine.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.ottoszika.sokoban.engine.GameEngine;

public class GeneralInputMultiplexer extends InputMultiplexer {
    /**
     * Game engine reference.
     */
    private GameEngine gameEngine;

    /**
     * General input multiplexer.
     *
     * @param gameEngine the game engine.
     */
    public GeneralInputMultiplexer(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        registerProcessors();
    }

    /**
     * Register input processors.
     */
    public void registerProcessors() {
        switch (Gdx.app.getType()) {
            case Desktop:
                addProcessor(new KeyboardInputProcessor(gameEngine));
                break;
            case Android:
            case iOS:
                addProcessor(new GestureDetector(new TouchInputProcessor(gameEngine)));
                break;
        }
    }
}
