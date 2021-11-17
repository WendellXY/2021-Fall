import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BST<E extends Comparable<E>> {
	private TreeNode<E> root;
	private int size = 0;

	public BST() { }

	public BST(E[] elements) {
        for (E element : elements) insert(element);
	}

	public boolean search(E e) {
		TreeNode<E> current = root;

		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			}
			else {
				return true;
            }
		}

		return false;
	}

	public boolean insert(E e) {
		if (root == null)
			root = createNewNode(e);
		else {
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else {
					return false;
                }
			}

            if (e.compareTo(parent.element) < 0)
				parent.left = createNewNode(e);
			else
				parent.right = createNewNode(e);
		}

		size++;
		return true;
	}

	private TreeNode<E> createNewNode(E e) {
		return new TreeNode<>(e);
	}

	public static class TreeNode<E extends Comparable<E>> {
		private E element;
		private TreeNode<E> left;
		private TreeNode<E> right;

		public TreeNode(E e) {
			element = e;
		}
	}

	public int getSize() {
		return size;
	}

    public TreeNode<E> getRoot() {
		return root;
	}

	public ArrayList<TreeNode<E>> path(E e) {
		ArrayList<TreeNode<E>> list = new ArrayList<>();
		TreeNode<E> current = root;

		while (current != null) {
			list.add(current);
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else {
				break;
            }
		}

		return list;
	}

	public boolean delete(E e) {
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				parent = current;
				current = current.right;
			} else {
				break;
            }
		}

		if (current == null)
			return false;

		if (current.left == null) {
			if (parent == null) {
				root = current.right;
			} else {
				if (e.compareTo(parent.element) < 0)
					parent.left = current.right;
				else
					parent.right = current.right;
			}
		} else {
			TreeNode<E> parentOfRightMost = current;
			TreeNode<E> rightMost = current.left;

			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right;
			}

			current.element = rightMost.element;

			if (parentOfRightMost.right == rightMost)
				parentOfRightMost.right = rightMost.left;
			else
				parentOfRightMost.left = rightMost.left;
		}

		size--;
		return true;
	}

	public void clear() {
		root = null;
		size = 0;
	}

	public void breadthFirstTraversal() {
		if (root == null) return;
		Queue<TreeNode<E>> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()){
			TreeNode<E> current = queue.element();
			if (current.left != null) {
				queue.add(current.left);
			}
			if (current.right != null) {
				queue.add(current.right);
			}
			System.out.print(queue.remove().element + " ");
		}
	}

	public int height() {
		return height(root);
	}

	private int height(TreeNode<E> root) {
		if (root == null) return 0;
		return 1 + Math.max(height(root.left), height(root.right));
	}

	public boolean isPerfectBST() {
		return size == Math.pow(2, height()) - 1;
	}
}
