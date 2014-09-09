package pl.polsl.tpdia.mal.impl;

import pl.polsl.tpdia.mal.IAggregateKey;

public class TuppleKey implements IAggregateKey, Cloneable {

	public int venueId;

	public int timestamp;

	public TuppleKey clone() {
		try {
			return (TuppleKey) super.clone();
		} catch (Exception e) {
			return null;
		}
	}
}
