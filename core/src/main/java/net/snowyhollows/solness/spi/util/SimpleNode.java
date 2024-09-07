package net.snowyhollows.solness.spi.util;

import net.snowyhollows.solness.spi.exception.SnNotFoundException;
import net.snowyhollows.solness.spi.scene.SnNode;
import net.snowyhollows.solness.spi.scene.SnNodeType;

public class SimpleNode<EntityHandle> implements SnNode<EntityHandle> {
    private final SnNodeType snNodeType;
    private final String name;
    private final ListPage<EntityHandle> children;
    private final SnNodeType nodeType;

    public SimpleNode(final SnNodeType snNodeType, final String name, ListPage<EntityHandle> children) {
        this.snNodeType = snNodeType;
        this.name = name;
        this.children = children;
        this.nodeType = snNodeType;
        if (children == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public SnNodeType getType() {
        return nodeType;
    }

    @Override
    public boolean hasEntity() {
        return false;
    }

    @Override
    public EntityHandle getEntity() throws SnNotFoundException {
        throw new SnNotFoundException();
    }

    @Override
    public boolean hasChildren() {
        return children != null;
    }

    @Override
    public SnPage<EntityHandle> getChildrenFirstPage() throws SnNotFoundException {
        if (children == null) {
            throw new SnNotFoundException();
        }
        return children;
    }
}
