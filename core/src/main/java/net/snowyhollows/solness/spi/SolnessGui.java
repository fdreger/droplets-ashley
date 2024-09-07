package net.snowyhollows.solness.spi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.layout.FlowGroup;
import com.kotcrab.vis.ui.widget.*;
import net.snowyhollows.solness.spi.component.SnAttribute;
import net.snowyhollows.solness.spi.component.SnComponentType;
import net.snowyhollows.solness.spi.exception.SnNotFoundException;
import net.snowyhollows.solness.spi.scene.SnNode;
import net.snowyhollows.solness.spi.scene.SnScene;

public class SolnessGui<EntityEngine, Entity> {
    private final ScreenViewport screenViewport;
    private final OrthographicCamera orthographicCamera;
    private final Stage stage;
    private final VisTable attTable;

    private final VisTree<TreeTableNode<Entity>, Actor> sceneGraph;
    private final Group properties;
    private final VisTree assets;
    private final VisTree layers;
    private final SnScene<EntityEngine, Entity> scene;
    private SnNode<Entity> selectedNode;

    public SolnessGui(SnScene<EntityEngine, Entity> scene) {
        this.scene = scene;
        orthographicCamera = new OrthographicCamera();
        screenViewport = new ScreenViewport(orthographicCamera);

        VisUI.load();
        stage = new Stage(screenViewport);
        Gdx.input.setInputProcessor(stage);

        attTable = new VisTable();
        attTable.setFillParent(true);
        stage.addActor(attTable);

        sceneGraph = new VisTree<>();
        properties = new FlowGroup(true);
        assets = new VisTree<>();
        layers = new VisTree<>();

        MenuBar menuBar = new MenuBar();

        attTable.add(menuBar.getTable()).colspan(3).growX();
        attTable.row().expandY().pad(10);
        attTable.add(sceneGraph).width(200).fill();
        attTable.add().fill().expandX();
        attTable.add(properties).width(200).fill();

        fillSceneGraph();
        attTable.setDebug(true); // This is optional, but enables debug lines for tables.
    }

    private void fillSceneGraph() {
        SnNode<Entity> rootNode = scene.getRootNode();

        TreeTableNode<Entity> treeNode = null;
        try {
            treeNode = createNode(rootNode);
        } catch (SnNotFoundException e) {
            throw new RuntimeException(e);
        }
        sceneGraph.add(treeNode);

        try {
            for (SnNode<Entity> item : rootNode.getChildrenFirstPage().getItems()) {
                if (!item.hasEntity()) {
                    continue;
                }

                Entity entity = item.getEntity();

                System.out.println(entity);
                for (SnComponentType<Entity> component : scene.getComponents()) {
                    System.out.println("component = " + component.getName());
                    if (component.contains(entity)) {
                        for (SnAttribute<Entity> attribute : component.getAttributes()) {
                            System.out.println(" - " + attribute.getName() + " = " + attribute.getString(entity));
                        }
                    }
                }
                scene.getComponents();
            }
        } catch (SnNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private TreeTableNode<Entity> createNode(SnNode<Entity> sceneNode) throws SnNotFoundException {
        TreeTableNode<Entity> node = new TreeTableNode<>(new VisLabel(sceneNode.getName()));
        node.setValue(sceneNode);
        if (sceneNode.hasChildren()) {
            for (SnNode<Entity> item : sceneNode.getChildrenFirstPage().getItems()) {
                node.add(createNode(item));
            }
        }
        return node;
    }

    public void fillProperties() throws SnNotFoundException {
        properties.clearChildren();
        for (SnComponentType<Entity> component : scene.getComponents()) {
            if (component.contains(selectedNode.getEntity())) {
                VisCheckBox visCheckBox = new VisCheckBox(component.getName());
                FlowGroup attributesGroup = new FlowGroup(true);
                properties.addActor(visCheckBox);
                properties.addActor(attributesGroup);
                createAttributes(component, attributesGroup);
            }
        }
    }

    private void createAttributes(SnComponentType<Entity> component, Group componentNode) throws SnNotFoundException {
        for (SnAttribute<Entity> attribute : component.getAttributes()) {
            FlowGroup flowGroup = new FlowGroup(false);
            flowGroup.addActor(new VisLabel(attribute.getName()));
            Entity currentEntity = selectedNode.getEntity();
            VisTextField actor = new VisTextField(attribute.getString(currentEntity));
            actor.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor unused) {
                    attribute.setString(currentEntity, actor.getText());
                }
            });
            flowGroup.addActor(actor);
            componentNode.addActor(flowGroup);
        }
    }

    private TreeTableNode<Entity> createComponentNode(SnComponentType component) {
        TreeTableNode<Entity> objectTreeTableNode = new TreeTableNode<>(new VisLabel(component.getName()));
        objectTreeTableNode.expandAll();
        return objectTreeTableNode;
    }


    public void act() {
        if (sceneGraph.getSelectedNode() != null && sceneGraph.getSelectedNode().getValue() != selectedNode && sceneGraph.getSelectedNode().getValue().hasEntity()) {
            selectedNode = sceneGraph.getSelectedNode().getValue();
            try {
                fillProperties();
            } catch (SnNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        stage.getViewport().update(Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight(), true);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void dispose() {
        VisUI.dispose();
    }

    static class TreeTableNode<Entity> extends Tree.Node<TreeTableNode, SnNode<Entity>, Actor> {
        public TreeTableNode(Actor actor) {
            super(actor);
        }
    }
}
