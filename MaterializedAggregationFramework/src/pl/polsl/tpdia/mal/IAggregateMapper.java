package pl.polsl.tpdia.mal;

public interface IAggregateMapper<A extends Aggregate<? extends IAggregateKey, ? extends IAggregateValue>> {

	public A map(Object o);
}
