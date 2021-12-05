package fr.pederobien.minecraftdictionary.impl;

import fr.pederobien.dictionary.impl.MessageCode;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageCode;
import fr.pederobien.minecraftdictionary.interfaces.IPlayerGroup;

public class MinecraftMessageCode extends MessageCode implements IMinecraftMessageCode {
	private IPlayerGroup group;

	/**
	 * Creates a minecraft message code associated to a specific code and a group of player.
	 * 
	 * @param value The code value.
	 * @param group The player group that will receive the message.
	 */
	public MinecraftMessageCode(String value, IPlayerGroup group) {
		super(value);
		this.group = group;
	}

	public MinecraftMessageCode(String value) {
		this(value, PlayerGroup.OPERATORS);
	}

	@Override
	public IPlayerGroup getGroup() {
		return group;
	}

	@Override
	public void setGroup(IPlayerGroup group) {
		this.group = group;
	}
}
