package fr.pederobien.minecraft.dictionary.impl;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.bukkit.GameRule;
import org.bukkit.entity.Player;

import fr.pederobien.dictionary.impl.DictionaryContext;
import fr.pederobien.dictionary.interfaces.IDictionary;
import fr.pederobien.dictionary.interfaces.IDictionaryContext;
import fr.pederobien.dictionary.interfaces.IDictionaryParser;
import fr.pederobien.dictionary.interfaces.IMessageEvent;
import fr.pederobien.minecraft.dictionary.interfaces.IMinecraftMessageEvent;
import fr.pederobien.minecraft.managers.BukkitManager;
import fr.pederobien.minecraft.managers.MessageManager;
import fr.pederobien.minecraft.managers.MessageManager.TitleMessage;

public class MinecraftDictionaryContext implements IDictionaryContext {
	private IDictionaryContext context;

	private MinecraftDictionaryContext() {
		context = new DictionaryContext();
	}

	/**
	 * @return The unique instance of this dictionary context.
	 */
	public static MinecraftDictionaryContext instance() {
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
	public IDictionaryContext register(String path) throws Exception {
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
		BukkitManager.getConsoleSender().sendMessage(getMessage(event));
		event.getMinecraftCode().getGroup().toStream().forEach(player -> sendMessage(player, event));
	}

	private void sendMessage(Player player, IMinecraftMessageEvent event) {
		if (!event.isSynchro() || player.getWorld().getGameRuleDefault(GameRule.SEND_COMMAND_FEEDBACK))
			MessageManager.sendMessage(event.getDisplayOption(), player, TitleMessage.of(format(event), event.isBold(), event.isItalic()));
	}

	private String format(IMinecraftMessageEvent event) {
		String message = "";
		if (event.getPrefix() != null)
			message = event.getPrefix();
		message += event.getColor().getInColor(getMessage(event));
		if (event.getSuffix() != null)
			message += event.getSuffix();
		return message;
	}
}
