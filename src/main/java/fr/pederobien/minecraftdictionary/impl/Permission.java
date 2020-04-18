package fr.pederobien.minecraftdictionary.impl;

public enum Permission {
	/**
	 * Permission to send the message to each players currently logged into the server.
	 */
	ALL,

	/**
	 * Permission to send the message only to operators currently logged into the server.
	 */
	OPERATORS,

	/**
	 * Permission to send the message only to the sender if and only if the sender is a player.
	 */
	SENDER
}
