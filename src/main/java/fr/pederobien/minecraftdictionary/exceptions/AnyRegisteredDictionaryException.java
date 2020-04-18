package fr.pederobien.minecraftdictionary.exceptions;

import fr.pederobien.minecraftdictionary.interfaces.IMessageEvent;

public class AnyRegisteredDictionaryException extends AbstractDictionaryException {
	private static final long serialVersionUID = 1L;

	public AnyRegisteredDictionaryException(IMessageEvent event) {
		super(event);
	}

	@Override
	public String getMessage() {
		return "There is any registered dictionary for the plugin " + getEvent().getPlugin().getName();
	}
}
