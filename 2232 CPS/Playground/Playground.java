public class Playground {
    public static void main(String[] args) {
		System.out.println("\nCode of Problem 25.2 p.1006: ");
		// Source code of BST is in the file named BST.java
		Integer[] test1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		Integer[] test2 = {4, 2, 1, 3, 8, 5, 9};
		BST<Integer> testTree1 = new BST<>(test1);
		BST<Integer> testTree2 = new BST<>(test2);

		System.out.print("Test 1: ");
		System.out.println(testTree1.isPerfectBST());
		System.out.print("Test 2: ");
		System.out.println(testTree2.isPerfectBST());

		System.out.println("\nCode of Problem 27.1 p.1058: ");
		// Source code of BST is in the file named BST.java
		MyMap<String, Integer> asciiTable = new MyHashMap<>();

		asciiTable.put("A", 65);
		asciiTable.put("B", 66);
		asciiTable.put("C", 67);
		asciiTable.put("a", 97);
		asciiTable.put("b", 98);
		asciiTable.put("c", 99);

		System.out.println("Entries in map: " + asciiTable);

		System.out.println("The ASCII code of A is " + asciiTable.get("A"));

		System.out.println("The ASCII code of B is " + asciiTable.get("B"));

		System.out.println("The ASCII code of a is " + asciiTable.get("a"));

		System.out.println("The ASCII code of b is " + asciiTable.get("b"));

		System.out.println("Is code 65 in the table? " + asciiTable.containsValue(65));

		System.out.println("Is code 99 in the table? " + asciiTable.containsValue(99));

		System.out.print("Keys in table: ");
		for (String key : asciiTable.keySet())
			System.out.print(key + " ");

		System.out.println();

		System.out.print("Values in table: ");
		for (int value : asciiTable.values()) {
			System.out.print(value + " ");
		}
		System.out.println();

		asciiTable.remove("A");
		System.out.println("Entries in table after removed A: " + asciiTable);

		asciiTable.clear();
		System.out.println("Entries in table after clear the whole table: " + asciiTable);
    }
}