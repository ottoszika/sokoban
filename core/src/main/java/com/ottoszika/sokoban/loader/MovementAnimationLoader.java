package com.ottoszika.sokoban.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.ottoszika.sokoban.loader.definitions.MovementAnimationDefinition;
import com.ottoszika.sokoban.loader.parameters.MovementAnimationParameter;
import com.ottoszika.sokoban.utils.Assets;
import com.ottoszika.sokoban.utils.Direction;
import com.ottoszika.sokoban.utils.MovementAnimationMap;

public class MovementAnimationLoader extends SynchronousAssetLoader<MovementAnimationMap, com.ottoszika.sokoban.loader.parameters.MovementAnimationParameter> {
    /**
     * Json object.
     */
    private Json json;

    /**
     * Animation frame rate.
     */
    private float frameRate = 0.15f;

    /**
     * Movement animation loader constructor.
     *
     * @param resolver the file handle resolver.
     */
    public MovementAnimationLoader(FileHandleResolver resolver) {
        super(resolver);
        json = new Json();
    }

    /**
     * Handle asset load.
     *
     * @param assetManager the current asset manager instance.
     * @param fileName the asset file name.
     * @param file the asset file handle.
     * @param parameter the asset loader parameter.
     * @return the loaded level.
     */
    @Override
    public MovementAnimationMap load(AssetManager assetManager, String fileName, FileHandle file, com.ottoszika.sokoban.loader.parameters.MovementAnimationParameter parameter) {
        // Read file
        byte[] movementAnimationData = file.readBytes();

        // Parse movement animation json data
        MovementAnimationDefinition movementAnimationDefinition = json.fromJson(MovementAnimationDefinition.class, new String(movementAnimationData));

        // Initialize the animation map
        MovementAnimationMap movementAnimationMap = new MovementAnimationMap();

        // Get the spritesheet from the asset manager
        TextureAtlas textureAtlas = assetManager.get(Assets.SPRITESHEET_ATLAS, TextureAtlas.class);

        // Loop over each direction present in the animation
        for (String directionString : movementAnimationDefinition.keySet()) {
            // Each direction has an array of frame names
            Array<String> frameNames = movementAnimationDefinition.get(directionString);

            // For each frame name we need to load
            // the region from the spritesheet
            TextureRegion[] frames = new TextureRegion[frameNames.size];
            int i = 0;
            for (String frameName : frameNames) {
                frames[i++] = (textureAtlas.findRegion(frameName));
            }

            // Build up the animation from the frames
            // and add to the movement animation map
            Animation<TextureRegion> animation = new Animation<TextureRegion>(frameRate, frames);
            movementAnimationMap.put(Direction.valueOf(directionString), animation);
        }

        return movementAnimationMap;
    }

    /**
     * Get loader dependencies.
     *
     * @param fileName the filename.
     * @param file the file handle.
     * @param parameter the asset loader params.
     * @return the array of dependencies.
     */
    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, MovementAnimationParameter parameter) {
        Array<AssetDescriptor> assetDescriptors = new Array<AssetDescriptor>();

        // Level loader depends on spritesheet atlas,
        // so we need to load it first as dependency
        assetDescriptors.add(new AssetDescriptor<TextureAtlas>(Assets.SPRITESHEET_ATLAS, TextureAtlas.class));

        return assetDescriptors;
    }
}
