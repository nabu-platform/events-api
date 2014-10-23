package be.nabu.libs.events.api;

/**
 * Exceptions thrown by the event handler should also be passed to the response handler
 * This will prevent memory leaks in case state is kept (e.g. BufferedResponseHandler)
 * For the same reason "null" values returned by event handlers should also be pushed to the response handler
 */
public interface EventDispatcher {
	
	/**
	 * Fires an event (may be done asynchronously as no response is expected)
	 * @param <E>
	 * @param event
	 * @param source
	 */
	public <E> void fire(E event, Object source);
	
	/**
	 * Fires an event synchronously and returns a response
	 * @param <E>
	 * @param <R>
	 * @param event
	 * @param source
	 * @param responseHandler
	 * @return
	 */
	public <E, R> R fire(E event, Object source, ResponseHandler<E, R> responseHandler);
	
	/**
	 * Subscribe to a specific event type from zero or more specific source objects with the given event handler
	 * If no specific objects are indicated, all events of the given type are subscribed to
	 * @param eventType
	 * @param eventHandler
	 * @param sources
	 */
	public <E, R> EventSubscription<E, R> subscribe(Class<E> eventType, EventHandler<E, R> eventHandler, Object...sources);
	
	/**
	 * Filter a specific event type from zero or more specific source objects with the given filter
	 * The filter must return a boolean which means:
	 * - true & null: continue
	 * - false: filter event
	 * 
	 * @param eventType
	 * @param filter
	 * @param sources
	 */
	public <E> EventSubscription<E, Boolean> filter(Class<E> eventType, EventHandler<E, Boolean> filter, Object...sources);
	
}
