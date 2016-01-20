package be.nabu.libs.events.filters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import be.nabu.libs.events.api.EventHandler;

public class OrEventFilter<E> implements EventHandler<E, Boolean> {

	private List<EventHandler<E, Boolean>> handlers = new ArrayList<EventHandler<E, Boolean>>();
	
	public OrEventFilter(EventHandler<E, Boolean>...handlers) {
		this.handlers.addAll(Arrays.asList(handlers));
	}
	
	@Override
	public Boolean handle(E event) {
		// if any filter allows the event, we should let it pass
		for (EventHandler<E, Boolean> handler : handlers) {
			Boolean response = handler.handle(event);
			if (response == null || !response) {
				return false;
			}
		}
		return true;
	}

}
