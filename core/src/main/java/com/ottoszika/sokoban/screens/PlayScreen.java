package com.ottoszika.sokoban.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.ottoszika.sokoban.Sokoban;
import com.ottoszika.sokoban.engine.GameEngine;

public class PlayScreen extends AbstractScreen {
    /**
     * Game reference.
     */
    private Sokoban game;

    /**
     * Game engine.
     */
    private GameEngine gameEngine;

    /**
     * Play screen constructor.
     *
     * @param game the game instance.
     * @param gameEngine the game flow.
     */
    public PlayScreen(Sokoban game, GameEngine gameEngine) {
        this.game = game;
        this.gameEngine = gameEngine;
    }

    /**
     * Render callback.
     *
     * @param delta the amount of time from the last rendered frame.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.game.getSpriteBatch().begin();
        gameEngine.draw(game.getSpriteBatch());
        this.game.getSpriteBatch().end();
    }
}
