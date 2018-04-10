package com.ottoszika.sokoban;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ottoszika.sokoban.loader.LevelLoader;
import com.ottoszika.sokoban.logic.Level;
import com.ottoszika.sokoban.screens.LevelSelectorScreen;

public class Sokoban extends Game {
	/**
	 * Sprite batch.
	 */
	private SpriteBatch spriteBatch;

	/**
	 * Asset manager.
	 */
	private AssetManager assetManager;

	/**
	 * Create callback.
	 */
	@Override
	public void create() {
		// Initialize fields
		spriteBatch = new SpriteBatch();
		assetManager = new AssetManager();

		this.initAssetLoaders();

		setScreen(new LevelSelectorScreen(this));
	}

	private void initAssetLoaders() {
		assetManager.setLoader(Level.class, new LevelLoader(new InternalFileHandleResolver()));
	}

	/**
	 * Get sprite batch.
	 *
	 * @return the sprite batch.
	 */
	public SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}

	/**
	 * Get asset manager.
	 *
	 * @return the asset manager.
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}
}
