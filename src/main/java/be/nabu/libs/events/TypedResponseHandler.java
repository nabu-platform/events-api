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
