package be.nabu.libs.events.api;

/**
 * Exceptions thrown by the event handler should also be passed to the response handler
 * This will prevent memory leaks in case state is kept (e.g. BufferedResponseHandler)
 * For the same reason "null" values returned by event handlers should also be pushed to the response handler
 */
public interface EventDispatcher extends EventTarget {
	/**
	 * Fires an event synchronously and returns a response
	 */
	public <E, R> R fire(E event, Object source, ResponseHandler<E, R> responseHandler);
	
	/**
	 * Fires an event synchronously and returns a response, also allows for rewriting the original event
	 * Note that any implementation should first check the response handler after the subscription returns something and only if it returns null, check if the rewrite handler can use it to rewrite the event
	 */
	public <E, R> R fire(E event, Object source, ResponseHandler<E, R> responseHandler, ResponseHandler<E, E> rewriteHandler);
	
	/**
	 * Subscribe to a specific event type from zero or more specific source objects with the given event handler
	 * If no specific objects are indicated, all events of the given type are subscribed to
	 */
	public <E, R> EventSubscription<E, R> subscribe(Class<E> eventType, EventHandler<E, R> eventHandler, Object...sources);
	
	/**
	 * Filter a specific event type from zero or more specific source objects with the given filter
	 * The filter must return a boolean which means:
	 * - true & null: continue
	 * - false: filter event
	 */
	public <E> EventSubscription<E, Boolean> filter(Class<E> eventType, EventHandler<E, Boolean> filter, Object...sources);
	
}
