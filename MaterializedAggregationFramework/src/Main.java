import java.util.Date;

import pl.polsl.tpdia.mal.impl.TuppleKey;
import pl.polsl.tpdia.mal.impl.TuppleToAggregateMapperGranulity;
import pl.polsl.tpdia.mal.impl.querybuilder.daterange.QueryBuilderKeysByRange;
import pl.polsl.tpdia.mal.impl.querybuilder.daterange.QueryBuilderKeysByRangeKeysProducerByGranulity;
import pl.polsl.tpdia.mal.querybuilder.daterange.DateRangeKeysProducerManager;


public class Main {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		DateRangeKeysProducerManager rangesProducerManager = new DateRangeKeysProducerManager();
		rangesProducerManager.addDateRangeKeysProducer(
			new QueryBuilderKeysByRangeKeysProducerByGranulity(
				TuppleToAggregateMapperGranulity.DAY
			)
		);
		rangesProducerManager.addDateRangeKeysProducer(
			new QueryBuilderKeysByRangeKeysProducerByGranulity(
				TuppleToAggregateMapperGranulity.MONTH
			)
		);
		
		
		long dateRangeStartTimestamp = ((new Date(2014 - 1900, 11, 25)).getTime());
		long dateRangeStopTimestamp = ((new Date(2015 - 1900, 02, 05)).getTime());
		QueryBuilderKeysByRange qb = new QueryBuilderKeysByRange(rangesProducerManager, dateRangeStartTimestamp, dateRangeStopTimestamp);
		
		for (TuppleKey key : qb.getKeys()) {
			System.out.println(key);
		}
	}

}
