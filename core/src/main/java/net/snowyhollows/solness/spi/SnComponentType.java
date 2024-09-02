package net.snowyhollows.solness.spi;

import java.util.List;

public interface SnComponentType<EntityHandle> {
    void add(EntityHandle entity);
    void remove(EntityHandle entity);
    boolean contains(EntityHandle entity);
    List<? extends SnAttribute<EntityHandle>> getAttributes();
}
