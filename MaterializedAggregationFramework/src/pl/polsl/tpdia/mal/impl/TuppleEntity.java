package pl.polsl.tpdia.mal.impl;

import pl.polsl.tpdia.mal.Aggregate;

public class TuppleEntity extends Aggregate<TuppleKey, TuppleValue> {

	public TuppleEntity(final TuppleKey key, final TuppleValue value) {
		super(key, value);
	}
}
