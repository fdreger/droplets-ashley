package com.badlogic.drop;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.drop.component.*;
import com.badlogic.drop.system.ControlledSystem;
import com.badlogic.drop.system.GeneralMovementSystem;
import com.badlogic.drop.system.Renderer;
import com.badlogic.drop.system.SpawningSystem;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import net.snowyhollows.solness.SnEditor;
import net.snowyhollows.solness.ashley.AshleyScene;
import net.snowyhollows.solness.ashley.SolnessAshleyGame;
import net.snowyhollows.solness.spi.SnScene;

import java.util.List;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Engine engine;
    private AssetManager assetManager;
    private SnEditor solness;
    private boolean prepared;
    private SolnessAshleyGame snGame;

    public Main() {
    }

    @Override
    public void create() {
        assetManager = new AssetManager();
        assetManager.load("bucket.png", Texture.class);
        assetManager.load("droplet.png", Texture.class);

        engine = new Engine();
        // load the images for the droplet and the bucket, 64x64 pixels each



    }

    private void prepare() {
        engine.addEntity(createSpawnerEntity());
        engine.addEntity(createBucketEntity());

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        batch = new SpriteBatch();

        engine.addSystem(new ControlledSystem(new Controller(camera)));
        engine.addSystem(new SpawningSystem(assetManager));
        engine.addSystem(new GeneralMovementSystem());
        engine.addSystem(new Renderer(batch));

        snGame = new SolnessAshleyGame(Bounded.class, Collectible.class, Controlled.class, RenderedAsTexture.class, SpawningDroplets.class);

        SnScene<Engine, Entity> scene = snGame.create("start", engine, assetManager);
        scene.load();

        List<Entity> result = scene.getEntities();
        scene.getComponents()
        for (Entity entity : result) {
            scene.getComponents();
        }

        prepared = true;
    }

    private Entity createSpawnerEntity() {
        Entity spawner = new Entity();
        spawner.add(new Bounded());
        spawner.getComponent(Bounded.class).setX(0);
        spawner.getComponent(Bounded.class).setY(480);
        spawner.getComponent(Bounded.class).setWidth(800);
        spawner.getComponent(Bounded.class).setHeight(20);
        spawner.add(new SpawningDroplets());
        spawner.getComponent(SpawningDroplets.class).setSpawnTime(1.5f);
        return spawner;
    }

    private Entity createBucketEntity() {
        Entity bucket = new Entity();
        bucket.add(new RenderedAsTexture());
        bucket.getComponent(RenderedAsTexture.class).setTexture(assetManager.get("bucket.png", Texture.class));
        bucket.add(new Bounded());
        bucket.getComponent(Bounded.class).setX(800 / 2 - 64 / 2);
        bucket.getComponent(Bounded.class).setY(20);
        bucket.getComponent(Bounded.class).setWidth(64);
        bucket.getComponent(Bounded.class).setHeight(64);
        bucket.add(new Controlled());
        return bucket;
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        if (!assetManager.isFinished()) {
            assetManager.update();
            return;
        }

        if (!prepared) {
            prepare();
        }

        camera.update();
        batch.setProjectionMatrix(camera.combined);
        engine.update(Gdx.graphics.getDeltaTime());
        solness.act();
    }

    @Override
    public void dispose() {
        assetManager.dispose();
//        solness.dispose();
    }
}
