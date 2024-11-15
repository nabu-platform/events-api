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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
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
	
	@SuppressWarnings("unchecked")
	public EventDispatcher getEventDispatcher() {
		if (eventDispatcher == null) {
			try {
				// let's try this with custom service loading based on a configuration
				Class<?> clazz = getClass().getClassLoader().loadClass("be.nabu.utils.services.ServiceLoader");
				Method declaredMethod = clazz.getDeclaredMethod("load", Class.class);
				List<EventDispatcher> list = (List<EventDispatcher>) declaredMethod.invoke(null, EventDispatcher.class);
				if (!list.isEmpty()) {
					eventDispatcher = list.get(0);
				}
			}
			catch (ClassNotFoundException e) {
				// ignore, the framework is not present
			}
			catch (NoSuchMethodException e) {
				// corrupt framework?
				throw new RuntimeException(e);
			}
			catch (SecurityException e) {
				throw new RuntimeException(e);
			}
			catch (IllegalAccessException e) {
				// ignore
			}
			catch (InvocationTargetException e) {
				// ignore
			}
			if (eventDispatcher == null) {
				ServiceLoader<EventDispatcher> serviceLoader = ServiceLoader.load(EventDispatcher.class);
				Iterator<EventDispatcher> iterator = serviceLoader.iterator();
				if (iterator.hasNext()) {
					eventDispatcher = iterator.next();
				}
				else {
					eventDispatcher = new EventDispatcherStub();
				}
			}
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
