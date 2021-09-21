import java.util.ArrayList;
import java.util.Arrays;

class DynamicArray<E> {
    E[] data;

    private int count;

    private int capacity;

    public int getCount() {
        return count;
    }

    public E[] getData() {
        return data;
    }

    public int getCapacity() {
        return capacity;
    }

    DynamicArray() {
        this.data = (E[]) new Object[0];
        this.capacity = 0;
        this.count = 0;
    }

    DynamicArray(E[] data) {
        this.data = data.clone();
        this.capacity = data.length;
        this.count = data.length;
    }

    void append(E element) {
        if (count >= capacity) {
            capacity = capacity * 2 + 1;
            E[] tmp = (E[]) new Object[capacity];
            System.arraycopy(data, 0, tmp, 0, count);
            data = tmp;

        }
        data[count++] = element;
    }

    void printData() {
        System.out.printf("%d/%d[", capacity, count);
        for (int i = 0; i < count; ++i) {
            System.out.print(data[i]);
            System.out.print(i == count - 1 ? "" : ", ");
        }
        System.out.print("]\n");
    }
}

public class Playground {
    public static void main(String[] args) {
        DynamicArray<Integer> test = new DynamicArray<>();
        test.append(1);

        System.out.println(test.getCount());
        test.printData();

        test.append(2);

        test.printData();

        ArrayList<Integer> list = new ArrayList<>();

        System.out.println(list.size());

        list.add(1);

        System.out.println(list.size());

    }
}