package fr.pederobien.minecraftdictionary.impl;

import java.util.StringJoiner;

import org.bukkit.entity.Player;

import fr.pederobien.dictionary.impl.MessageEvent;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageCode;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageEvent;
import fr.pederobien.minecraftmanagers.PlayerManager;

public class MinecraftMessageEvent extends MessageEvent implements IMinecraftMessageEvent {
	private Player player;
	private IMinecraftMessageCode code;

	public MinecraftMessageEvent(Player player, IMinecraftMessageCode code, String... args) {
		super(PlayerManager.getPlayerLocale(player), code, args);
		this.player = player;
		this.code = code;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public IMinecraftMessageCode getCode() {
		return code;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{MessageEvent=", "}");
		joiner.add("{Player=" + getPlayer().getName() + "}");
		joiner.add("{Locale=" + getLocale() + "}");
		joiner.add("{Code=" + getCode() + "}");
		StringJoiner joinerBis = new StringJoiner(" ", "{Args={", "}}");
		for (String arg : getArgs())
			joinerBis.add(arg);
		return joiner.add(joinerBis.toString()).toString();
	}
}
