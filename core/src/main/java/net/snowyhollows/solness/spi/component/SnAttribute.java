package net.snowyhollows.solness.spi.component;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface SnAttribute<EntityHandle> {
    String getName();
    void setString(EntityHandle entity, String value);
    String getString(EntityHandle entity);
    SnAttributeType getType();
    default Actor createEditor() {
        throw new UnsupportedOperationException("No editor for " + getName());
    }
}
