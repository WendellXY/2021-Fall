import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

class Gen<T extends Number> {
    T[] values;

    Double average() {
        Double ret = 0.0;

        for (int i = 0; i < values.length; i++)
            ret += values[i].doubleValue();

        ret /= values.length;

        return ret;
    }
}

class ArrayListReview<E> {
    ArrayList<E> al;
    LinkedList<E> ll;

    ArrayListReview() {
        this.al = new ArrayList<>();
        this.ll = new LinkedList<>();
    }

    ArrayListReview(E[] list) {
        this.al = new ArrayList<>(Arrays.asList(list));
        this.ll = new LinkedList<>(Arrays.asList(list));
    }

    E insert(E element, int i) {
        this.al.add(i, element);
        this.ll.add(i, element);

        return element;
    }
}

class Order {
    LinkedList<Integer> IDs;
    LinkedList<Integer> counts;

    Order() {
        this.IDs = new LinkedList<Integer>();
        this.counts = new LinkedList<Integer>();
    }

    void add(int ID, int count) {
        this.IDs.add(ID);
        this.counts.add(count);
    }

    void add(String buffer, OrderProcessingSystem system) {
        final String[] tempStrings = buffer.split(" ");
        // System.out.println(Arrays.asList(tempStrings).toString());
        final int id = system.orderNameToID(tempStrings[0]);
        final int count = Integer.parseInt(tempStrings[1]);

        this.add(id, count);
    }
}

class OrderProcessingSystem {
    LinkedList<String> names;
    LinkedList<Double> prices;

    int length;

    OrderProcessingSystem() {
        this.names = new LinkedList<String>();
        this.prices = new LinkedList<Double>();
        this.length = 0;
    }

    void addNewGood(String name, double price) {
        names.add(name);
        prices.add(price);
        length += 1;
    }

    void addNewGood(String buffer) {
        final String[] tempStrings = buffer.split(" ");
        // System.out.println(Arrays.asList(tempStrings).toString());
        final double price = Double.parseDouble(tempStrings[1]);
        this.addNewGood(tempStrings[0], price);
    }

    double calculateTotalPrice(LinkedList<Integer> IDs, LinkedList<Integer> counts) {
        double total = 0.0;
        for (int i = 0; i < counts.size(); i++) {
            final int id = IDs.get(i);
            final int count = counts.get(i);
            total += prices.get(id) * count;
        }
        return total;
    }

    double calculateTotalPrice(Order order) {
        return calculateTotalPrice(order.IDs, order.counts);
    }

    int orderNameToID(String name) {
        for (int i = 0; i < this.length; i++)
            if (names.get(i).equals(name))
                return i;

        return -1;
    }
}

public class Playground210916 {
    public static <E extends Comparable<E>> E max(E o1, E o2) {
        return o1.compareTo(o2) > 0 ? o1 : o2;
    }

    public static <E extends Comparable<E>> E max(E o1, E o2, E o3) {
        return max(max(o1, o2), o3);
    }

    public static void main(String[] args) {
        System.out.println("Test for ArrayList Review");
        ArrayListReview<Integer> arrayListReview = new ArrayListReview<>();
        arrayListReview.insert(120, 0);
        arrayListReview.insert(110, 1);
        arrayListReview.insert(100, 1);
        arrayListReview.insert(90, 1);
        System.out.println(arrayListReview.ll.toString());
        System.out.println(arrayListReview.al.toString());

        // -------------------------------------------

        System.out.println("Order Processing System");

        OrderProcessingSystem system = new OrderProcessingSystem();

        LinkedList<Order> orders = new LinkedList<>();

        Order tmpOrder = new Order();

        final String quitCommand = "QUIT";
        final String endPriceCommand = "END_PRICES";
        final String endInvoiceCommand = "END_INVOICE";

        boolean enteringPrice = true;

        System.out.println("Input Prices");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String buffer = scanner.nextLine();

            if (buffer.equals(quitCommand)) {
                break;
            };

            if (buffer.equals(endPriceCommand)) {
                enteringPrice = false;
                continue;
            };

            if (enteringPrice) {
                system.addNewGood(buffer);
            } else {
                if (buffer.equals(endInvoiceCommand)) {
                    orders.add(tmpOrder);
                    tmpOrder = new Order();
                } else {
                    tmpOrder.add(buffer, system);
                }
            }
        }

        scanner.close();

        // Show result
        for (int i = 0; i < orders.size(); i++) {
            final double ret = system.calculateTotalPrice(orders.get(i));
            System.out.printf("Total: %f\n", ret);
        }
    }
}