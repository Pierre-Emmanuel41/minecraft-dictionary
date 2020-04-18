package fr.pederobien.minecraftdictionary.interfaces;

public interface INotificationCenter {

	/**
	 * Send a message to one are more player currently logged into the server.
	 * 
	 * @param event The event used to get which message should be send, and to who the message should be sent.
	 */
	void sendMessage(IMessageEvent event);

	/**
	 * @return The context to register or unregister a dictionary.
	 */
	IDictionaryContext getDictionaryContext();
}
