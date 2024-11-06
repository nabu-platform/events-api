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

package be.nabu.libs.events;

import be.nabu.libs.events.api.EventDispatcher;
import be.nabu.libs.events.api.EventHandler;
import be.nabu.libs.events.api.EventSubscription;
import be.nabu.libs.events.api.ResponseHandler;

public class EventDispatcherStub implements EventDispatcher {

	@Override
	public <E> void fire(E event, Object source) {
		// do nothing
	}

	@Override
	public <E, R> R fire(E event, Object source, ResponseHandler<E, R> responseHandler) {
		return null;
	}

	@Override
	public <E, R> EventSubscription<E, R> subscribe(Class<E> eventType, EventHandler<E, R> handler, Object... sources) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <E> EventSubscription<E, Boolean> filter(Class<E> eventType, EventHandler<E, Boolean> filter, Object... sources) {
		throw new UnsupportedOperationException();
	}

	@Override
	public <E, R> R fire(E event, Object source, ResponseHandler<E, R> responseHandler, ResponseHandler<E, E> rewriteHandler) {
		return null;
	}
	
}