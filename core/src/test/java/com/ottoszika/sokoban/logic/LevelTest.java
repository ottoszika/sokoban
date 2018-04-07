package com.ottoszika.sokoban.logic;

import com.ottoszika.sokoban.entities.GameEntity;
import com.ottoszika.sokoban.utils.Direction;
import com.ottoszika.sokoban.utils.Position;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LevelTest {


    @Test
    public void testGetEntititesByPosition() {
        GameEntity gameEntity = mock(GameEntity.class);
        Level level = new Level();

        Set<GameEntity> gameEntitySet = new HashSet<>();
        gameEntitySet.add(gameEntity);

        level.getMap().put(new Position(2, 1), gameEntitySet);

        assertEquals(gameEntitySet, level.getEntitiesByPosition(new Position(2, 1)));
        assertNull(level.getEntitiesByPosition(new Position(2, 2)));
    }

    @Test
    public void testGetNearbyPosition() {
        assertEquals(new Position(1, 1), Level.getNearbyPosition(new Position(2, 1), Direction.LEFT));
        assertEquals(new Position(3, 1), Level.getNearbyPosition(new Position(2, 1), Direction.RIGHT));
        assertEquals(new Position(2, 0), Level.getNearbyPosition(new Position(2, 1), Direction.UP));
        assertEquals(new Position(2, 2), Level.getNearbyPosition(new Position(2, 1), Direction.DOWN));
    }

    @Test
    public void testGetNearbyEntities() {
        Set<GameEntity> gameEntitySet = new HashSet<>();
        Set<GameEntity> gameEntityLeftSet = new HashSet<>();
        Set<GameEntity> gameEntityRightSet = new HashSet<>();
        Set<GameEntity> gameEntityUpSet = new HashSet<>();
        Set<GameEntity> gameEntityDownSet = new HashSet<>();

        Level level = new Level();
        level.getMap().put(new Position(2, 1), gameEntitySet);
        level.getMap().put(new Position(1, 1), gameEntityLeftSet);
        level.getMap().put(new Position(3, 1), gameEntityRightSet);
        level.getMap().put(new Position(2, 0), gameEntityUpSet);
        level.getMap().put(new Position(2, 2), gameEntityDownSet);

        assertEquals(gameEntityLeftSet, level.getNearbyEntities(new Position(2, 1), Direction.LEFT));
        assertEquals(gameEntityRightSet, level.getNearbyEntities(new Position(2, 1), Direction.RIGHT));
        assertEquals(gameEntityUpSet, level.getNearbyEntities(new Position(2, 1), Direction.UP));
        assertEquals(gameEntityDownSet, level.getNearbyEntities(new Position(2, 1), Direction.DOWN));
    }

    @Test
    public void testExists() {
        Level level = new Level();

        level.getMap().put(new Position(2, 1), new HashSet<GameEntity>());

        assertFalse(level.exists(new Position(1, 2)));
        assertTrue(level.exists(new Position(2, 1)));
    }

    @Test
    public void testClear() {
        Level level = new Level();

        level.getMap().put(new Position(2, 1), new HashSet<GameEntity>());
        level.getMap().put(new Position(2, 3), new HashSet<GameEntity>());
        level.getMap().put(new Position(2, 3), new HashSet<GameEntity>());

        level.clear();
        assertTrue(level.getMap().isEmpty());

        level.getMap().put(new Position(2, 1), new HashSet<GameEntity>());
        level.getMap().put(new Position(2, 3), new HashSet<GameEntity>());
        level.getMap().put(new Position(2, 4), new HashSet<GameEntity>());
        level.clear(new Position(2, 3));

        assertEquals(2, level.getMap().size());
        assertTrue(level.getMap().containsKey(new Position(2, 1)));
        assertFalse(level.getMap().containsKey(new Position(2, 3)));
        assertTrue(level.getMap().containsKey(new Position(2, 4)));
    }

    @Test
    public void testAdd() {
        GameEntity gameEntityOne = mock(GameEntity.class);
        GameEntity gameEntityTwo = mock(GameEntity.class);

        Level level = new Level();

        Position position = new Position(2, 1);

        when(gameEntityOne.getPosition()).thenReturn(position);
        level.add(gameEntityOne);

        assertEquals(1, level.getMap().size());
        assertNotNull(level.getMap().get(position));
        assertEquals(1, level.getMap().get(position).size());
        assertTrue(level.getMap().get(position).contains(gameEntityOne));

        when(gameEntityTwo.getPosition()).thenReturn(position);
        level.add(gameEntityTwo);

        assertEquals(1, level.getMap().size());
        assertNotNull(level.getMap().get(position));
        assertEquals(2, level.getMap().get(position).size());
        assertTrue(level.getMap().get(position).contains(gameEntityOne));
        assertTrue(level.getMap().get(position).contains(gameEntityTwo));
    }

    @Test
    public void testRemove() {
        GameEntity gameEntityOne = mock(GameEntity.class);
        GameEntity gameEntityTwo = mock(GameEntity.class);
        GameEntity gameEntityThree = mock(GameEntity.class);

        when(gameEntityOne.getPosition()).thenReturn(new Position(2, 1));
        when(gameEntityTwo.getPosition()).thenReturn(new Position(2, 1));
        when(gameEntityThree.getPosition()).thenReturn(new Position(2, 3));

        Level level = new Level();

        Set<GameEntity> gameEntitiesOne = new HashSet<>();
        Set<GameEntity> gameEntitiesTwo = new HashSet<>();

        gameEntitiesOne.add(gameEntityOne);
        gameEntitiesOne.add(gameEntityTwo);
        gameEntitiesTwo.add(gameEntityThree);

        level.getMap().put(new Position(2, 1), gameEntitiesOne);
        level.getMap().put(new Position(2, 3), gameEntitiesTwo);

        level.remove(gameEntityOne);
        assertEquals(2, level.getMap().size());
        assertEquals(1, level.getMap().get(new Position(2, 1)).size());
        assertEquals(1, level.getMap().get(new Position(2, 3)).size());

        level.remove(gameEntityTwo);
        assertEquals(2, level.getMap().size());
        assertEquals(0, level.getMap().get(new Position(2, 1)).size());
        assertEquals(1, level.getMap().get(new Position(2, 3)).size());

        level.remove(gameEntityThree);
        assertEquals(2, level.getMap().size());
        assertEquals(0, level.getMap().get(new Position(2, 1)).size());
        assertEquals(0, level.getMap().get(new Position(2, 3)).size());
    }
}