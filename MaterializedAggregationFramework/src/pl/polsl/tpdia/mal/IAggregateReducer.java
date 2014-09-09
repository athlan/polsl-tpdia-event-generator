package pl.polsl.tpdia.mal;

import java.util.List;

public interface IAggregateReducer<A extends Aggregate<K, V>, K extends IAggregateKey, V extends IAggregateValue> {

	public A reduce(final K key, final List<V> values);
}
