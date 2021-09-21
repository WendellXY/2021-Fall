public class Playground210906 {
    public static void main(String[] args) {
        System.out.println("Hello World");
        System.out.println(factorial(5));
        System.out.println(power(10, 5));
        System.out.println(mystery(3, 2, 6));

        for (int i = 0; i < 11; i++)
            trial(i + 1);
    }

    private static int factorial(int value) {
        if (value < 1)  return 0;
        if (value == 1) return 1;

        return value * factorial(value - 1);
    }

    private static int power(int value, int times) {
        if (times == 0) {
            return 1;
        }

        int ret = power(value, times / 2);

        if (times % 2 == 0) {
            return ret * ret;
        } else {
            return ret * ret * value;
        }
    }

    private static int mystery(int n, int a, int d) {
        if (n == 1) return a;
        else return d + mystery(n - 1, a, d);
    }

    // After-class Practice
    private static void trial(int id) {
        final int MIN = 2, MAX = 11;
        final int COUNT = MAX - new Random().nextInt(MAX - MIN + 1) - MIN;

        final Integer[] RAW_VALUES = IntStream.rangeClosed(1, 11).boxed().toArray(Integer[]::new);

        LinkedList<Integer> possibleValues = new LinkedList<>(Arrays.asList(RAW_VALUES));

        for (int i = 0; i < COUNT; i++) {
            final int RIGHT_BOUND = MAX - i;
            final int INDEX = new Random().nextInt(RIGHT_BOUND);
            possibleValues.remove(INDEX);
        }

        System.out.printf("Trial %02d: %s\n", id, possibleValues.toString());
    }
}
