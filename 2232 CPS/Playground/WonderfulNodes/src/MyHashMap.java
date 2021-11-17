import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class MyHashMap<K, V> implements MyMap<K, V> {

    private static final int DEFAULT_CAPACITY = 4;

    private static final int MAX_CAPACITY = 1 << 30;

    private int capacity;

    private static final float DEFAULT_MAX_LOAD_FACTOR = 0.5f;

    private final float loadFactorThreshold;

    private int size = 0;

    ArrayList<MyMap.Entry<K, V>> table;

    public MyHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFactorThreshold) {
		if (initialCapacity > MAX_CAPACITY)
			this.capacity = MAX_CAPACITY;
		else
			this.capacity = trimToPowerOf2(initialCapacity);

		this.loadFactorThreshold = loadFactorThreshold;
		table = new ArrayList<>();
		for (int i = 0; i < capacity; i++)
			table.add(null);
	}

    /** Return a power of 2 for initialCapacity */
	private int trimToPowerOf2(int initialCapacity) {
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
	public boolean containsKey(K key) {
		return get(key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		for (int i = 0; i < capacity; i++)
			if (table.get(i) != null && table.get(i).getValue() == value)
				return true;

		return false;
	}

	@Override
    public Set<MyMap.Entry<K,V>> entrySet() {
		Set<MyMap.Entry<K, V>> set = new HashSet<>();

		for (int i = 0; i < capacity; i++)
			if (table.get(i) != null)
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
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<>();

		for (int i = 0; i < capacity; i++)
			if (table.get(i) != null)
				set.add(table.get(i).getKey());

		return set;
	}

	@Override
	public V put(K key, V value) {
		int index = hash(key.hashCode());

		while (table.get(index) != null) {
			// The key is already in the map
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

		if (size >= capacity * loadFactorThreshold) {
			if (capacity == MAX_CAPACITY)
				throw new RuntimeException("Exceeding maximum capacity");
			rehash();
		}

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
	public int size() { return size; }

	@Override
	public Set<V> values() {
		Set<V> set = new HashSet<>();

		for (int i = 0; i < capacity; i++)
			if (table.get(i) != null)
				set.add(table.get(i).getValue());

		return set;
	}

	/** Hash function */
	private int hash(int hashCode) {
		return supplementalHash(hashCode) & (capacity - 1);
	}

	private static int supplementalHash(int h) {
		h ^= (h >>> 20) ^ (h >>> 12);
		return h ^ (h >>> 7) ^ (h >>> 4);
	}

    /** Rehash the map */
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

		for (Entry<K, V> entry: table)
			if (entry != null)
				builder.append(entry);

		builder.append("]");
		return builder.toString();
	}
}
