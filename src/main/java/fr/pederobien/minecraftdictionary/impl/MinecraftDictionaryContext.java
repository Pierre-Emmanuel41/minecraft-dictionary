package fr.pederobien.minecraftdictionary.impl;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.bukkit.entity.Player;

import fr.pederobien.dictionary.impl.DictionaryContext;
import fr.pederobien.dictionary.interfaces.IDictionary;
import fr.pederobien.dictionary.interfaces.IDictionaryContext;
import fr.pederobien.dictionary.interfaces.IDictionaryParser;
import fr.pederobien.dictionary.interfaces.IMessageEvent;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageEvent;
import fr.pederobien.minecraftmanagers.MessageManager;
import fr.pederobien.minecraftmanagers.MessageManager.TitleMessage;

public class MinecraftDictionaryContext implements IDictionaryContext {
	private IDictionaryContext context;

	private MinecraftDictionaryContext() {
		context = DictionaryContext.getInstance();
	}

	/**
	 * @return The unique instance of this dictionary context.
	 */
	public static MinecraftDictionaryContext getInstance() {
		return SingletonHolder.CONTEXT;
	}

	private static class SingletonHolder {
		private static final MinecraftDictionaryContext CONTEXT = new MinecraftDictionaryContext();
	}

	@Override
	public IDictionaryContext setParser(IDictionaryParser parser) {
		return context.setParser(parser);
	}

	@Override
	public IDictionaryContext register(IDictionary dictionary) {
		return context.register(dictionary);
	}

	@Override
	public IDictionaryContext register(Path path) throws FileNotFoundException {
		return context.register(path);
	}

	@Override
	public IDictionaryContext unregister(IDictionary dictionary) {
		return context.unregister(dictionary);
	}

	@Override
	public Optional<IDictionary> getDictionary(Locale locale) {
		return context.getDictionary(locale);
	}

	@Override
	public Map<Locale, IDictionary> getDictionaries() {
		return context.getDictionaries();
	}

	@Override
	public String getMessage(IMessageEvent event) {
		return context.getMessage(event);
	}

	/**
	 * Send a message to one or more player currently logged into the server.
	 * 
	 * @param event The event used to get which message should be send, and to who the message should be sent.
	 */
	public void send(IMinecraftMessageEvent event) {
		event.getCode().getGroup().toStream().forEach(player -> sendMessage(player, event));
	}

	private void sendMessage(Player player, IMinecraftMessageEvent event) {
		MessageManager.sendMessage(event.getDisplayOption(), player, TitleMessage.of(getMessage(event), event.isBold(), event.isItalic(), event.getColor()));
	}
}
