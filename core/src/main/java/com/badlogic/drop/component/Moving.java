package com.badlogic.drop.component;

import com.badlogic.ashley.core.Component;

public class Moving implements Component {
    private float dx = 0;
    private float dy = 0;

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public float getDy() {
        return dy;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }
}
