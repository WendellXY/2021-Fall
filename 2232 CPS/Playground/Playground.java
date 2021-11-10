public class Playground {
	static final int DATA_LENGTH = 1000000;

	public static void main(String[] args) {
		MyHashSet<Double> myHashSet = new MyHashSet<>();
		MyArrayList<Double> myArrayList = new MyArrayList<>();

		for (int i = 0; i < DATA_LENGTH; i++) {
			double randomDouble = Math.random() * DATA_LENGTH;
			myHashSet.add(randomDouble);
			myArrayList.add(randomDouble);
		}

		double[] list = new double[DATA_LENGTH];
		for (int i = 0; i < list.length; i++)
			list[i] = Math.random() * DATA_LENGTH;


		System.out.println("HashSet Time Cost: " + testTimeSet(list, myHashSet) + " ms");

		System.out.println("ArrayList Time Cost: " + testTimeArray(list, myArrayList) + " ms");
	}

	public static long testTimeSet(double[] list, MyHashSet<Double> set) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < DATA_LENGTH; i++)
			set.contains(list[i]);

		return System.currentTimeMillis() - startTime;
	}

	public static long testTimeArray(double[] list, MyArrayList<Double> array) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < DATA_LENGTH; i++)
			array.contains(list[i]);

		return System.currentTimeMillis() - startTime;
	}
}