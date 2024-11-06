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

import be.nabu.libs.events.api.ResponseHandler;

/**
 * Returns the first response that is of a certain type
 */
public class TypedResponseHandler<E, R> implements ResponseHandler<E, R> {

	private Class<R> responseType;
	
	public TypedResponseHandler(Class<R> responseType) {
		this.responseType = responseType;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public R handle(E event, Object response, boolean isLast) {
		if (response != null && responseType.isAssignableFrom(response.getClass()))
			return (R) response;
		else
			return null;
	}
}
