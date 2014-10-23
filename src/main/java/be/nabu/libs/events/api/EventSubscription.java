package be.nabu.libs.events.api;

/**
 * TODO: a way to prioritize subscriptions (e.g. promote() & demote())
 *
 * @param <E>
 * @param <R>
 */
public interface EventSubscription<E, R> {
	
	/**
	 * Get the handler for this subscription
	 * @return
	 */
	public EventHandler<E, R> getHandler();
	
	/**
	 * Get the event type that this subscription is interested in
	 * @return
	 */
	public Class<E> getEventType();
	
	/**
	 * Allows you to filter a specific subscription in which case it is skipped for the event in question
	 * @param filter
	 */
	public void filter(EventHandler<E, Boolean> filter);
	
	/**
	 * Removes this subscription from its event dispatcher
	 */
	public void unsubscribe();
}
