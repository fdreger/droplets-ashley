package com.badlogic.drop.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.drop.component.Bounded;
import com.badlogic.drop.component.Collectible;
import com.badlogic.drop.component.Controlled;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class ControlledSystem extends IteratingSystem {

    private final Camera camera;
    private final Vector3 touchPos = new Vector3();
    private final Family collectibles = Family.all(Collectible.class, Bounded.class).get();

    public ControlledSystem(Camera camera) {
        super(Family.all(Controlled.class, Bounded.class).get());
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);

        Bounded bounded = entity.getComponent(Bounded.class);

        if (Gdx.input.isTouched()) {
            bounded.setX(touchPos.x - bounded.getWidth() / 2);
//            bounded.setY(touchPos.y - bounded.getHeight() / 2);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            bounded.setX(bounded.getX() - 600 * Gdx.graphics.getDeltaTime());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            bounded.setX(bounded.getX() + 600 * Gdx.graphics.getDeltaTime());
        }

        if(bounded.getX() < 0) {
            bounded.setX(0);
        }
        if(bounded.getX() > 800 - bounded.getWidth()) {
            bounded.setX(800 - bounded.getWidth());
        }

        ImmutableArray<Entity> collectibleEntities = getEngine().getEntitiesFor(collectibles);

        for (Entity collectible : collectibleEntities) {
            Bounded bounded1 = collectible.getComponent(Bounded.class);
            if(bounded.getRectangle().overlaps(bounded1.getRectangle())) {
                getEngine().removeEntity(collectible);
            }
        }
    }
}
