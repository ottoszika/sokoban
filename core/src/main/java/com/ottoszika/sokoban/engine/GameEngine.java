package com.ottoszika.sokoban.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ottoszika.sokoban.contracts.LevelEventListener;
import com.ottoszika.sokoban.contracts.MovementAnimation;
import com.ottoszika.sokoban.engine.input.GeneralInputMultiplexer;
import com.ottoszika.sokoban.entities.GameEntity;
import com.ottoszika.sokoban.entities.Player;
import com.ottoszika.sokoban.logic.Level;
import com.ottoszika.sokoban.logic.LevelManager;
import com.ottoszika.sokoban.utils.Direction;

public class GameEngine implements LevelEventListener {
    /**
     * Level of the game.
     */
    private Level level;

    /**
     * Level drawer of the level.
     */
    private LevelDrawer levelDrawer;

    /**
     * Level manager of the level.
     */
    private LevelManager levelManager;

    /**
     * Player object.
     */
    private Player player;

    /**
     * Input multiplexer.
     */
    private InputMultiplexer inputMultiplexer;

    /**
     * Game engine constructor.
     *
     * @param level the level to play on.
     */
    public GameEngine(Level level) {
        this.level = level;
        init();
        registerListeners();
    }

    /**
     * Initialize level related stuffs.
     */
    private void init() {
        inputMultiplexer = new GeneralInputMultiplexer(this);
        levelDrawer = new LevelDrawer(level);
        levelManager = new LevelManager(level);
        player = level.getPlayer();
    }

    /**
     * Register listeners.
     */
    private void registerListeners() {
        levelManager.addLevelEventListener(this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * Move player.
     *
     * @param direction the direction where to move the player.
     */
    public void movePlayer(Direction direction) {
        if (!levelDrawer.isEntityMoved(player)) {
            levelManager.move(player, direction);
        }
    }

    /**
     * Animate by direction.
     *
     * @param movementAnimation the entity to animate.
     * @param direction the direction of the movement animation.
     */
    private void animate(MovementAnimation movementAnimation, Direction direction) {
        Animation<TextureRegion> animation = movementAnimation.getAnimationsMap().get(direction);
        movementAnimation.setCurrentMovementAnimation(animation);
    }

    /**
     * Draw the game.
     *
     * @param spriteBatch the sprite batch to use for drawing.
     */
    public void draw(SpriteBatch spriteBatch) {
        levelDrawer.draw(spriteBatch);
    }

    /**
     * On entity was moved event.
     *
     * @param entity the entity which was moved.
     * @param direction the direction where the entity was moved.
     */
    @Override
    public void onEntityMoved(GameEntity entity, Direction direction) {
        if (entity instanceof MovementAnimation) {
            animate((MovementAnimation) entity, direction);
        }
    }
}
