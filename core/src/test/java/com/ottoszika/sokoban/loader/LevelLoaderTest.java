package com.ottoszika.sokoban.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.ottoszika.sokoban.loader.parameters.LevelParameter;
import com.ottoszika.sokoban.utils.Assets;
import com.ottoszika.sokoban.utils.MovementAnimationMap;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LevelLoaderTest {

    @Test
    public void testLoad() {
        // TODO: Test
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetDependencies() {
        LevelLoader levelLoader = new LevelLoader(mock(FileHandleResolver.class));
        Array<AssetDescriptor> assetDescriptorArray =
                levelLoader.getDependencies(
                        "test.json",
                        mock(FileHandle.class),
                        mock(LevelParameter.class)
                );

        assertEquals(2, assetDescriptorArray.size);
        assertEquals(TextureAtlas.class, assetDescriptorArray.get(0).type);
        assertEquals(Assets.SPRITESHEET_ATLAS, assetDescriptorArray.get(0).fileName);

        assertEquals(2, assetDescriptorArray.size);
        assertEquals(MovementAnimationMap.class, assetDescriptorArray.get(1).type);
        assertEquals(Assets.MOVEMENT_ANIMATIONS, assetDescriptorArray.get(1).fileName);
    }
}