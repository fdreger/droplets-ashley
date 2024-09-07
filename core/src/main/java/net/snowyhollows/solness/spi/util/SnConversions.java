package net.snowyhollows.solness.spi.util;

import net.snowyhollows.solness.spi.component.SnAttributeTypeClassifier;

public class SnConversions {

    public static String objectToString(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public static SnAttributeTypeClassifier getTypeClassifier(Class target) {
        if (target.isEnum()) {
            return SnAttributeTypeClassifier.ENUM;
        }
        switch (target.getName()) {
            case "java.lang.Integer":
            case "int":
                return SnAttributeTypeClassifier.INT;
            case "java.lang.Float":
            case "float":
                return SnAttributeTypeClassifier.FLOAT;
            case "java.lang.Boolean":
            case "boolean":
                return SnAttributeTypeClassifier.BOOLEAN;
            case "java.lang.Long":
            case "long":
                return SnAttributeTypeClassifier.INT;
            case "java.lang.Double":
            case "double":
                return SnAttributeTypeClassifier.FLOAT;
            case "java.lang.Short":
            case "short":
                return SnAttributeTypeClassifier.INT;
            case "java.lang.Byte":
            case "byte":
                return SnAttributeTypeClassifier.INT;
            default:
                return SnAttributeTypeClassifier.GENERIC;
        }
    }

    public static Object stringToObject(Class target, String value) {
        if ("".equals(value) || value == null) {
            return null;
        }
        if (target.isEnum()) {
            return Enum.valueOf(target, value);
        }
        switch (target.getName()) {
            case "java.lang.String":
                return value;
            case "java.lang.Integer":
            case "int":
                return Integer.parseInt(value);
            case "java.lang.Float":
            case "float":
                return Float.parseFloat(value);
            case "java.lang.Boolean":
            case "boolean":
                return Boolean.parseBoolean(value);
            case "java.lang.Long":
            case "long":
                return Long.parseLong(value);
            case "java.lang.Double":
            case "double":
                return Double.parseDouble(value);
            case "java.lang.Short":
            case "short":
                return Short.parseShort(value);
            case "java.lang.Byte":
            case "byte":
                return Byte.parseByte(value);
            default:
                throw new IllegalArgumentException("Unsupported type: " + target.getName());
        }

    }
}
