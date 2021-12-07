package fr.pederobien.minecraft.dictionary.interfaces;

import fr.pederobien.dictionary.interfaces.ICode;

public interface IMinecraftCode extends ICode {

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
