package pl.polsl.tpdia.mal;

public class Aggregate<K extends IAggregateKey, V extends IAggregateValue> {
	
	private final K key;
	private final V value;
	private boolean isValid;

	public Aggregate(final K key, final V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
