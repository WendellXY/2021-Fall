import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WonderfulNodes extends Application {
    private final static double nodeWidth = 50;
    private final static double nodeHeight = 50;

    public StackPane nodeShape(String value) {
        final Text nodeText = new Text(value);
        final Rectangle rectangle = new Rectangle(nodeWidth, nodeHeight);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        final StackPane node = new StackPane();
        node.getChildren().addAll(rectangle, nodeText);
        return node;
    }

    public StackPane nodeShape(String value, double x, double y) {
        StackPane node = nodeShape(value);
        setNodeLayout(node, x, y);
        return node;
    }

    public Line lineBetweenNodes(StackPane nodeX, StackPane nodeY) {
        Line internalLine = new Line();

        internalLine.setStartX(nodeX.getLayoutX() + nodeWidth * 0.5);
        internalLine.setStartY(nodeX.getLayoutY() + nodeHeight);
        internalLine.setEndX(nodeY.getLayoutX() + nodeWidth * 0.5);
        internalLine.setEndY(nodeY.getLayoutY());

        return internalLine;
    }

    public void setNodeLayout(StackPane node, double x, double y) {
        node.setLayoutX(x - nodeWidth * 0.5);
        node.setLayoutY(y - nodeWidth * 0.5);
    }

    @Override
    public void start(Stage stage) {
        StackPane nodeStackA = nodeShape("A", 300, 50);
        StackPane nodeStackB = nodeShape("B", 200, 200);
        StackPane nodeStackC = nodeShape("C", 400, 200);

        Line intervalLineAB = lineBetweenNodes(nodeStackA, nodeStackB);
        Line intervalLineAC = lineBetweenNodes(nodeStackA, nodeStackC);

        Group root = new Group(nodeStackA, nodeStackB, nodeStackC, intervalLineAB, intervalLineAC);

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
