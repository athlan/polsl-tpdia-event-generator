package pl.polsl.tpdia.mal.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import pl.polsl.tpdia.mal.IAggregateKey;

public class TuppleKey implements IAggregateKey, Cloneable {

	public int venueId;

	public long timestamp;
	
	public long timestampGranulity;

	public TuppleKey clone() {
		try {
			return (TuppleKey) super.clone();
		} catch (Exception e) {
			return null;
		}
	}
	
	protected static SimpleDateFormat toStringFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("venueId = " + venueId + "; ");
		sb.append("timestampGranulity = " + timestampGranulity + "; ");
		sb.append("timestamp = " + toStringFormat.format(new Date(timestamp)) + "; ");
		return sb.toString();
	}
}
