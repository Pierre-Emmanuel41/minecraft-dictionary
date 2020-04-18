package fr.pederobien.minecraftdictionary.exceptions;

import java.util.StringJoiner;

import fr.pederobien.minecraftdictionary.interfaces.IDictionary;
import fr.pederobien.minecraftdictionary.interfaces.IMessage;
import fr.pederobien.minecraftdictionary.interfaces.IMessageEvent;

public class NotEnoughArgumentsException extends AbstractDictionaryMessageException {
	private static final long serialVersionUID = 1L;
	private IMessage message;

	public NotEnoughArgumentsException(IMessageEvent event, IDictionary dictionary, IMessage message) {
		super(event, dictionary);
		this.message = message;
	}

	@Override
	public String getMessage() {
		StringJoiner joiner = new StringJoiner(", ");
		joiner.add("Not enough arguments");
		joiner.add("{Plugin=" + getEvent().getPlugin().getName() + "}");
		joiner.add(getDictionary().toString());
		joiner.add("{Code=" + getEvent().getCode() + "}");
		StringJoiner joinerBis = new StringJoiner(" ", "{Args={", "}}");
		for (String arg : getEvent().getArgs())
			joinerBis.add(arg);
		joiner.add("\n{Message=" + getDictionaryMessage() + "}");
		return joiner.toString();
	}

	/**
	 * @return The message found in the dictionary.
	 */
	public IMessage getDictionaryMessage() {
		return message;
	}
}
