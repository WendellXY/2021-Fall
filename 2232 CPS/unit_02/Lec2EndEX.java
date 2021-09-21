import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

public class Lec2EndEX {
    public static void main(String[] args) {
        for (int i = 1; i <= 11; i++)
            trial(i);
    }

    private static void trial(int id) {
        final int MIN = 2, MAX = 11;
        final int COUNT = MAX - randomInt(MIN, MAX);

        final Integer[] RAW_VALUES = IntStream.rangeClosed(1, 11).boxed().toArray(Integer[]::new);

        LinkedList<Integer> possibleValues = new LinkedList<>(Arrays.asList(RAW_VALUES));

        for (int i = 0; i < COUNT; i++) {
            final int RIGHT_BOUND = MAX - i - 1;
            final int INDEX = randomInt(0, RIGHT_BOUND);
            possibleValues.remove(INDEX);
        }

        System.out.printf("Trial %02d: %s\n", id, possibleValues.toString());
    }

    private static int randomInt(int min, int max) {
        return new Random().nextInt(min - max + 1) + min;
    }
}