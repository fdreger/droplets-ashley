package net.snowyhollows.solness.spi.scene;

import net.snowyhollows.solness.spi.component.SnComponentType;

import java.util.List;

public interface SnScene<EntityEngine, EntityHandle> {
    String getName();
    void save();
    void load();

    EntityEngine getEngine();
    SnNode<EntityHandle> getRootNode();

    EntityHandle addEntity();
    List<SnComponentType<EntityHandle>> getComponents();
    void removeEntity(EntityHandle entity);

    void dispose();
}
