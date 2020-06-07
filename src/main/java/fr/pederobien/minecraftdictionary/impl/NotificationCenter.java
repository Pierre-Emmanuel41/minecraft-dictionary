package fr.pederobien.minecraftdictionary.impl;

import java.util.stream.Stream;

import org.bukkit.entity.Player;

import fr.pederobien.dictionary.impl.DictionaryContext;
import fr.pederobien.dictionary.interfaces.IDictionaryContext;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageEvent;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftNotificationCenter;
import fr.pederobien.minecraftmanagers.BukkitManager;
import fr.pederobien.minecraftmanagers.MessageManager;
import fr.pederobien.minecraftmanagers.PlayerManager;

public class NotificationCenter implements IMinecraftNotificationCenter {
	private IDictionaryContext dictionaryContext;

	private NotificationCenter() {
		dictionaryContext = new DictionaryContext();
	}

	public static IMinecraftNotificationCenter getInstance() {
		return SingletonHolder.CENTER;
	}

	private static class SingletonHolder {
		public static final IMinecraftNotificationCenter CENTER = new NotificationCenter();
	}

	@Override
	public IDictionaryContext getDictionaryContext() {
		return dictionaryContext;
	}

	@Override
	public void sendMessage(IMinecraftMessageEvent event) {
		switch (event.getCode().getPermission()) {
		case ALL:
			sendMessage(PlayerManager.getPlayers(), event);
			break;
		case OPERATORS:
			sendMessage(BukkitManager.getOnlineOperators(), event);
			break;
		case SENDER:
			sendMessage(event.getPlayer(), event);
			break;
		}
	}

	@Override
	public String getMessage(IMinecraftMessageEvent event) {
		return getDictionaryContext().getMessage(event);
	}

	private void sendMessage(Player player, IMinecraftMessageEvent event) {
		if (player.equals(event.getPlayer()))
			MessageManager.sendMessage(player, getMessage(event));
		else
			MessageManager.sendMessage(player, getMessage(new MinecraftMessageEvent(player, event.getCode(), event.getArgs())));
	}

	private void sendMessage(Stream<Player> players, IMinecraftMessageEvent event) {
		players.parallel().forEach(player -> sendMessage(player, event));
	}
}
