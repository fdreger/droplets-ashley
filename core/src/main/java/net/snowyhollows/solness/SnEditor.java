package net.snowyhollows.solness;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.*;
import net.snowyhollows.solness.spi.SnScene;

public class SnEditor<EntityEngine, Entity> {
    private final ScreenViewport screenViewport;
    private final OrthographicCamera orthographicCamera;
    private final Stage stage;
    private final VisTable table;

    public SnEditor(SnScene<EntityEngine, Entity> snScene) {
        orthographicCamera = new OrthographicCamera();
        screenViewport = new ScreenViewport(orthographicCamera);

        VisUI.load();
        stage = new Stage(screenViewport);
        Gdx.input.setInputProcessor(stage);

        table = new VisTable();
        table.setFillParent(true);
        stage.addActor(table);

        MenuBar menuBar = new MenuBar();
        menuBar.addMenu(createFileMenu());
        menuBar.addMenu(createEditMenu());

        VisTree tree1 = createTable();
        VisTree tree2 = createTable();

        VisLabel v1 = new VisLabel("v1");
        VisLabel v2 = new VisLabel("v2");
        VisLabel v3 = new VisLabel("v3");

        VisList<String> list = new VisList<>();
        list.setItems("Asdasd", "ASDf asdf", "Werwer", "rythrfvbd", "gfsdfgsdfg");

        table.add(menuBar.getTable()).colspan(3).growX();
        table.row().expandY().pad(10);
        table.add(tree1).width(200).fill();
        table.add().fill().expandX();
        table.add(tree2).width(200).fill();


//        table.setDebug(true); // This is optional, but enables debug lines for tables.
    }

    private static VisTree createTable() {
        VisTree tree = new VisTree();
        TestNode item1 = new TestNode(new VisLabel("item 1"));
        TestNode item2 = new TestNode(new VisLabel("item 2"));
        TestNode item3 = new TestNode(new VisLabel("item 3"));

        item1.add(new TestNode(new VisLabel("item 1.1")));
        item1.add(new TestNode(new VisLabel("item 1.2")));
        item1.add(new TestNode(new VisLabel("item 1.3")));

        item2.add(new TestNode(new VisLabel("item 2.1")));
        item2.add(new TestNode(new VisLabel("item 2.2")));
        item2.add(new TestNode(new VisLabel("item 2.3")));

        item3.add(new TestNode(new VisLabel("item 3.1")));
        item3.add(new TestNode(new VisLabel("item 3.2")));
        item3.add(new TestNode(new VisLabel("item 3.3")));

        item1.setExpanded(true);

        tree.add(item1);
        tree.add(item2);
        tree.add(item3);
        return tree;
    }

    private Menu createEditMenu() {
        Menu menu = new Menu("Edit");

        MenuItem cutMenuItem = new MenuItem("Cut");
        MenuItem copyMenuItem = new MenuItem("Copy");
        MenuItem pasteMenuItem = new MenuItem("Paste");

        menu.addItem(cutMenuItem);
        menu.addItem(copyMenuItem);
        menu.addItem(pasteMenuItem);

        return menu;
    }

    private Menu createFileMenu() {
        Menu menu = new Menu("File");

        MenuItem newMenuItem = new MenuItem("New");
        MenuItem openMenuItem = new MenuItem("Open");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem exitMenuItem = new MenuItem("Exit");

        menu.addItem(newMenuItem);
        menu.addItem(openMenuItem);
        menu.addItem(saveMenuItem);
        menu.addItem(exitMenuItem);

        return menu;
    }

    public void act() {
        stage.getViewport().update(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void dispose() {
        VisUI.dispose();
    }

    static class TestNode extends Tree.Node {
        public TestNode(Actor actor) {
            super(actor);
        }
    }
}
