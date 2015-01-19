package pl.polsl.tpdia.mal.impl.querybuilder.daterange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.polsl.tpdia.mal.impl.TuppleToAggregateMapperGranulity;
import pl.polsl.tpdia.mal.querybuilder.daterange.DateRangeProduct;
import pl.polsl.tpdia.mal.querybuilder.daterange.IDateRangeKeysProducer;

/**
 * Produces aggregate tuples keys based on time granulity.
 * 
 * @see TuppleToAggregateMapperGranulity
 * 
 * @author Athlan
 *
 */
public class QueryBuilderKeysByRangeKeysProducerByGranulity implements IDateRangeKeysProducer {

	final public TuppleToAggregateMapperGranulity granulity;
	
	public QueryBuilderKeysByRangeKeysProducerByGranulity(TuppleToAggregateMapperGranulity granulity) {
		this.granulity = granulity;
	}
	
	@Override
	public long getRangeSpan() {
		return granulity.getGranulityRange();
	}

	@Override
	public List<DateRangeProduct> splitRange(DateRangeProduct range) {
		List<DateRangeProduct> result = new ArrayList<DateRangeProduct>();
		
		long start = range.start;
		long stop = granulity.getAlignedTimestamp(range.stop);
		
		if(start != granulity.getAlignedTimestamp(start)) {
			start = granulity.getAlignedTimestampFirstNext(start);
			
			// prevent next call will generate no elements
			if(granulity.getAlignedTimestampFirstNext(start) > range.stop) {
				return Collections.emptyList();
			}
			
			// generate first split from left side
			DateRangeProduct split = new DateRangeProduct(range.start, start);
			result.add(split);
		}
		
		long curr = start;
		long next = start;
		while (curr < stop) {
			next = granulity.getAlignedTimestampFirstNext(curr);
			
			// prevent the case that next token will be out of range
			if(next > stop)
				break;
			
			DateRangeProduct split = new DateRangeProduct(curr, next, true);
			result.add(split);
			
			curr = next;
		}
		
		// generate first split from right side
		if(next != range.stop) {
			DateRangeProduct split = new DateRangeProduct(next, range.stop);
			result.add(split);
		}
		
		return result;
	}

}
