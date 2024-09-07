package net.snowyhollows.solness.ashley;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.ObjectMap;
import net.snowyhollows.solness.spi.SnWorld;
import net.snowyhollows.solness.spi.scene.SnScene;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SolnessAshleyWorld implements SnWorld<Engine, Entity> {
    private final List<AshleyComponent> collect;
    public SolnessAshleyWorld(Class<? extends Component>... components) {
        getAssignedComponentTypes();

        List<Class<? extends Component>> result = getAssignedComponentTypes();
        result.addAll(Arrays.asList(components));


         collect = result.stream().map(c -> new AshleyComponent(c.getSimpleName(), c, ComponentMapper.getFor(c))).collect(Collectors.toList());
    }

    private static List<Class<? extends Component>> getAssignedComponentTypes() {

        List<Class<? extends Component>> result = new ArrayList<>();
        try {
            Field privateField = ComponentType.class.getDeclaredField("assignedComponentTypes");

            // Make the field accessible
            privateField.setAccessible(true);

            // Get the value of the field
            ObjectMap<Class<? extends Component>, ComponentType> fieldValue = (ObjectMap<Class<? extends Component>, ComponentType>) privateField.get(null);

            for (ObjectMap.Entry<Class<? extends Component>, ComponentType> classComponentTypeEntry : fieldValue) {
                result.add(classComponentTypeEntry.key);
            }


        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    @Override
    public SnScene<Engine, Entity> create(String name, Engine engine, AssetManager assetManager) {

        return new AshleyScene(name, engine, assetManager, collect);
    }
}
