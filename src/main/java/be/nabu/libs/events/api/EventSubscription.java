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
