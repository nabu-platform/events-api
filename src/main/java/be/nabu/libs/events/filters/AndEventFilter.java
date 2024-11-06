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

package be.nabu.libs.events.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.nabu.libs.events.api.EventHandler;

public class AndEventFilter<E> implements EventHandler<E, Boolean> {

	private List<EventHandler<E, Boolean>> handlers = new ArrayList<EventHandler<E, Boolean>>();
	
	public AndEventFilter(EventHandler<E, Boolean>...handlers) {
		this.handlers.addAll(Arrays.asList(handlers));
	}
	
	@Override
	public Boolean handle(E event) {
		// all filters must allow the event for it to pass
		for (EventHandler<E, Boolean> handler : handlers) {
			Boolean response = handler.handle(event);
			if (response != null && response) {
				return true;
			}
		}
		return false;
	}

}
