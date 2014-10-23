package be.nabu.libs.events;

import java.util.Iterator;
import java.util.ServiceLoader;

import be.nabu.libs.events.api.EventDispatcher;

public class EventDispatcherFactory {
	
	private static EventDispatcherFactory instance;
	
	public static EventDispatcherFactory getInstance() {
		if (instance == null)
			instance = new EventDispatcherFactory();
		return instance;
	}
	
	private EventDispatcher eventDispatcher;
	
	public EventDispatcher getEventDispatcher() {
		if (eventDispatcher == null) {
			ServiceLoader<EventDispatcher> serviceLoader = ServiceLoader.load(EventDispatcher.class);
			Iterator<EventDispatcher> iterator = serviceLoader.iterator();
			if (iterator.hasNext())
				eventDispatcher = iterator.next();
			else
				eventDispatcher = new EventDispatcherStub();
		}
		return eventDispatcher;
	}
	
	public void setEventDispatcher(EventDispatcher eventDispatcher) {
		this.eventDispatcher = eventDispatcher;
	}
	
	public void unsetEventDispatcher(EventDispatcher eventDispatcher) {
		this.eventDispatcher = null;
	}
	
	@SuppressWarnings("unused")
	private void activate() {
		instance = this;
	}
	@SuppressWarnings("unused")
	private void deactivate() {
		instance = null;
	}
}
