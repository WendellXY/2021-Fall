public class Playground211026 {
    private static void printSquares(int n) {
        int square = 0, prev_x = 0;

        for (int x = 0; x < n; x++) {
            square = square + x + prev_x;

            System.out.printf("%d^%d = %d\n", x, 2, square);

            prev_x = x;
        }
    }

    private static void printCubes(int n) {
        int cube = 0, prev_x = 0;
        int square = 0, prev_square = 0;

        for (int x = 0; x < n; x++) {
            prev_square = square;

            square = square + x + prev_x;

            cube = cube + (prev_square << 1) + square + prev_x;

            System.out.printf("%d^%d = %d\n", x, 3, cube);

            prev_x = x;
        }
    }

    public static void main(String[] args) {
        printSquares(10);
        printCubes(10);
    }
}