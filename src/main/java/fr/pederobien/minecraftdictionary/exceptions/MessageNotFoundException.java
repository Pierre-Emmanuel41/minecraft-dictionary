package fr.pederobien.minecraftdictionary.exceptions;

import java.util.StringJoiner;

import fr.pederobien.minecraftdictionary.interfaces.IDictionary;
import fr.pederobien.minecraftdictionary.interfaces.IMessageEvent;

public class MessageNotFoundException extends AbstractDictionaryMessageException {
	private static final long serialVersionUID = 1L;

	public MessageNotFoundException(IMessageEvent event, IDictionary dictionary) {
		super(event, dictionary);
	}

	@Override
	public String getMessage() {
		StringJoiner joiner = new StringJoiner(", ");
		joiner.add("No associated message");
		joiner.add("{Plugin=" + getEvent().getPlugin().getName() + "}");
		joiner.add(getDictionary().toString());
		joiner.add("{Code=" + getEvent().getCode() + "}");
		StringJoiner joinerBis = new StringJoiner(" ", "{Args={", "}}");
		for (String arg : getEvent().getArgs())
			joinerBis.add(arg);
		return joiner.toString();
	}
}
