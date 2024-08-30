package com.badlogic.drop.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;

public class RenderedAsTexture implements Component {
    private Texture texture;

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
}
