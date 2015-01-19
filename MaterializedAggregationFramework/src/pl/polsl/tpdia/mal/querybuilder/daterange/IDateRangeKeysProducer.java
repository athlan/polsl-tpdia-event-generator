package pl.polsl.tpdia.mal.querybuilder.daterange;

import java.util.List;

/**
 * DateRange producer interface.
 * 
 * Ranges can be spliced to smaller ranges by keys producers.
 * Used by @see {@link DateRangeKeysProducerManager} to provide
 * as wide ranges as possible without gaps.
 * 
 * @author Athlan
 *
 */
public interface IDateRangeKeysProducer {
	
	public long getRangeSpan();
	
	public List<DateRangeProduct> splitRange(DateRangeProduct range);
}
