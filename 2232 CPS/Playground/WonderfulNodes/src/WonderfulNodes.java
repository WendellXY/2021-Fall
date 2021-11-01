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

    double sceneWidth;
    double sceneHeight;

    private StackPane nodeShape(String value) {
        final Text nodeText = new Text(value);
        final Rectangle rectangle = new Rectangle(nodeWidth, nodeHeight);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        final StackPane node = new StackPane();
        node.getChildren().addAll(rectangle, nodeText);
        return node;
    }

    private void setNodeLayout(StackPane node, double x, double y) {
        node.setLayoutX(x - nodeWidth * 0.5);
        node.setLayoutY(y - nodeWidth * 0.5);
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

    private int calculateTreeLevel(MyBinaryTree tree) {
        int treeSize = tree.size;
        // suppose the tree is perfectly balanced
        int level = 0;
        int maximumNodesForTheLevel;

        do {
            maximumNodesForTheLevel = 1 << level++;
            treeSize -= maximumNodesForTheLevel;
        } while (treeSize > maximumNodesForTheLevel);

        return level;
    }

    private int calculateXLevelDiffer(MyBinaryTree tree) {
        return (int) (calculateTreeLevel(tree) * nodeWidth);
    }

    private void visualizeNodeRecursive(
            Node node, StackPane nodePane,
            int x, int y, int xDiffer,
            ArrayList<StackPane> shapes, ArrayList<Line> lines
    ) {
        final int newY = y + levelDiffer;

        if (node.left != null) {
            final int leftX = x - xDiffer;
            StackPane newNodePane = nodeShape(node.left, leftX, newY);
            lines.add(lineBetweenNodes(nodePane, newNodePane));
            shapes.add(newNodePane);
            visualizeNodeRecursive(node.left, newNodePane, leftX, newY, xDiffer >> 1, shapes, lines);
        }

        if (node.right != null) {
            final int rightX = x + xDiffer;
            StackPane newNodePane = nodeShape(node.right, rightX, newY);
            lines.add(lineBetweenNodes(nodePane, newNodePane));
            shapes.add(newNodePane);
            visualizeNodeRecursive(node.right, newNodePane, rightX, newY, xDiffer >> 1, shapes, lines);
        }
    }

    public Group visualizeTree(MyBinaryTree tree) {
        final int startX = (int) this.sceneWidth / 2;
        final int startY = (int) nodeHeight;

        ArrayList<StackPane> shapes = new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<>();

        Group group = new Group();

        Node root = tree.root;
        StackPane node = nodeShape(root, startX, startY);
        shapes.add(node);

        visualizeNodeRecursive(root, node, startX, startY, calculateXLevelDiffer(tree), shapes, lines);

        group.getChildren().addAll(shapes);
        group.getChildren().addAll(lines);

        return group;
    }

    @Override
    public void start(Stage stage) {
        MyBinaryTree myBinaryTree = createTestBinaryTree();
        // initialize sceneWidth and sceneHeight
        final double treeLevel = calculateTreeLevel(myBinaryTree);
        this.sceneWidth = Math.pow(2, treeLevel + 1) * nodeWidth;
        this.sceneHeight = 2 * treeLevel * nodeHeight;
        // Visualize Tree
        Group root = visualizeTree(myBinaryTree);
        // Creating a scene object
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setTitle("WonderfulNodes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static MyBinaryTree createTestBinaryTree() {
        MyBinaryTree myBinaryTree = new MyBinaryTree();

        myBinaryTree.add(6);
        myBinaryTree.add(4);
        myBinaryTree.add(8);
        myBinaryTree.add(3);
        myBinaryTree.add(5);
        myBinaryTree.add(7);
        myBinaryTree.add(9);

        return myBinaryTree;
    }
}
