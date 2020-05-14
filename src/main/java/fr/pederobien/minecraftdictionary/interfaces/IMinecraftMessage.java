package fr.pederobien.minecraftdictionary.interfaces;

import fr.pederobien.dictionary.interfaces.IMessage;
import fr.pederobien.dictionary.interfaces.IMessageEvent;
import fr.pederobien.minecraftdictionary.impl.Permission;

public interface IMinecraftMessage extends IMessage {

	/**
	 * @return A minecraft message event which is a simple simple message event with permission.
	 * 
	 * @see IMessageEvent
	 * @see Permission
	 */
	IMinecraftMessageCode getCode();
}
