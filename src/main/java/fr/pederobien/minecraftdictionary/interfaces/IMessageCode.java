package fr.pederobien.minecraftdictionary.interfaces;

import fr.pederobien.minecraftdictionary.impl.Permission;

public interface IMessageCode {

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
