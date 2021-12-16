package fr.pederobien.minecraft.dictionary.impl;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.pederobien.minecraft.dictionary.interfaces.IPlayerGroup;

public class PlayerGroup implements IPlayerGroup {

	/**
	 * Creates a group that contains all players.
	 */
	public static final IPlayerGroup ALL = of("all", player -> true);

	/**
	 * Creates a group that contains only operators.
	 */
	public static final IPlayerGroup OPERATORS = of("operators", player -> player.isOp());

	private String name;
	private Predicate<Player> predicate;

	/**
	 * Creates a group of player based on the current logged players and filter the collection of connected player according to the
	 * given predicate.
	 * 
	 * @param name      The name of this group of players.
	 * @param predicate The predicate used to filter the connection players.
	 * 
	 * @return A new player group.
	 */
	public static PlayerGroup of(String name, Predicate<Player> predicate) {
		return new PlayerGroup(name, predicate);
	}

	/**
	 * Creates a group of player based on the current logged players and filter the collection of connected player according to the
	 * given predicate.
	 * 
	 * @param name      The name of this group of players.
	 * @param predicate The predicate used to filter the connection players.
	 */
	private PlayerGroup(String name, Predicate<Player> predicate) {
		this.name = name;
		this.predicate = predicate;
	}

	@Override
	public String getName() {
		return name;
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

	@Override
	public String toString() {
		return name;
	}
}
