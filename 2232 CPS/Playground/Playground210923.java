import java.util.ArrayList;
import java.util.PriorityQueue;

class LinkedList<E> {

	Node<E> head;

	static class Node<E> {
		E data;
		Node<E> next;

		Node(E d) {
			data = d;
			next = null;
		}

        public boolean hasNext() {
            return next != null;
        }
	}

    public void append(E element) {
		Node<E> new_node = new Node<>(element);
		new_node.next = null;

		if (head == null) {
			head = new_node;
		} else {
			Node<E> last = head;
			while (last.hasNext())
				last = last.next;

			last.next = new_node;
		}
    }

    public E dropFirst() {
        if (head == null)
            return null;

        final E firstE = head.data;
        head = head.next;
        return firstE;

    }

    public E[] dropFirst(int times) {
        ArrayList<E> list = new ArrayList<>();

        for (int i = 0; i < times; ++i)
            list.add(dropFirst());

        return (E[]) list.toArray();
    }

    public E dropLast() {
        if (head == null)
            return null;

        Node<E> node = head;

        while (node.hasNext()) {
            if (!node.next.hasNext()) {
                final E lastE = node.next.data;
                node.next = null;
                return lastE;
            }
            node = node.next;
        }

        return null;
    }

    public E[] dropLast(int times) {
        ArrayList<E> list = new ArrayList<>();

        for (int i = 0; i < times; ++i)
            list.add(dropLast());

        return (E[]) list.toArray();
    }

    public int count() {
        if (head == null)
            return 0;

        Node<E> node = head;
        int count = 1;
        while (node.next != null) {
            node = node.next;
            count++;
        }

        return count;
    }

	public static void print(LinkedList list) {
		Node currNode = list.head;

		System.out.print("LinkedList: [");

		while (currNode != null) {
			System.out.print(currNode.data);

            System.out.print(currNode.next == null ? "" : ", ");

			currNode = currNode.next;
		}
        System.out.print("]\n");
	}
}



public class Playground210923 {
    public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();

		list.append(1);
		list.append(2);
		list.append(3);
		list.append(4);
		list.append(5);
		list.append(6);
		list.append(7);
		list.append(8);

		LinkedList.print(list);

        System.out.printf("%d\n", list.count());

        list.dropLast(8);

        LinkedList.print(list);

        PriorityQueue<String> pq = new PriorityQueue<>();

        pq.add("China");
        pq.add("Japan");
        pq.add("London");
        pq.add("Germany"); //pq.remove("For");
        System.out.println(pq);
    }
}