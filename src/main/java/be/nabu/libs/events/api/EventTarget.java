package be.nabu.libs.events.api;

public interface EventTarget {
	/**
	 * Fires an event (may be done asynchronously as no response is expected)
	 * There is no overloaded version with a rewrite handler as this can be very hard to achieve in an asynchronous environment
	 */
	public <E> void fire(E event, Object source);
}
