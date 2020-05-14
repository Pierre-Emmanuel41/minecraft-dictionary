package fr.pederobien.minecraftdictionary.impl;

import fr.pederobien.dictionary.impl.AbstractMessage;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessage;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageCode;

public abstract class AbstractMinecraftMessage extends AbstractMessage implements IMinecraftMessage {

	protected AbstractMinecraftMessage(IMinecraftMessageCode code) {
		super(code);
	}

	@Override
	public IMinecraftMessageCode getCode() {
		return (IMinecraftMessageCode) super.getCode();
	}
}
