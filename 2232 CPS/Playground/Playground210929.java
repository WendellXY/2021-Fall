import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Playground210929 {
    public static void main(String[] args) {
        System.out.println("Hello World");
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Smith", 100);
        hashMap.put("Anderson", 91);
        hashMap.put("Lewis", 99);
        hashMap.put("Cook", 89);
        System.out.println("Display entries in HashMap");
        System.out.println(hashMap + "\n");

        // Create a TreeMap from the preceding HashMap
        Map<String, Integer> treeMap = new TreeMap<>(hashMap);
        System.out.println("Display entries in ascending order of ket.");
        System.out.println(treeMap);

        // Create a LinkedHashMap
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);

        linkedHashMap.put("Smith", 30);
        linkedHashMap.put("Anderson", 31);
        linkedHashMap.put("Lewis", 29);
        linkedHashMap.put("Cook", 29);

        // DIsplay the grade for Lewis
        System.out.println("\nThe grade for Lewis is " + treeMap.get("Lewis"));

        // Display the age for Lewis
        System.out.println("\nThe age for " + "Lewis is " + linkedHashMap.get("Lewis"));
        System.out.println("Display entries in LinkedHashMap");
        System.out.println(linkedHashMap);

        // Display each entry with name and age
        System.out.print("\nNames and grades are ");
        treeMap.forEach((name, grade) -> System.out.print(name + ": " + grade + " "));
    }
}