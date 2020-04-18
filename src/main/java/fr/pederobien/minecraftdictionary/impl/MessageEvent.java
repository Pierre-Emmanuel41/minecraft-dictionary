package fr.pederobien.minecraftdictionary.impl;

import java.util.StringJoiner;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.pederobien.minecraftdictionary.interfaces.IMessageCode;
import fr.pederobien.minecraftdictionary.interfaces.IMessageEvent;

public class MessageEvent implements IMessageEvent {
	private Player player;
	private Plugin plugin;
	private IMessageCode code;
	private String[] args;

	public MessageEvent(Player player, Plugin plugin, IMessageCode code, String[] args) {
		this.player = player;
		this.plugin = plugin;
		this.code = code;
		this.args = args;
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public Plugin getPlugin() {
		return plugin;
	}

	@Override
	public IMessageCode getCode() {
		return code;
	}

	@Override
	public String[] getArgs() {
		return args;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(", ", "{MessageEvent=", "}");
		joiner.add("{Plugin=" + getPlugin().getName() + "}");
		joiner.add("{Player=" + getPlayer().getName() + "}");
		joiner.add("{Code=" + getCode() + "}");
		StringJoiner joinerBis = new StringJoiner(" ", "{Args={", "}}");
		for (String arg : getArgs())
			joinerBis.add(arg);
		return joiner.add(joinerBis.toString()).toString();
	}
}
