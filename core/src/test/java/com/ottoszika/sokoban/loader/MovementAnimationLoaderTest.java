package com.ottoszika.sokoban.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.ottoszika.sokoban.loader.parameters.LevelParameter;
import com.ottoszika.sokoban.loader.parameters.MovementAnimationParameter;
import com.ottoszika.sokoban.utils.Assets;
import com.ottoszika.sokoban.utils.MovementAnimationMap;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MovementAnimationLoaderTest {

    @Test
    public void load() {
        // TODO: Test
    }

    @Test
    public void getDependencies() {
        MovementAnimationLoader movementAnimationLoader = new MovementAnimationLoader(mock(FileHandleResolver.class));
        Array<AssetDescriptor> assetDescriptorArray =
                movementAnimationLoader.getDependencies(
                        "test.json",
                        mock(FileHandle.class),
                        mock(MovementAnimationParameter.class)
                );

        assertEquals(1, assetDescriptorArray.size);
        assertEquals(TextureAtlas.class, assetDescriptorArray.get(0).type);
        assertEquals(Assets.SPRITESHEET_ATLAS, assetDescriptorArray.get(0).fileName);
    }
}