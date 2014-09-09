package pl.polsl.tpdia.mal.impl;

import java.util.List;

import pl.polsl.tpdia.mal.IAggregateReducer;

public class TupperMaterializedAggregateReducer implements IAggregateReducer<TuppleEntity, TuppleKey, TuppleValue> {

	@Override
	public TuppleEntity reduce(final TuppleKey key, final List<TuppleValue> values) {
		TuppleValue valueSum = new TuppleValue();
		
		for(TuppleValue value : values) {
			valueSum.fuelLitersSold += value.fuelLitersSold;
		}
		
		return new TuppleEntity(key, valueSum);
	}
}
