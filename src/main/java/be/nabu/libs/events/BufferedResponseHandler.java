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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.nabu.libs.events.api.ResponseHandler;

/**
 * Buffers all responses of a certain type and sends back a list of them when the event has been processed by the entire chain 
 */
public class BufferedResponseHandler<E, R> implements ResponseHandler<E, List<R>> {

	private Map<E, List<R>> buffer = new HashMap<E, List<R>>();
	
	private Class<R> responseType;
	
	private boolean stopOnException = true;
	
	public BufferedResponseHandler(Class<R> responseType) {
		this.responseType = responseType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<R> handle(E event, Object response, boolean isLast) {
		if (response != null && responseType.isAssignableFrom(response.getClass())) {
			if (!buffer.containsKey(event))
				buffer.put(event, new ArrayList<R>());
			buffer.get(event).add((R) response);
		}
		return isLast || (stopOnException && response instanceof Exception) ? buffer.remove(event) : null;
	}

	public boolean isStopOnException() {
		return stopOnException;
	}

	public void setStopOnException(boolean stopOnException) {
		this.stopOnException = stopOnException;
	}
}
