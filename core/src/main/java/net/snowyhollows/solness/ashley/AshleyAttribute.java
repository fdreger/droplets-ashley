package net.snowyhollows.solness.ashley;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import net.snowyhollows.solness.ConversionUtils;
import net.snowyhollows.solness.spi.SnAttribute;
import net.snowyhollows.solness.spi.SnAttributeType;


public class AshleyAttribute implements SnAttribute<Entity> {
    private final ComponentMapper<?> componentType;
    private final SnAttributeType type;
    private final String name;
    private final Field field;

    public AshleyAttribute(ComponentMapper<?> componentType, SnAttributeType type, String name, Field field) {
        this.componentType = componentType;
        this.type = type;
        this.name = name;
        this.field = field;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setString(Entity entity, String value) {
        setString(entity, value, componentType, field);
    }

    @Override
    public String getString(Entity entity) {
        return getString(entity, componentType, field);
    }

    @Override
    public SnAttributeType getType() {
        return type;
    }

    private static void setString(Entity entity, String value, ComponentMapper<?> mapper, Field field) {
        Object component = mapper.get(entity);
        try {
            field.set(component, ConversionUtils.stringToObject(field.getType(), value));ConversionUtils.objectToString(field.get(component));
        } catch (ReflectionException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getString(Entity entity, ComponentMapper<?> mapper, Field field) {
        Object component = mapper.get(entity);
        try {
            return ConversionUtils.objectToString(field.get(component));
        } catch (ReflectionException e) {
            throw new RuntimeException(e);
        }
    }
}
