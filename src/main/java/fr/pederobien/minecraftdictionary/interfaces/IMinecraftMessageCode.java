package fr.pederobien.minecraftdictionary.interfaces;

import fr.pederobien.dictionary.interfaces.IMessageCode;

public interface IMinecraftMessageCode extends IMessageCode {

	/**
	 * @return the group of player that should receive the message.
	 */
	IPlayerGroup getGroup();

	/**
	 * Set the group of player that should receive the message.
	 * 
	 * @param group The new group associated to this message.
	 */
	void setGroup(IPlayerGroup group);
}
