package fr.pederobien.minecraft.dictionary.interfaces;

public interface IMinecraftCode {

	/**
	 * @return The value represented by this message code.
	 */
	String value();

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
