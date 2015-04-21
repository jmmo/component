package org.jmmo.component;

/**
 * Интерфейс для обработки некоторых объектов
 * @author Tomas Shestakov
 */
public interface Handler<T> {

	void handle(T object);
}
