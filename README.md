# What is it

This eventing API is based on a number of existing systems and adds a few twists. 
Two specific details may set it apart from other implementations.

## Cross-system Events

There is a focus on cross-system events which means the receiver does not necessarily have access to the environment that spawned the event.
This has two specific consequences:

- The event recipient does not get any contextual information (e.g. access to the source object like java.util.Observer) because this would require massive serialization
- The event recipient may not be able to get access to the necessary objects to react to the event

## Synchronous feedback

In a lot of cases you want the recipient to be able to influence the original sender.

For example you could plug in a validation routine which wants to be able to stop the save() method if the content is invalid.

Another example could be a pluggable proxy filter where you evaluate real-time which connections must be stopped.
In this case an asynchronous fire does not work as you have no way to interact with the method invocation.

# Design

To solve both issues in the above, the API provides a fire() method where the sender can clarify a response handler. 

Any response sent back by an event handler is sent to the response handler who can decide whether or not this is an appropriate response.

```java
public interface EventDispatcher {
	// this can be (but doesn't have to be) asynchronous
	public <E> void fire(E event, Object source);
	// this is always synchronous and will return a response
	public <E, R> R fire(E event, Object source, ResponseHandler<E, R> responseHandler);
}
```

# How to use

- SPI: be.nabu.libs.events.SPIEventDispatcher
- OSGi: a stub implementation is provided which does nothing on fire() and fails on subscribe(). It has a service ranking of 0 so make sure custom implementations have a higher ranking.

The easiest way to get an event dispatcher is from the factory:

```java
EventDispatcherFactory.getInstance().getEventDispatcher();
```

Which event dispatcher is returned depends on whether OSGi is enabled and/or what SPI can find. 
The returned dispatcher however is shared by anyone who calls this method.

Note however that you can obviously also create your own instances to have an event dispatcher with a smaller scope.
