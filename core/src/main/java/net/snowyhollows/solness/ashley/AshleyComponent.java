package net.snowyhollows.solness.ashley;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import net.snowyhollows.solness.ConversionUtils;
import net.snowyhollows.solness.spi.SnAttribute;
import net.snowyhollows.solness.spi.SnAttributeType;
import net.snowyhollows.solness.spi.SnComponentType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AshleyComponent implements SnComponentType<Entity> {
    private final String name;
    private final Class<? extends Component> clazz;
    private final ComponentMapper<?> componentMapper;
    private final List<AshleyAttribute> ashleyAttributes;
    private final List<AshleyAttribute> immutableAttributes;

    public AshleyComponent(String name, Class<? extends Component> clazz, ComponentMapper<?> componentMapper) {
        this.name = name;
        this.clazz = clazz;
        this.componentMapper = componentMapper;
        Field[] declaredFields = ClassReflection.getFields(clazz);
        ashleyAttributes = Arrays.stream(declaredFields).map(f -> new AshleyAttribute(componentMapper, new SnAttributeType(ConversionUtils.getTypeClassifier(f.getType())), f.getName(), f)).collect(Collectors.toList());
        immutableAttributes = Collections.unmodifiableList(ashleyAttributes);
    }


    @Override
    public void add(Entity entity) {
        try {
            Component component = ClassReflection.newInstance(clazz);
            entity.add(component);
        } catch (ReflectionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Entity entity) {
        entity.remove(clazz);
    }

    @Override
    public boolean contains(Entity entity) {
        return componentMapper.has(entity);
    }

    @Override
    public List<? extends SnAttribute<Entity>> getAttributes() {
        return immutableAttributes;
    }
}
