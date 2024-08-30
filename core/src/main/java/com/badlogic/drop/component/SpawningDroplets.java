package com.badlogic.drop.component;

import com.badlogic.ashley.core.Component;

public class SpawningDroplets implements Component {
    private float spawnTime = 1.5f;
    private float timeSinceLastSpawn = 0;

    public float getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(float spawnTime) {
        this.spawnTime = spawnTime;
    }

    public float getTimeSinceLastSpawn() {
        return timeSinceLastSpawn;
    }

    public void setTimeSinceLastSpawn(float timeSinceLastSpawn) {
        this.timeSinceLastSpawn = timeSinceLastSpawn;
    }
}
