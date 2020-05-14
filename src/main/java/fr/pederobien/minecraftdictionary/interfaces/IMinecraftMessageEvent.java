package fr.pederobien.minecraftdictionary.interfaces;

import org.bukkit.entity.Player;

import fr.pederobien.dictionary.interfaces.IMessageEvent;
import fr.pederobien.minecraftdictionary.impl.Permission;

public interface IMinecraftMessageEvent extends IMessageEvent {

	/**
	 * @return The player to send the message.
	 */
	Player getPlayer();

	/**
	 * @return A minecraft message event which is a simple simple message event with permission.
	 * 
	 * @see IMessageEvent
	 * @see Permission
	 */
	IMinecraftMessageCode getCode();
}
