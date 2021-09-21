import java.util.Arrays;

public class Playground210909 {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++)
            System.out.printf("[%02d] %d\n", i, fib(i));


        int N_PRINT_TIMES = 10;
        System.out.printf("\nTest when TIMES == %d\n", N_PRINT_TIMES);
        nPrintln("Hello World", N_PRINT_TIMES, true);

        N_PRINT_TIMES = 0;
        System.out.printf("\nTest when TIMES == %d\n", N_PRINT_TIMES);
        nPrintln("Hello World", N_PRINT_TIMES, true);

        int[] arr = {3, 1, 7, 5, 0 };
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(selectionSort(arr)));

        System.out.println(gcd(10, 6));
    }

    private static int fib(int i) {
        // if (i == 0) {
        //     return 0;
        // } else if (i == 1) {
        //     return 1;
        // } else {
        //     return fib(i - 1) + fib(i - 2);
        // }
        return i <= 1 ? i : fib(i - 1) + fib(i - 2);
    }

    private static void nPrintln(String message, int times, boolean ordered) {
        if (times > 1)
            nPrintln(message, times - 1, ordered);

        if (times <= 0)
            System.out.printf("The value is %d\n", times);
        else if (ordered)
            System.out.printf("[%02d] %s\n", times, message);
        else
            System.out.println(message);
    }

    private static boolean isPalindrome(String s) {
        if ((s.length() == 0) || (s.length() == 1)) return true;

        final char first = s.charAt(0);
        final char last = s.charAt(s.length() - 1);

        if (first != last) return false;

        s = s.substring(1, s.length() - 1);

        return isPalindrome(s);
    }

    private static int[] _selectionSort(int[] arr, int startIndex) {
        final int LENGTH = arr.length;

        if (startIndex == LENGTH - 1) return arr;

        // find the index of small value
        int indexOfSmallestValue = startIndex;

        for (int i = startIndex; i < LENGTH; ++i)
            if (arr[i] < arr[indexOfSmallestValue])
                indexOfSmallestValue = i;


        final int tmp = arr[startIndex];
        arr[startIndex] = arr[indexOfSmallestValue];
        arr[indexOfSmallestValue] = tmp;

        return _selectionSort(arr, startIndex + 1);
    }

    private static int[] selectionSort(int[] arr) {
        return _selectionSort(arr, 0);
    }

    private static int gcd(int x, int y) {
        if (y == 0) return x;

        return gcd(y, x % y);
    }
}