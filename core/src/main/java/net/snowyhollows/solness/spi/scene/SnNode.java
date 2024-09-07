package net.snowyhollows.solness.spi.scene;

import net.snowyhollows.solness.spi.exception.SnNotFoundException;

import java.util.List;

public interface SnNode<Entity> {
    String getName();
    SnNodeType getType();
    boolean hasEntity();
    Entity getEntity() throws SnNotFoundException;
    boolean hasChildren();
    SnPage<Entity> getChildrenFirstPage() throws SnNotFoundException;

    interface SnPage<Entity> {
        List<SnNode<Entity>> getItems();
    }

}
