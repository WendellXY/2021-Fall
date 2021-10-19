public class Heap {

    Node root;

    public Heap(int[] keys) {
        int[] sortedKeys = QuickSort.quickSort(keys);

        final int length = sortedKeys.length;

        this.root = new Node(sortedKeys[length - 1]);

        for (int i = length - 2; i >= 0; i--) {

        }
    }

    public static class Node {
        int key;

        Node leftNode;
        Node rightNode;

        public Node(int key) {
            this.key = key;
            this.leftNode = null;
            this.rightNode = null;
        }

        public boolean hasNullNode() {
            return leftNode == null || rightNode == null;
        }

        public static Node firstAvailableNode(Node node) {
            if (node.hasNullNode()) {
                return node;
            }


        }
    }

    public static void main(String[] args) {

    }
}
