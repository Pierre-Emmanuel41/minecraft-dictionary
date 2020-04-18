package fr.pederobien.minecraftdictionary.interfaces;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public interface IMessageEvent {

	/**
	 * @return The player to send the message.
	 */
	Player getPlayer();

	/**
	 * @return The plugin to which the message code is associated.
	 */
	Plugin getPlugin();

	/**
	 * @return The code used to get the associated {@link IMessage}.
	 */
	IMessageCode getCode();

	/**
	 * @return An array of arguments used for {@link IMessage#getMessage(String...)}.
	 */
	String[] getArgs();
}
