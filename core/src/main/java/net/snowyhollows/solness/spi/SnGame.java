package net.snowyhollows.solness.spi;

import com.badlogic.gdx.assets.AssetManager;

public interface SnGame<EntityEngine, EntityHandle> {
    SnScene<EntityEngine, EntityHandle> create(String name, EntityEngine entityEngine, AssetManager assetManager);
}
