package com.badlogic.drop.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;

public class Bounded implements Component {
    private final Rectangle rectangle = new Rectangle();

    public float getX() {
        return rectangle.getX();
    }

    public Rectangle setX(float x) {
        return rectangle.setX(x);
    }

    public float getY() {
        return rectangle.getY();
    }

    public Rectangle setY(float y) {
        return rectangle.setY(y);
    }

    public float getWidth() {
        return rectangle.getWidth();
    }

    public Rectangle setWidth(float width) {
        return rectangle.setWidth(width);
    }

    public float getHeight() {
        return rectangle.getHeight();
    }

    public Rectangle setHeight(float height) {
        return rectangle.setHeight(height);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }
}
