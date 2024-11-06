/*
* Copyright (C) 2014 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

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
