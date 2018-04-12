package com.ottoszika.sokoban.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ottoszika.sokoban.contracts.Collidable;
import com.ottoszika.sokoban.contracts.MovementAnimation;
import com.ottoszika.sokoban.utils.MovementAnimationMap;

public class Player extends GameEntity implements Collidable, MovementAnimation {
    /**
     * Mass value.
     */
    private static final int MASS = 2;

    /**
     * Animation map.
     */
    private MovementAnimationMap movementAnimationMap;

    /**
     * Current animation.
     */
    private Animation<TextureRegion> currentAnimation;

    /**
     * Get mass value.
     *
     * @return the mass value.
     */
    @Override
    public int getMass() {
        return MASS;
    }

    /**
     * Get animations map.
     *
     * @return the animations map.
     */
    @Override
    public MovementAnimationMap getAnimationsMap() {
        return movementAnimationMap;
    }

    /**
     * Set animations map.
     *
     * @param movementAnimationMap the animations map to be set.
     */
    @Override
    public void setMovementAnimationMap(MovementAnimationMap movementAnimationMap) {
        this.movementAnimationMap = movementAnimationMap;
    }

    /**
     * Get current animation.
     *
     * @return the current animation.
     */
    @Override
    public Animation<TextureRegion> getCurrentMovementAnimation() {
        return currentAnimation;
    }

    /**
     * Set current animation.
     *
     * @param currentAnimation the current animation to be set.
     */
    @Override
    public void setCurrentMovementAnimation(Animation<TextureRegion> currentAnimation) {
        this.currentAnimation = currentAnimation;
    }
}
