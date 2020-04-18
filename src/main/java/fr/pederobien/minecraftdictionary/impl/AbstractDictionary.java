package fr.pederobien.minecraftdictionary.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import fr.pederobien.minecraftdictionary.exceptions.MessageNotFoundException;
import fr.pederobien.minecraftdictionary.exceptions.NotEnoughArgumentsException;
import fr.pederobien.minecraftdictionary.interfaces.IDictionary;
import fr.pederobien.minecraftdictionary.interfaces.IMessage;
import fr.pederobien.minecraftdictionary.interfaces.IMessageCode;
import fr.pederobien.minecraftdictionary.interfaces.IMessageEvent;

public class AbstractDictionary implements IDictionary {
	private List<Locale> locales;
	private Map<IMessageCode, IMessage> messages;
	private List<IMessage> messageValues;

	protected AbstractDictionary(Locale... locales) {
		this.locales = Arrays.asList(locales);
		messages = new HashMap<IMessageCode, IMessage>();
	}

	@Override
	public List<Locale> getLocales() {
		return Collections.unmodifiableList(locales);
	}

	@Override
	public String getMessage(IMessageEvent event) {
		IMessage message = messages.get(event.getCode());
		if (message == null)
			throw new MessageNotFoundException(event, this);

		try {
			return message.getMessage(event.getArgs());
		} catch (IndexOutOfBoundsException e) {
			throw new NotEnoughArgumentsException(event, this, message);
		}
	}

	@Override
	public IDictionary register(IMessage message) {
		messages.put(message.getCode(), message);
		updateMessageValues();
		return this;
	}

	@Override
	public IDictionary unregister(IMessageCode code) {
		if (messages.remove(code) != null)
			updateMessageValues();
		return this;
	}

	@Override
	public List<IMessage> getMessages() {
		return Collections.unmodifiableList(messageValues);
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{Dictionary={languages={", "}}}");
		for (Locale locale : locales)
			joiner.add(locale.toString());
		return joiner.toString();
	}

	private void updateMessageValues() {
		messageValues = messages.values().stream().collect(Collectors.toList());
	}
}
