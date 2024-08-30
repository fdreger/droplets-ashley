package com.badlogic.drop.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.drop.component.Bounded;
import com.badlogic.drop.component.RenderedAsTexture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Renderer extends IteratingSystem {

    private final SpriteBatch spriteBatch;

    public Renderer(SpriteBatch spriteBatch) {
        super(Family.all(Bounded.class, RenderedAsTexture.class).get());
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void update(float deltaTime) {
        spriteBatch.begin();
        super.update(deltaTime);
        spriteBatch.end();
    }

    @Override
    protected void processEntity(Entity entity, float v) {
        Bounded bounded = entity.getComponent(Bounded.class);
        RenderedAsTexture renderedAsTexture = entity.getComponent(RenderedAsTexture.class);
        spriteBatch.draw(renderedAsTexture.getTexture(), bounded.getX(), bounded.getY());
    }
}
