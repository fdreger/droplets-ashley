package net.snowyhollows.solness.ashley;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import net.snowyhollows.solness.spi.SnComponentType;
import net.snowyhollows.solness.spi.SnScene;

import java.util.List;

public class AshleyScene implements SnScene<Engine, Entity> {
    private final String name;
    private final Engine engine;
    private final AssetManager assetManager;


    AshleyScene(String name, Engine engine, AssetManager assetManager, List<ComponentMapper<? extends Component>> mappers) {
        this.name = name;
        this.engine = engine;
        this.assetManager = assetManager;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void readFromEngine() {

    }

    @Override
    public void writeToEngine() {

    }

    @Override
    public void save() {

    }

    @Override
    public void load() {

    }

    @Override
    public Engine getEngine() {
        return null;
    }

    @Override
    public List<Entity> getEntities() {
        return engine.getEntities();
    }

    @Override
    public Entity addEntity() {
        Entity entity = new Entity();
        engine.addEntity(entity);
        return entity;
    }

    @Override
    public List<SnComponentType> getComponents(SnComponentType componentType) {
        return List.of();
    }

    @Override
    public void removeEntity(Entity entity) {
        engine.removeEntity(entity);
    }
}
