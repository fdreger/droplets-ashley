package com.badlogic.drop.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.drop.component.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class SpawningSystem extends IteratingSystem {

    private AssetManager assetManager;

    public SpawningSystem(AssetManager assetManager) {
        super(Family.all(Bounded.class, SpawningDroplets.class).get());
        this.assetManager = assetManager;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        SpawningDroplets spawningDroplets = entity.getComponent(SpawningDroplets.class);
        Bounded bounded = entity.getComponent(Bounded.class);
        spawningDroplets.setTimeSinceLastSpawn(spawningDroplets.getTimeSinceLastSpawn() + deltaTime);
        if (spawningDroplets.getTimeSinceLastSpawn() > spawningDroplets.getSpawnTime()) {
            spawningDroplets.setTimeSinceLastSpawn(0);
            spawnDroplet(bounded);
        }
    }

    private void spawnDroplet(Bounded bounded) {
        Entity droplet = new Entity();
        droplet.add(new RenderedAsTexture());
        droplet.getComponent(RenderedAsTexture.class).setTexture(assetManager.get("droplet.png", Texture.class));
        droplet.add(new Bounded());
        droplet.getComponent(Bounded.class).setWidth(64);
        droplet.getComponent(Bounded.class).setHeight(64);
        droplet.add(new Collectible());
        droplet.add(new Moving());
        droplet.getComponent(Moving.class).setDy(-100);
        droplet.getComponent(Bounded.class).setX(MathUtils.random(bounded.getX(), bounded.getX() + bounded.getWidth() - 64));
        droplet.getComponent(Bounded.class).setY(MathUtils.random(bounded.getY(), bounded.getY() + bounded.getHeight() - 64));
        getEngine().addEntity(droplet);
    }
}
