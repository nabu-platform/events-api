package be.nabu.libs.events.api;

/**
 * Use java.lang.Void if the return type does not concern you
 */
public interface EventHandler<E, R> {
	public R handle(E event);
}
