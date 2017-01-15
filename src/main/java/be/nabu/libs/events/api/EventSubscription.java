package be.nabu.libs.events.api;

public interface EventSubscription<E, R> {
	
	/**
	 * Get the handler for this subscription
	 */
	public EventHandler<E, R> getHandler();
	
	/**
	 * Get the event type that this subscription is interested in
	 */
	public Class<E> getEventType();
	
	/**
	 * Allows you to filter a specific subscription in which case it is skipped for the event in question
	 */
	public void filter(EventHandler<E, Boolean> filter);
	
	/**
	 * Removes this subscription from its event dispatcher
	 */
	public void unsubscribe();
	/**
	 * Allows you to promote a subscription to the head of the list
	 */
	public void promote();
	/**
	 * Allows you to demote a subscription to the end of the list
	 */
	public void demote();
}
