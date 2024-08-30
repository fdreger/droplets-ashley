package com.badlogic.drop.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.drop.component.Bounded;
import com.badlogic.drop.component.Moving;

public class GeneralMovementSystem extends IteratingSystem {


    public GeneralMovementSystem() {
        super(Family.all(Bounded.class, Moving.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Bounded bounded = entity.getComponent(Bounded.class);
        Moving moving = entity.getComponent(Moving.class);

        bounded.setX(bounded.getX() + moving.getDx() * deltaTime);
        bounded.setY(bounded.getY() + moving.getDy() * deltaTime);

        if(bounded.getX() < -50 || bounded.getX() > 850 || bounded.getY() < -50 || bounded.getY() > 480 + 50) {
            getEngine().removeEntity(entity);
        }
    }
}
