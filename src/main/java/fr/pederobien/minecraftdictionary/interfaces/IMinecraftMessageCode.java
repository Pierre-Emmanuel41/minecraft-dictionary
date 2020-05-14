package fr.pederobien.minecraftdictionary.interfaces;

import fr.pederobien.dictionary.interfaces.IMessageCode;
import fr.pederobien.minecraftdictionary.impl.Permission;

public interface IMinecraftMessageCode extends IMessageCode {

	/**
	 * @return The permission used to send the message.
	 */
	Permission getPermission();

	/**
	 * Set the new permission of this message code.
	 * 
	 * @param permission The new permission of this message.
	 */
	void setPermission(Permission permission);
}
