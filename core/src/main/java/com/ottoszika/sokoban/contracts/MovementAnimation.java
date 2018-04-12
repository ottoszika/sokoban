package com.ottoszika.sokoban.contracts;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ottoszika.sokoban.utils.MovementAnimationMap;

public interface MovementAnimation {
    /**
     * Get animations map.
     *
     * @return the animations map.
     */
    MovementAnimationMap getAnimationsMap();

    /**
     * Set animations map.
     *
     * @param movementAnimationMap the animations map to be set.
     */
    void setMovementAnimationMap(MovementAnimationMap movementAnimationMap);

    /**
     * Get current animation.
     *
     * @return the current animation.
     */
    Animation<TextureRegion> getCurrentMovementAnimation();

    /**
     * Set current animation.
     *
     * @param currentAnimation the current animation to be set.
     */
    void setCurrentMovementAnimation(Animation<TextureRegion> currentAnimation);
}
