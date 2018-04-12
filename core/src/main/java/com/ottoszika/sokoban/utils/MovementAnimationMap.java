package com.ottoszika.sokoban.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class MovementAnimationMap {
    /**
     * Direction -> Animation map.
     * Each direction will have an animation attached.
     */
    private Map<Direction, Animation<TextureRegion>> directionAnimationMap;

    /**
     * Movement animation map default constructor.
     */
    public MovementAnimationMap() {
        directionAnimationMap = new HashMap<Direction, Animation<TextureRegion>>();
    }

    /**
     * Movement animation map constructor.
     *
     * @param directionAnimationMap the direction -> animation map to be set.
     */
    public MovementAnimationMap(Map<Direction, Animation<TextureRegion>> directionAnimationMap) {
        this.directionAnimationMap = directionAnimationMap;
    }

    /**
     * Get animation by direction.
     *
     * @param direction the direction to lookup.
     * @return the animation for the specified direction.
     */
    public Animation<TextureRegion> get(Direction direction) {
        return directionAnimationMap.get(direction);
    }

    /**
     * Put a new animation for a direction.
     *
     * @param direction the direction for which the animation will be added.
     * @param animation the animation to be added.
     */
    public void put(Direction direction, Animation<TextureRegion> animation) {
        directionAnimationMap.put(direction, animation);
    }

    /**
     * Get direction -> animation map.
     *
     * @return the direction -> animation map.
     */
    public Map<Direction, Animation<TextureRegion>> getDirectionAnimationMap() {
        return directionAnimationMap;
    }

    /**
     * Set direction -> animation map.
     *
     * @param directionAnimationMap the direction -> animation map to be set.
     */
    public void setDirectionAnimationMap(Map<Direction, Animation<TextureRegion>> directionAnimationMap) {
        this.directionAnimationMap = directionAnimationMap;
    }
}
