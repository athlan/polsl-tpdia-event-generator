package pl.polsl.tpdia.mal.impl;

import java.util.Calendar;

public enum TuppleToAggregateMapperGranulity {
	
	MINUTE {
		@Override
		public long getAlignedTimestamp(long timestamp) {
			return (timestamp / getGranulityRange()) * getGranulityRange(); // time aligned to 60 secs
		}
		
		@Override
		public long getAlignedTimestampFirstNext(long timestamp) {
			return (timestamp / getGranulityRange() + 1) * getGranulityRange(); // time aligned to 60 secs
		}

		@Override
		public long getGranulityRange() {
			return 60000L;
		}
	},
	HOUR {
		@Override
		public long getAlignedTimestamp(long timestamp) {
			return (timestamp / getGranulityRange()) * getGranulityRange(); // time aligned to 3600 secs
		}

		@Override
		public long getAlignedTimestampFirstNext(long timestamp) {
			return (timestamp / getGranulityRange() + 1) * getGranulityRange(); // time aligned to 60 secs
		}

		@Override
		public long getGranulityRange() {
			return 3600000;
		}
	},
	DAY {
		public long getAlignedTimestamp(long timestamp) {
			return (timestamp / getGranulityRange()) * getGranulityRange(); // time aligned to 86400 secs
		}

		@Override
		public long getAlignedTimestampFirstNext(long timestamp) {
			return (timestamp / getGranulityRange() + 1) * getGranulityRange(); // time aligned to 60 secs
		}

		@Override
		public long getGranulityRange() {
			return 86400000L;
		}
	},
	MONTH {
		public long getAlignedTimestamp(long timestamp) {
			Calendar date = Calendar.getInstance();
			date.setTimeInMillis(timestamp);

			date.set(Calendar.DAY_OF_MONTH, 1);
			date.set(Calendar.HOUR_OF_DAY, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MILLISECOND, 0);
			
			return date.getTimeInMillis();
		}

		@Override
		public long getAlignedTimestampFirstNext(long timestamp) {
			Calendar date = Calendar.getInstance();
			date.setTimeInMillis(timestamp);

			date.set(Calendar.DAY_OF_MONTH, 1);
			date.set(Calendar.HOUR_OF_DAY, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MILLISECOND, 0);
			
			date.add(Calendar.MONTH, 1);
			
			return date.getTimeInMillis();
		}

		@Override
		public long getGranulityRange() {
			return 2592000000L; // 30 days, this is for sorting only
		}
	};

	public abstract long getAlignedTimestamp(long timestamp);

	public abstract long getAlignedTimestampFirstNext(long timestamp);
	
	public abstract long getGranulityRange();
}
