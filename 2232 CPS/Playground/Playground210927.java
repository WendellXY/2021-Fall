import java.util.Arrays;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class Playground210927 {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        // add new element to the set
        set.add(1);
        set.add(22);
        set.add(32);
        set.add(45);
        set.add(0);
        set.add(10);
        // remove all elements in the set
        // set.clear();
        System.out.println("Does the set contains 1? " + set.contains(1));
        System.out.println("Does the set contains 11?" + set.contains(11));
        // get the size of the set
        System.out.println("The size of the set is " +set.size());
        // print out all elements in the set
        System.out.println(set);

        for (int element: set) {
            System.out.print(element + " ");
        }

        System.out.print("\n");

        NavigableSet<Integer> treeSet = new TreeSet<>();

        treeSet.add(1);
        treeSet.add(22);
        treeSet.add(32);
        treeSet.add(45);
        treeSet.add(0);
        treeSet.add(10);

        System.out.println(treeSet);

        for (int element: treeSet) {
            System.out.print(element + " ");
        }

        System.out.print("\n");

        Set<Integer> setA = new TreeSet<>(
            Arrays.asList(1,2,3,4,6,7,9,22,32,89,90)
        );
        Set<Integer> setB = new TreeSet<>(
            Arrays.asList(1,3,5,7,8,10,11,20,21,32,70,90)
        );

        Set<Integer> setC = new TreeSet<>(setA);
        Set<Integer> setD = new TreeSet<>(setA);

        setC.addAll(setB);
        System.out.println("A + B: " + setC);
        setD.retainAll(setB);
        System.out.println("A ^ B: " + setD);

        treeSet.ceiling(1);
        treeSet.pollFirst();
        
        treeSet.lower(1);
    }
}