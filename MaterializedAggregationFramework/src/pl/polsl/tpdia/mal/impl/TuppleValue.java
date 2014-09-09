package pl.polsl.tpdia.mal.impl;

import pl.polsl.tpdia.mal.IAggregateValue;

public class TuppleValue implements IAggregateValue, Cloneable {

	public float fuelLitersSold;

	public TuppleValue clone() {
		try {
			return (TuppleValue) super.clone();
		} catch (Exception e) {
			return null;
		}
	}
}
