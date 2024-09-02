package net.snowyhollows.solness.spi;

import java.util.List;

public interface SnScene<EntityEngine, EntityHandle> {
    String getName();

    void readFromEngine();
    void writeToEngine();
    void save();
    void load();

    EntityEngine getEngine();
    List<EntityHandle> getEntities();

    EntityHandle addEntity();
    List<SnComponentType> getComponents();
    void removeEntity(EntityHandle entity);
}
