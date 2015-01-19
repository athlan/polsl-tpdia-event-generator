package pl.polsl.tpdia.mal.impl;

import pl.polsl.tpdia.mal.IAggregateMapper;

public class TuppleToAggregateMapper implements IAggregateMapper<TuppleEntity> {
	
	final public TuppleToAggregateMapperGranulity granulity;
	
	public TuppleToAggregateMapper(TuppleToAggregateMapperGranulity granulity) {
		this.granulity = granulity;
	}

	@Override
	public TuppleEntity map(Object o) {
		if(o instanceof TuppleEntity) {
			TuppleEntity item = (TuppleEntity) o;
			
			TuppleKey key = item.getKey().clone();
			key.timestamp = granulity.getAlignedTimestamp(key.timestamp);
			key.timestampGranulity = granulity.getGranulityRange();
			
			return new TuppleEntity(key, item.getValue().clone());
		}
		
		return null;
	}
}
