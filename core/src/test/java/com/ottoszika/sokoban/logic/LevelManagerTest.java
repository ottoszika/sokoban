package com.ottoszika.sokoban.logic;

import com.ottoszika.sokoban.entities.Block;
import com.ottoszika.sokoban.entities.Crate;
import com.ottoszika.sokoban.entities.Player;
import com.ottoszika.sokoban.utils.Direction;
import com.ottoszika.sokoban.utils.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevelManagerTest {

    private Level level;
    private Player player;

    @Before
    public void setUp() {
        level = new Level(8, 6);

        /*
        BBBBBBBB
        B CP  CB
        B  C   B
        B  C   B
        B      B
        BBBBBBBB
         */

        for (int i = 0; i < 8; i++) {
            Block blocksUp = new Block();
            blocksUp.setPosition(new Position(i, 0));
            level.add(blocksUp);

            Block blocksDown = new Block();
            blocksDown.setPosition(new Position(i, 5));
            level.add(blocksDown);
        }

        for (int i = 1; i < 5; i++) {
            Block blocksLeft = new Block();
            blocksLeft.setPosition(new Position(0, i));
            level.add(blocksLeft);

            Block blocksRight = new Block();
            blocksRight.setPosition(new Position(7, i));
            level.add(blocksRight);
        }

        Crate crateOne = new Crate();
        Crate crateTwo = new Crate();
        Crate crateThree = new Crate();
        Crate crateFour = new Crate();

        crateOne.setPosition(new Position(2, 1));
        crateTwo.setPosition(new Position(6, 1));
        crateThree.setPosition(new Position(3, 2));
        crateFour.setPosition(new Position(3, 3));

        level.add(crateOne);
        level.add(crateTwo);
        level.add(crateThree);
        level.add(crateFour);

        player = new Player();
        player.setPosition(new Position(3, 1));

        level.add(player);
    }

    @Test
    public void testCanMove() {
        LevelManager levelManager = new LevelManager(level);
        assertFalse(levelManager.canMove(player, Direction.UP));
        assertFalse(levelManager.canMove(player, Direction.DOWN));
        assertTrue(levelManager.canMove(player, Direction.RIGHT));
        assertTrue(levelManager.canMove(player, Direction.LEFT));
    }

    @Test
    public void testMove() {
        LevelManager levelManager = new LevelManager(level);
        assertTrue(levelManager.move(player, Direction.RIGHT));
        assertTrue(levelManager.move(player, Direction.RIGHT));
        assertFalse(levelManager.move(player, Direction.RIGHT));
        assertTrue(levelManager.move(player, Direction.DOWN));
        assertTrue(levelManager.move(player, Direction.LEFT));
        assertTrue(levelManager.move(player, Direction.LEFT));
        assertTrue(levelManager.move(player, Direction.LEFT));
        assertFalse(levelManager.move(player, Direction.LEFT));
        assertFalse(levelManager.move(player, Direction.LEFT));
        assertTrue(levelManager.move(player, Direction.DOWN));
        assertTrue(levelManager.move(player, Direction.DOWN));
        assertFalse(levelManager.move(player, Direction.DOWN));
    }

    @Test
    public void testToString() {
        System.out.println(level.toString());
    }
}