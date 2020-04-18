package fr.pederobien.minecraftdictionary.exceptions;

import fr.pederobien.minecraftdictionary.interfaces.IMessageEvent;

public abstract class AbstractDictionaryException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private IMessageEvent event;

	protected AbstractDictionaryException(IMessageEvent event) {
		this.event = event;
	}

	/**
	 * @return An array of arguments used for the not returned message.
	 */
	public IMessageEvent getEvent() {
		return event;
	}
}
