package pl.polsl.tpdia.mal.impl.querybuilder.daterange;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.tpdia.mal.impl.TuppleKey;
import pl.polsl.tpdia.mal.querybuilder.IQueryBuilderKeys;
import pl.polsl.tpdia.mal.querybuilder.daterange.DateRangeKeysProducerManager;
import pl.polsl.tpdia.mal.querybuilder.daterange.DateRangeProduct;

/**
 * Implementation of query builder based on date range
 * for specific tuple key.
 * 
 * @author Athlan
 *
 */
public class QueryBuilderKeysByRange implements IQueryBuilderKeys<TuppleKey> {
	
	protected long dateRangeStartTimestamp;
	protected long dateRangeStopTimestamp;
	
	protected DateRangeKeysProducerManager dateRangeKeysProducerManager;
	
	public QueryBuilderKeysByRange(DateRangeKeysProducerManager dateRangeKeysProducerManager, long dateRangeStartTimestamp, long dateRangeStopTimestamp) {
		setDateRangeStartTimestamp(dateRangeStartTimestamp);
		setDateRangeStopTimestamp(dateRangeStopTimestamp);
		
		this.dateRangeKeysProducerManager = dateRangeKeysProducerManager;
	}
	
	@Override
	public List<TuppleKey> getKeys() {
		List<TuppleKey> result = new ArrayList<TuppleKey>();
		List<DateRangeProduct> ranges = dateRangeKeysProducerManager.getRanges(dateRangeStartTimestamp, dateRangeStopTimestamp);
		
		// collect all produced keys
		for (DateRangeProduct range : ranges) {
			if(range.isProcessed) {
				TuppleKey key = new TuppleKey();
				key.timestamp = range.start;
				key.timestampGranulity = range.producerRange;
				
				result.add(key);
			}
		}
		
		return result;
	}

	public long getDateRangeStartTimestamp() {
		return dateRangeStartTimestamp;
	}

	protected void setDateRangeStartTimestamp(long dateRangeStartTimestamp) {
		this.dateRangeStartTimestamp = dateRangeStartTimestamp;
	}

	public long getDateRangeStopTimestamp() {
		return dateRangeStopTimestamp;
	}

	protected void setDateRangeStopTimestamp(long dateRangeStopTimestamp) {
		this.dateRangeStopTimestamp = dateRangeStopTimestamp;
	}
}
