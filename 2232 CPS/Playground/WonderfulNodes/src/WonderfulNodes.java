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
        StackPane nodePane = nodeShape(String.valueOf(node.key));
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

    private int calculateTreeDepth(Node node) {
        if (node == null) { return 0; }
        return 1 + Math.max(calculateTreeDepth(node.left), calculateTreeDepth(node.right));
    }

    private int calculateTreeDepth(WonderfulNodesAccessibleTree tree) {
        return calculateTreeDepth(tree.getRoot());
    }

    private int calculateXLevelDiffer(WonderfulNodesAccessibleTree tree) {
        return (int) (calculateTreeDepth(tree) * nodeWidth);
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

    public Group visualizeTree(WonderfulNodesAccessibleTree tree) {
        final int startX = (int) this.sceneWidth / 2;
        final int startY = (int) nodeHeight;

        ArrayList<StackPane> shapes = new ArrayList<>();
        ArrayList<Line> lines = new ArrayList<>();

        Group group = new Group();

        Node root = tree.getRoot();
        StackPane node = nodeShape(root, startX, startY);
        shapes.add(node);

        visualizeNodeRecursive(root, node, startX, startY, calculateXLevelDiffer(tree), shapes, lines);

        group.getChildren().addAll(shapes);
        group.getChildren().addAll(lines);

        return group;
    }

    @Override
    public void start(Stage stage) {
        WonderfulNodesAccessibleTree tree = createTestAVLTree();
        // initialize sceneWidth and sceneHeight
        final double treeDepth = calculateTreeDepth(tree);
        System.out.println(treeDepth);

        this.sceneWidth = Math.pow(2, treeDepth) * nodeWidth + 2 * nodeWidth + 10;
        this.sceneHeight = 2 * treeDepth * nodeHeight;
        // Visualize Tree
        Group root = visualizeTree(tree);
        // Creating a scene object
        Scene scene = new Scene(root, sceneWidth, sceneHeight);
        stage.setTitle("WonderfulNodes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static WonderfulNodesAccessibleTree createTestBinaryTree() {
        MyBinaryTree myBinaryTree = new MyBinaryTree();

         myBinaryTree.add(37);
         myBinaryTree.add(24);
         myBinaryTree.add(42);
         myBinaryTree.add(7);
         myBinaryTree.add(2);
         myBinaryTree.add(40);
         myBinaryTree.add(42);
         myBinaryTree.add(32);
         myBinaryTree.add(120);

        return myBinaryTree;
    }

    private static WonderfulNodesAccessibleTree createTestAVLTree() {
        AVLTree myAVLTree = new AVLTree();

        myAVLTree.insert(15);
        myAVLTree.insert(20);
        myAVLTree.insert(24);
        myAVLTree.insert(10);
        myAVLTree.insert(13);
        myAVLTree.insert(7);
        myAVLTree.insert(30);
        myAVLTree.insert(36);
        myAVLTree.insert(25);

        return myAVLTree;
    }
}
