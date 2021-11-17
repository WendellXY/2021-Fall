import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class MyHashMap<K, V> implements MyMap<K, V> {

    private static final int DEFAULT_CAPACITY = 6;

    private static final float DEFAULT_LOAD_FACTOR_THRESHOLD = 0.5f;

    private final float loadFactorThreshold;

	private int capacity;

    private int size = 0;

    ArrayList<MyMap.Entry<K, V>> table;

	@Override
	public int size() { return size; }

	public int getCapacity() { return capacity; }

    public MyHashMap() { this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR_THRESHOLD); }

    public MyHashMap(int initialCapacity, float loadFactorThreshold) {
		this.capacity = ToNearestPowerOf2(initialCapacity);

		this.loadFactorThreshold = loadFactorThreshold;
		table = new ArrayList<>();
		for (int i = 0; i < capacity; i++)
			table.add(null);
	}

	private int ToNearestPowerOf2(int initialCapacity) {
		int capacity = 1;

		while (capacity < initialCapacity)
			capacity <<= 1;

		return capacity;
	}

	@Override
	public void clear() {
		size = 0;
		table.clear();
	}

	@Override
	public boolean containsKey(K key) { return get(key) != null; }

	@Override
	public boolean containsValue(V value) {
		for (int i = 0; i < capacity; i++) if (table.get(i) != null && table.get(i).getValue() == value)
			return true;

		return false;
	}

	@Override
    public Set<MyMap.Entry<K,V>> entrySet() {
		Set<MyMap.Entry<K, V>> set = new HashSet<>();

		for (int i = 0; i < capacity; i++) if (table.get(i) != null)
			set.add(table.get(i));

		return set;
	}

	@Override
	public V get(K key) {
		int index = hash(key.hashCode());

		while(table.get(index) != null) {
			if (table.get(index).getKey().equals(key))
				return table.get(index).getValue();

			index++;
			index %= capacity;
		}

		return null;
	}

	@Override
	public boolean isEmpty() { return size == 0; }

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<>();

		for (int i = 0; i < capacity; i++) if (table.get(i) != null)
			set.add(table.get(i).getKey());

		return set;
	}

	@Override
	public V put(K key, V value) {
		int index = hash(key.hashCode());

		while (table.get(index) != null) {

			if (table.get(index).getKey().equals(key)) {
				Entry<K, V> entry = table.get(index);
				V oldValue = entry.getValue();

				entry.value = value;
				table.set(index, entry);

				return oldValue;
			}

			index++;
			index %= capacity;
		}

		if (size >= capacity * loadFactorThreshold)
			rehash();


		table.set(index, new MyMap.Entry<>(key, value));

		size++;

		return value;
	}

	@Override
	public void remove(K key) {
		int index = hash(key.hashCode());

		while (table != null) {
			if (table.get(index).getKey().equals(key)) {
				table.remove(index);
				size--;
				break;
			}
			index++;
			index %= capacity;
		}
	}

	@Override
	public Set<V> values() {
		Set<V> set = new HashSet<>();

		for (int i = 0; i < capacity; i++) if (table.get(i) != null)
			set.add(table.get(i).getValue());

		return set;
	}

	private int hash(int hashCode) {
		return hashCode % capacity;
	}

	private void rehash() {
		Set<Entry<K, V>> set = entrySet();
		capacity <<= 1;
		size = 0;
		table.clear();
		for (int i = 0; i < capacity; i++)
			table.add(null);

		for (Entry<K, V> entry : set)
			put(entry.getKey(), entry.getValue());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("[");

		String prefix = "";
		for (Entry<K, V> entry: table) {
			if (entry != null) {
				builder.append(prefix);
				prefix = ", ";
				builder.append(entry);
			}
		}

		builder.append("]");
		return builder.toString();
	}
}
