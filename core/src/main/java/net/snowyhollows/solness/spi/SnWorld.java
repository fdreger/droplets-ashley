package net.snowyhollows.solness.spi;

import com.badlogic.gdx.assets.AssetManager;
import net.snowyhollows.solness.spi.scene.SnScene;

public interface SnWorld<EntityEngine, EntityHandle> {
    SnScene<EntityEngine, EntityHandle> create(String name, EntityEngine entityEngine, AssetManager assetManager);
}
