package com.ottoszika.sokoban.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.ottoszika.sokoban.Sokoban;
import com.ottoszika.sokoban.utils.LevelDrawer;

public class PlayScreen extends AbstractScreen {
    /**
     * Game reference.
     */
    private Sokoban game;

    /**
     * Level drawer.
     */
    private LevelDrawer levelDrawer;

    /**
     * Play screen constructor.
     *
     * @param game the game instance.
     * @param levelDrawer the level drawer.
     */
    public PlayScreen(Sokoban game, LevelDrawer levelDrawer) {
        this.game = game;
        this.levelDrawer = levelDrawer;
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
        this.levelDrawer.draw(game.getSpriteBatch());
        this.game.getSpriteBatch().end();
    }
}
