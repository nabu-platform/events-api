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
	
}