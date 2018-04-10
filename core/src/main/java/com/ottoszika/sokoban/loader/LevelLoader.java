package com.ottoszika.sokoban.loader;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.SynchronousAssetLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.ottoszika.sokoban.entities.GameEntity;
import com.ottoszika.sokoban.factories.GameEntityFactory;
import com.ottoszika.sokoban.loader.definitions.LevelDefinition;
import com.ottoszika.sokoban.logic.Level;
import com.ottoszika.sokoban.utils.Assets;
import com.ottoszika.sokoban.utils.Position;

public class LevelLoader extends SynchronousAssetLoader<Level, AssetLoaderParameters<Level>> {
    /**
     * Json object.
     */
    private Json json;

    /**
     * Level.
     */
    private Level level;

    /**
     * Level loader constructor.
     *
     * @param resolver the file handle resolver.
     */
    public LevelLoader(FileHandleResolver resolver) {
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
    public Level load(AssetManager assetManager, String fileName, FileHandle file, AssetLoaderParameters<Level> parameter) {
        // Read file
        byte[] levelData = file.readBytes();

        // Read JSON level into level definition
        LevelDefinition levelDefinition = json.fromJson(LevelDefinition.class, new String(levelData));

        // Build level based on definition size
        level = new Level(levelDefinition.getWidth(), levelDefinition.getHeight());

        // Get spritesheet atlas
        TextureAtlas textureAtlas = assetManager.get(Assets.SPRITESHEET_ATLAS, TextureAtlas.class);

        // Getting all entities
        for (com.ottoszika.sokoban.loader.definitions.GameEntityDefinition gameEntityDefinition : levelDefinition.getGameEntityDefinitions()) {
            // Create an entity based on type using the factory
            GameEntity entity = GameEntityFactory.create(gameEntityDefinition.getType());

            // Skip null entities
            if (entity == null) {
                continue;
            }

            // Get texture for the game entity
            TextureRegion textureRegion = textureAtlas.findRegion(gameEntityDefinition.getRegionName());

            // Build entity
            entity.setGridPosition(new Position(gameEntityDefinition.getX(), gameEntityDefinition.getY()));
            entity.setRegion(textureRegion);
            entity.setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
            entity.setPosition(
                    gameEntityDefinition.getX() * textureRegion.getRegionWidth(),
                    gameEntityDefinition.getY() * textureRegion.getRegionHeight()
            );

            level.add(entity);
        }

        return level;
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
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, AssetLoaderParameters<Level> parameter) {
        Array<AssetDescriptor> assetDescriptors = new Array<AssetDescriptor>();

        // Level loader depends on spritesheet atlas,
        // so we need to load it first as dependency
        assetDescriptors.add(new AssetDescriptor<TextureAtlas>(Assets.SPRITESHEET_ATLAS, TextureAtlas.class));

        return assetDescriptors;
    }
}
