package pl.polsl.tpdia.mal.impl;

public enum TuppleToAggregateMapperGranulity {
	
	MINUTE {
		public int getAlignedTimestamp(int timestamp) {
			return (timestamp / 60) * 60; // time aligned to 60 secs
		}
	},
	HOUR {
		public int getAlignedTimestamp(int timestamp) {
			return (timestamp / 3600) * 3600; // time aligned to 3600 secs
		}
	},
	DAY {
		public int getAlignedTimestamp(int timestamp) {
			return (timestamp / 86400) * 86400; // time aligned to 3600 secs
		}
	},
	MONTH {
		public int getAlignedTimestamp(int timestamp) {
			return 0;
		}
	},
	YEAR {
		public int getAlignedTimestamp(int timestamp) {
			return 0;
		}
	};
	
	public abstract int getAlignedTimestamp(int timestamp);
}
