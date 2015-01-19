package pl.polsl.tpdia.mal.querybuilder;

import java.util.List;

import pl.polsl.tpdia.mal.IAggregateKey;

/**
 * Generic keys query builder.
 * 
 * Provides the list of keys that should be queried database
 * to gather already materialized tuples.
 * 
 * @author Athlan
 *
 * @param <K>
 */
public interface IQueryBuilderKeys<K extends IAggregateKey> {
	
	/**
	 * Get all keys that should be queried database
	 * to gather already materialized tuples.
	 * 
	 * @return
	 */
	public List<K> getKeys();
}
