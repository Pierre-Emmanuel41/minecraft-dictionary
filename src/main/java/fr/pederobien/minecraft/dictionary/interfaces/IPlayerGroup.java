package fr.pederobien.minecraft.dictionary.interfaces;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.bukkit.entity.Player;

public interface IPlayerGroup {

	/**
	 * @return The predicate used to filter the collection of connected players.
	 */
	Predicate<Player> getPredicate();

	/**
	 * @return A stream that contains players that verify the predicate associated to this group.
	 */
	Stream<? extends Player> toStream();

	/**
	 * @return A list that contains players that verify the predicate associated to this group.
	 */
	List<? extends Player> toList();
}
