package com.badlogic.drop.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.drop.Controller;
import com.badlogic.drop.component.Bounded;
import com.badlogic.drop.component.Collectible;
import com.badlogic.drop.component.Controlled;
import com.badlogic.gdx.Gdx;

public class ControlledSystem extends IteratingSystem {
    private final Controller controller;

    private final Family collectibles = Family.all(Collectible.class, Bounded.class).get();

    public ControlledSystem(Controller controller) {
        super(Family.all(Controlled.class, Bounded.class).get());
        this.controller = controller;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Bounded bounded = entity.getComponent(Bounded.class);

        if (controller.isTouched()) {
            bounded.setX(controller.getTouchX() - bounded.getWidth() / 2);
        }

        if(controller.isLeftPressed()) {
            bounded.setX(bounded.getX() - 600 * Gdx.graphics.getDeltaTime());
        }
        if(controller.isRightPressed()) {
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
                Controlled controlled = entity.getComponent(Controlled.class);
                controlled.setScore(controlled.getScore() + collectible.getComponent(Collectible.class).getScore());
            }
        }
    }
}
