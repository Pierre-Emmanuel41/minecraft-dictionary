package fr.pederobien.minecraftdictionary.exceptions;

import fr.pederobien.minecraftdictionary.interfaces.IDictionary;
import fr.pederobien.minecraftdictionary.interfaces.IMessageEvent;

public abstract class AbstractDictionaryMessageException extends AbstractDictionaryException {
	private static final long serialVersionUID = 1L;
	private IDictionary dictionary;

	protected AbstractDictionaryMessageException(IMessageEvent event, IDictionary dictionary) {
		super(event);
		this.dictionary = dictionary;
	}

	/**
	 * @return The dictionary in which the messages is registered.
	 */
	public IDictionary getDictionary() {
		return dictionary;
	}
}
