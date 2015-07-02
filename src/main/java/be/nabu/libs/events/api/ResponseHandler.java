package be.nabu.libs.events.api;

public interface ResponseHandler<E, R> {
	/**
	 * Allows you to handle a response from an event handler
	 * You may wish to interpret the response or alter it in other ways (buffer, merge,...)
	 * Note that the response may be of the type Exception if one was thrown by the event handler and the dispatcher supports it
	 * 
	 * @param event
	 * @param response All responses (including "null") are sent to the handler
	 * @param isLast Whether or not this is the last response for the given event
	 */
	public R handle(E event, Object response, boolean isLast);
}
