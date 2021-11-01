import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class WonderfulNodes extends Application {
    private final static double nodeWidth = 50;
    private final static double nodeHeight = 50;
    private final static int levelDiffer = 100;
    private final static int startX = 300;
    private final static int startY = 50;

    private StackPane nodeShape(String value) {
        final Text nodeText = new Text(value);
        final Rectangle rectangle = new Rectangle(nodeWidth, nodeHeight);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        final StackPane node = new StackPane();
        node.getChildren().addAll(rectangle, nodeText);
        return node;
    }

    public StackPane nodeShape(Node node, double x, double y) {
        StackPane nodePane = nodeShape(String.valueOf(node.value));
        setNodeLayout(nodePane, x, y);
        return nodePane;
    }

    public Line lineBetweenNodes(StackPane nodeX, StackPane nodeY) {
        Line internalLine = new Line();

        internalLine.setStartX(nodeX.getLayoutX() + nodeWidth * 0.5);
        internalLine.setStartY(nodeX.getLayoutY() + nodeHeight);
        internalLine.setEndX(nodeY.getLayoutX() + nodeWidth * 0.5);
        internalLine.setEndY(nodeY.getLayoutY());

        return internalLine;
    }

    private void setNodeLayout(StackPane node, double x, double y) {
        node.setLayoutX(x - nodeWidth * 0.5);
        node.setLayoutY(y - nodeWidth * 0.5);
    }

    private void loopNode(
            Node node, StackPane nodePane,
            int x, int y, int xDiffer,
            ArrayList<StackPane> shapes, ArrayList<Line> lines
    ) {
        final int newY = y + levelDiffer;

        if (node.left != null) {
            final int leftX = x - xDiffer;
            StackPane leftNodePane = nodeShape(node.left, leftX, newY);
            lines.add(lineBetweenNodes(nodePane, leftNodePane));
            shapes.add(leftNodePane);
            loopNode(node.left, leftNodePane, leftX, newY, xDiffer / 2, shapes, lines);
        }

        if (node.right != null) {
            final int rightX = x + xDiffer;
            StackPane rightNodePane = nodeShape(node.right, rightX, newY);
            lines.add(lineBetweenNodes(nodePane, rightNodePane));
            shapes.add(rightNodePane);
            loopNode(node.right, rightNodePane, rightX, newY, xDiffer / 2, shapes, lines);
        }
    }

    public Group visualizeTree(MyBinaryTree tree) {
        ArrayList<StackPane> shapes = new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<>();

        Group group = new Group();

        Node root = tree.root;

        StackPane node = nodeShape(root, startX, startY);
        shapes.add(node);

        loopNode(root, node, startX, startY, levelDiffer, shapes, lines);

        group.getChildren().addAll(shapes);
        group.getChildren().addAll(lines);

        return group;
    }

    private MyBinaryTree createTestBinaryTree() {
        MyBinaryTree myBinaryTree = new MyBinaryTree();

        myBinaryTree.add(6);
        myBinaryTree.add(4);
        myBinaryTree.add(8);
        myBinaryTree.add(3);
        myBinaryTree.add(5);
        myBinaryTree.add(7);
        myBinaryTree.add(9);
        myBinaryTree.add(10);

        return myBinaryTree;
    }

    @Override
    public void start(Stage stage) {
        MyBinaryTree myBinaryTree = createTestBinaryTree();

        Group root = visualizeTree(myBinaryTree);

        // Creating a scene object
        Scene scene = new Scene(root, 600, 600);
        stage.setTitle("WonderfulNodes");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
