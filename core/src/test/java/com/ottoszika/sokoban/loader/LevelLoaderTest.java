package com.ottoszika.sokoban.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.ottoszika.sokoban.logic.Level;
import com.ottoszika.sokoban.utils.Assets;
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
        com.ottoszika.sokoban.loader.LevelLoader levelLoader = new com.ottoszika.sokoban.loader.LevelLoader(mock(FileHandleResolver.class));
        Array<AssetDescriptor> assetDescriptorArray =
                levelLoader.getDependencies(
                        "test.json",
                        mock(FileHandle.class),
                        (AssetLoaderParameters<Level>) mock(AssetLoaderParameters.class)
                );

        assertEquals(1, assetDescriptorArray.size);
        assertEquals(TextureAtlas.class, assetDescriptorArray.get(0).type);
        assertEquals(Assets.SPRITESHEET_ATLAS, assetDescriptorArray.get(0).fileName);
    }
}