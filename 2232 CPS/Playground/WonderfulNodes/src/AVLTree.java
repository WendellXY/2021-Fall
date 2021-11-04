public class AVLTree implements WonderfulNodesAccessibleTree {
    private int size = 0;

    private Node root;

    @Override
    public int getSize() { return size; }

    @Override
    public Node getRoot() { return root; }

    private void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int height(Node n) {
        return n == null ? -1 : n.height;
    }

    private int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        Node z = x.right;
        x.right = node;
        node.left = z;
        updateHeight(node);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        Node z = x.left;
        x.left = node;
        node.right = z;
        updateHeight(node);
        updateHeight(x);
        return x;
    }

    private Node rebalance(Node node) {
        updateHeight(node);
        int balance = getBalance(node);
        if (balance > 1) {
            if (height(node.right.right) <= height(node.right.left)) {
                node.right = rotateRight(node.right);
            }
            node = rotateLeft(node);
        } else if (balance < -1) {
            if (height(node.left.left) <= height(node.left.right)) {
                node.left = rotateLeft(node.left);
            }
            node = rotateRight(node);
        }
        return node;
    }

    public void insert(int key) {
        root = _insert(root, key);
        size++;
    }

    private Node _insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        } else if (node.key > key) {
            node.left = _insert(node.left, key);
        } else if (node.key < key) {
            node.right = _insert(node.right, key);
        } else {
//            throw new RuntimeException("duplicate Key!");
        }
        return rebalance(node);
    }

    public void delete(int key) {
        root = _delete(root, key);
        size--;
    }

    private Node _delete(Node node, int key) {
        if (node == null) {
            return null;
        } else if (node.key > key) {
            node.left = _delete(node.left, key);
        } else if (node.key < key) {
            node.right = _delete(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.key = mostLeftChild.key;
                node.right = _delete(node.right, node.key);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    private Node mostLeftChild(Node node) {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.key == key) {
                break;
            }
            current = current.key < key ? current.right : current.left;
        }
        return current;
    }
}
