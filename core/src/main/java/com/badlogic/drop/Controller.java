package com.badlogic.drop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class Controller {
    private final Camera camera;
    private final Vector3 touchPos = new Vector3();

    public Controller(Camera camera) {
        this.camera = camera;
    }

    public boolean isTouched() {
        boolean touched = Gdx.input.isTouched();
        if (touched) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
        }
        return touched;
    }

    public float getTouchX() {
        return touchPos.x;
    }

    public float getTouchY() {
        return touchPos.y;
    }

    public boolean isLeftPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }

    public boolean isRightPressed() {
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }
}
