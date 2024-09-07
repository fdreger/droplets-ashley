package net.snowyhollows.solness.spi.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface SnAttributeEditor<EntityEngine, EntityHandle> {
    Actor getActor();
    void load(EntityHandle entity);
    void save();
    void cancel();
}
