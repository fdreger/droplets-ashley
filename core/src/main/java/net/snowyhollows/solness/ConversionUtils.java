package net.snowyhollows.solness;

import net.snowyhollows.solness.spi.TypeClassifier;

public class ConversionUtils {

    public static String objectToString(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public static TypeClassifier getTypeClassifier(Class target) {
        if (target.isEnum()) {
            return TypeClassifier.ENUM;
        }
        switch (target.getName()) {
            case "java.lang.Integer":
                return TypeClassifier.INT;
            case "java.lang.Float":
                return TypeClassifier.FLOAT;
            case "java.lang.Boolean":
                return TypeClassifier.BOOLEAN;
            case "java.lang.Long":
                return TypeClassifier.INT;
            case "java.lang.Double":
                return TypeClassifier.FLOAT;
            case "java.lang.Short":
                return TypeClassifier.INT;
            case "java.lang.Byte":
                return TypeClassifier.INT;
            default:
                return TypeClassifier.GENERIC;
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
                return Integer.parseInt(value);
            case "java.lang.Float":
                return Float.parseFloat(value);
            case "java.lang.Boolean":
                return Boolean.parseBoolean(value);
            case "java.lang.Long":
                return Long.parseLong(value);
            case "java.lang.Double":
                return Double.parseDouble(value);
            case "java.lang.Short":
                return Short.parseShort(value);
            case "java.lang.Byte":
                return Byte.parseByte(value);
            default:
                throw new IllegalArgumentException("Unsupported type: " + target.getName());
        }

    }
}
