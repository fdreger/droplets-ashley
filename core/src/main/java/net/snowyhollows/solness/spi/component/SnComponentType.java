package net.snowyhollows.solness.spi.component;

import java.util.List;

public interface SnComponentType<EntityHandle> {
    String getName();
    void add(EntityHandle entity);
    void remove(EntityHandle entity);
    boolean contains(EntityHandle entity);
    List<? extends SnAttribute<EntityHandle>> getAttributes();
}
