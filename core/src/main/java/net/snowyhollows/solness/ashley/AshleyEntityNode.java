package net.snowyhollows.solness.ashley;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import net.snowyhollows.solness.spi.exception.SnNotFoundException;
import net.snowyhollows.solness.spi.scene.SnDefaultNodeType;
import net.snowyhollows.solness.spi.scene.SnNode;
import net.snowyhollows.solness.spi.scene.SnNodeType;

public class AshleyEntityNode implements SnNode<Entity> {
    private final static ComponentMapper<ManagedIdentifier> managedIdentifier = ComponentMapper.getFor(ManagedIdentifier.class);

    final Entity entity;

    public AshleyEntityNode(Entity entity) {
        this.entity = entity;
        if (entity == null) {
            throw new NullPointerException();
        }
    }

    @Override
    public String getName() {
        if (managedIdentifier.has(entity)) {
            return "#" + managedIdentifier.get(entity).identifier;
        } else {
            return "#" + entity.hashCode();
        }
    }

    @Override
    public SnNodeType getType() {
        return managedIdentifier.has(entity) ? SnDefaultNodeType.MANAGED_ENTITY : SnDefaultNodeType.UNMANAGED_ENTITY;
    }

    @Override
    public boolean hasEntity() {
        return true;
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public boolean hasChildren() {
        return false;
    }

    @Override
    public SnPage<Entity> getChildrenFirstPage() throws SnNotFoundException {
        throw new SnNotFoundException();
    }
}
