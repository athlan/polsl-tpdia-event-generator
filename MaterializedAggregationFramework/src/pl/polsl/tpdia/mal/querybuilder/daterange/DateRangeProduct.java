package pl.polsl.tpdia.mal.querybuilder.daterange;

import java.util.ArrayList;
import java.util.List;

import pl.polsl.tpdia.mal.IAggregateKey;

/**
 * Represents processed (or not) date range.
 * 
 * @author Athlan
 *
 */
public class DateRangeProduct {
	public long start;
	public long stop;
	
	public long producerRange;
	
	public boolean isProcessed;
	
	public List<IAggregateKey> keys;

	public DateRangeProduct(long start, long stop) {
		this(start, stop, false);
	}
	
	public DateRangeProduct(long start, long stop, boolean isProcessed) {
		this.start = start;
		this.stop = stop;
		this.isProcessed = isProcessed;
		this.keys = new ArrayList<IAggregateKey>();
	}
}
