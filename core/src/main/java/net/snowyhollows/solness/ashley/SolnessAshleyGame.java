package net.snowyhollows.solness.ashley;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.assets.AssetManager;
import net.snowyhollows.solness.spi.SnGame;
import net.snowyhollows.solness.spi.SnScene;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SolnessAshleyGame implements SnGame<Engine, Entity> {

    private final List<ComponentMapper<? extends Component>> mappers;

    public SolnessAshleyGame(Class<? extends Component>... components) {
        mappers = Arrays.stream(components).map(c -> ComponentMapper.getFor(c)).collect(Collectors.toList());
    }


    @Override
    public SnScene<Engine, Entity> create(String name, Engine engine, AssetManager assetManager) {

        return new AshleyScene(name, engine, assetManager, mappers);
    }
}
