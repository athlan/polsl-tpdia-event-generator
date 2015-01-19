package pl.polsl.tpdia.mal.querybuilder.daterange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Manager provides the flow of @see {@link IDateRangeKeysProducer}
 * to produce @see {@link DateRangeProduct} objects as wide as possible.
 * 
 * These ranges can be used as tuples keys for aggregated data for query
 * database to access already computed data.
 * 
 * @author Athlan
 *
 */
public class DateRangeKeysProducerManager {

	protected List<IDateRangeKeysProducer> dateRangeKeysProducer;
	
	public DateRangeKeysProducerManager() {
		dateRangeKeysProducer = new ArrayList<IDateRangeKeysProducer>();
	}
	
	public List<DateRangeProduct> getRanges(long dateRangeStartTimestamp, long dateRangeStopTimestamp) {
		List<DateRangeProduct> ranges = new ArrayList<DateRangeProduct>();
		ranges.add(new DateRangeProduct(dateRangeStartTimestamp, dateRangeStopTimestamp));
		
		for (IDateRangeKeysProducer producer : dateRangeKeysProducer) {
			
			List<DateRangeProduct> rangesModificationsAdd = new ArrayList<DateRangeProduct>();
			List<DateRangeProduct> rangesModificationsDel = new ArrayList<DateRangeProduct>();
			
			for (DateRangeProduct range : ranges) {
				if(range.isProcessed == true) {
					continue; // nothing to do here...
				}
				
				List<DateRangeProduct> rangeSplitted = producer.splitRange(range);
				
				if(rangeSplitted.size() > 0) {
					for (DateRangeProduct dateRangeProduct : rangeSplitted) {
						dateRangeProduct.producerRange = producer.getRangeSpan();
					}
					
					rangesModificationsAdd.addAll(rangeSplitted);
					rangesModificationsDel.add(range); // remove range, it is splitted
				}
			}
			
			ranges.removeAll(rangesModificationsDel);
			rangesModificationsDel.clear();
			
			ranges.addAll(rangesModificationsAdd);
			rangesModificationsAdd.clear();
		}
		
		return ranges;
	}

	public void addDateRangeKeysProducer(IDateRangeKeysProducer producer) {
		dateRangeKeysProducer.add(producer);
		
		// sort producers descending because chain-of-responsibility
		// ordered from widest range to the smallest
		Collections.sort(dateRangeKeysProducer, new Comparator<IDateRangeKeysProducer>() {

			@Override
			public int compare(IDateRangeKeysProducer o1, IDateRangeKeysProducer o2) {
				return Long.compare(o1.getRangeSpan(), o2.getRangeSpan()) * -1;
				// * -1 because of descending
			}
		});
	}
	
	public void removeDateRangeKeysProducer(IDateRangeKeysProducer producer) {
		dateRangeKeysProducer.remove(producer);
	}
}
