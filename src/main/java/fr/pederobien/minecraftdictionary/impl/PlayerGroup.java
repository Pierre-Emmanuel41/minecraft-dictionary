package fr.pederobien.minecraftdictionary.impl;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.pederobien.minecraftdictionary.interfaces.IPlayerGroup;

public class PlayerGroup implements IPlayerGroup {

	/**
	 * Creates a group that contains all players.
	 */
	public static final IPlayerGroup All = of(player -> true);

	/**
	 * Creates a group that contains only operators.
	 */
	public static final IPlayerGroup OPERATORS = of(player -> player.isOp());

	private Predicate<Player> predicate;

	/**
	 * Creates a group of player based on the current logged players and filter the collection of connected player according to the
	 * given predicate.
	 * 
	 * @param predicate The predicate used to filter the connection players.
	 * 
	 * @return A new player group.
	 */
	public static PlayerGroup of(Predicate<Player> predicate) {
		return new PlayerGroup(predicate);
	}

	/**
	 * Creates a group of player based on the current logged players and filter the collection of connected player according to the
	 * given predicate.
	 * 
	 * @param predicate The predicate used to filter the connection players.
	 */
	private PlayerGroup(Predicate<Player> predicate) {
		this.predicate = predicate;
	}

	@Override
	public Predicate<Player> getPredicate() {
		return predicate;
	}

	@Override
	public Stream<? extends Player> toStream() {
		return Bukkit.getOnlinePlayers().stream().filter(predicate);
	}

	@Override
	public List<? extends Player> toList() {
		return Bukkit.getOnlinePlayers().stream().filter(predicate).collect(Collectors.toList());
	}
}
