public class MyBinaryTree implements WonderfulNodesAccessibleTree {
    private int size = 0;

    private Node root;

    @Override
    public Node getRoot() {
        return root;
    }

    @Override
    public int getSize() {
        return size;
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.key) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.key) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }

        return current;
    }

    public void add(int value) {
        root = addRecursive(root, value);
        size += 1;
    }

    private Node removeRecursive(Node current, int value) {

        if (value < current.key) {

        } else if (value > current.key) {

        } else {
            
        }

        return null;
    }

    public void remove(int value) {
        root = removeRecursive(root, value);
        size--;
    }
}
