package net.snowyhollows.solness.ashley;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import net.snowyhollows.solness.spi.component.SnComponentType;
import net.snowyhollows.solness.spi.scene.SnDefaultNodeType;
import net.snowyhollows.solness.spi.scene.SnNode;
import net.snowyhollows.solness.spi.scene.SnScene;
import net.snowyhollows.solness.spi.util.ListPage;
import net.snowyhollows.solness.spi.util.SimpleNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AshleyScene implements SnScene<Engine, Entity> {
    private final String name;
    private final Engine engine;
    private final AssetManager assetManager;
    private final List<AshleyComponent> components;

    AshleyScene(String name, Engine engine, AssetManager assetManager, List<AshleyComponent> components) {
        this.name = name;
        this.engine = engine;
        this.assetManager = assetManager;
        this.components = components;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    @Override
    public Engine getEngine() {
        return engine;
    }

    @Override
    public SnNode<Entity> getRootNode() {
        List<SnNode<Entity>> entities = new ArrayList<>();
        for (Entity entity : engine.getEntities()) {
            entities.add(new AshleyEntityNode(entity));
        }
        return new SimpleNode<>(SnDefaultNodeType.GROUP, "entities", new ListPage<>(entities));
    }

    @Override
    public Entity addEntity() {
        Entity entity = new Entity();
        engine.addEntity(entity);
        return entity;
    }

    @Override
    public List<SnComponentType<Entity>> getComponents() {
        return Collections.unmodifiableList(components);
    }


    @Override
    public void removeEntity(Entity entity) {
        engine.removeEntity(entity);
    }

    @Override
    public void dispose() {

    }
}
