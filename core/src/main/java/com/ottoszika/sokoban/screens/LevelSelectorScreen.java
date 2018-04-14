package com.ottoszika.sokoban.screens;

import com.ottoszika.sokoban.Sokoban;
import com.ottoszika.sokoban.logic.Level;
import com.ottoszika.sokoban.utils.Assets;
import com.ottoszika.sokoban.engine.GameEngine;
import com.ottoszika.sokoban.utils.MovementAnimationMap;

public class LevelSelectorScreen extends AbstractScreen {
    /**
     * Game reference.
     */
    private Sokoban game;

    /**
     * Game engine.
     */
    private GameEngine gameEngine;

    /**
     * Level selector screen constructor.
     *
     * @param game the game object.
     */
    public LevelSelectorScreen(Sokoban game) {
        this.game = game;
    }

    @Override
    public void show() {
        // Loading the first level
        // TODO: Implement the UI for level selection
        Level level = loadLevel(1);
        gameEngine = new GameEngine(level);
        game.setScreen(new PlayScreen(game, gameEngine));
    }

    /**
     * Load level by number.
     *
     * @param level the level number.
     * @return the loaded level object.
     */
    private Level loadLevel(int level) {
        String levelFileName = Assets.getLevel(level);

        game.getAssetManager().load(levelFileName, Level.class);
        game.getAssetManager().load(Assets.MOVEMENT_ANIMATIONS, MovementAnimationMap.class);
        game.getAssetManager().finishLoading();

        return game.getAssetManager().get(levelFileName);
    }
}
