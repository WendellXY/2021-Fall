import java.util.ArrayList;

class StackWithoutGeneric {
    ArrayList<String> stack = new ArrayList<>();

    public String push(String element) {
        this.stack.add(element);

        return element;
    }

    public String pop() {
        return this.stack.remove(stack.size() - 1);
    }

    public String peek() {
        return this.stack.get(stack.size() - 1);
    }

    public int size() {
        return this.stack.size();
    }
}

class GenericStack<E> {
    ArrayList<E> stack = new ArrayList<>();

    public E push(E element) {
        this.stack.add(element);

        return element;
    }

    public E pop() {
        return this.stack.remove(stack.size() - 1);
    }

    public E peek() {
        return this.stack.get(stack.size() - 1);
    }

    public int size() {
        return this.stack.size();
    }
}

class Test<T> {
    T obj;
    Test(T obj) {
        this.obj = obj;
    }

    public T getObj() {
        return obj;
    }

    public static Test<Integer> newInstance(Integer value) {
        return new Test<Integer>(value);
    }

    public static Test<String> newInstance(String value) {
        return new Test<String>(value);
    }
}

public class Playground210914 {

    public static void main(String[] args) {
        StackWithoutGeneric stack = new StackWithoutGeneric();
        stack.push("Wendell");
        stack.push("Wang");

        System.out.println(stack.size());

        GenericStack<String> gStack = new GenericStack<>();
        gStack.push("Wendell");
        gStack.push("Wang");
        gStack.push("1");
        gStack.push("2.2");

        System.out.println(gStack.size());
        System.out.println(gStack.peek());

        System.out.println("For loop here");
        for (int i = 0, size = gStack.size(); i < size; i++) {
            System.out.println(gStack.pop());
        }

        Test iObject = Test.newInstance(15);
        System.out.println(iObject.getObj());

        Test sObject = Test.newInstance("Hello World");
        System.out.println(sObject.getObj());

        Test<Object> obj = new Test<Object>("F");
        System.out.println(obj.getObj());
        obj.obj = 15;
        System.out.println(obj.getObj());
    }
}
