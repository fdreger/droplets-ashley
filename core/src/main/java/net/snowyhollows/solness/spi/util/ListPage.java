package net.snowyhollows.solness.spi.util;

import net.snowyhollows.solness.spi.scene.SnNode;

import java.util.List;

public class ListPage<EntityHandle> implements SnNode.SnPage<EntityHandle> {

    private final List<SnNode<EntityHandle>> items;

    public ListPage(List<SnNode<EntityHandle>> items) {
        this.items = items;
    }

    @Override
    public List<SnNode<EntityHandle>> getItems() {
        return items;
    }
}
