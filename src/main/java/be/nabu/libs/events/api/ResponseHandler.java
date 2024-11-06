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
