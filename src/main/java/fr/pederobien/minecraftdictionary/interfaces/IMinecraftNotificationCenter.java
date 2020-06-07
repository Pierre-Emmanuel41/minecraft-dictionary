package fr.pederobien.minecraftdictionary.interfaces;

import fr.pederobien.dictionary.interfaces.INotificationCenter;

public interface IMinecraftNotificationCenter extends INotificationCenter {

	/**
	 * Send a message to one or more player currently logged into the server.
	 * 
	 * @param event The event used to get which message should be send, and to who the message should be sent.
	 */
	void sendMessage(IMinecraftMessageEvent event);

	/**
	 * Get the message associated to the message code in the given event that should be send to a player.
	 * 
	 * @param event The event used to get which message should be send, and to who the message should be sent.
	 * @return The registered message.
	 */
	String getMessage(IMinecraftMessageEvent event);
}
