package fr.pederobien.minecraft.dictionary.interfaces;

import org.bukkit.GameRule;

import fr.pederobien.dictionary.interfaces.IMessageEvent;
import fr.pederobien.minecraft.managers.EColor;
import fr.pederobien.minecraft.managers.MessageManager.DisplayOption;

public interface IMinecraftMessageEvent extends IMessageEvent {

	/**
	 * @return A minecraft code that specifies to which players the message should be sent.
	 */
	IMinecraftCode getMinecraftCode();

	/**
	 * @return The string that should be displayed before the message.
	 */
	String getPrefix();

	/**
	 * @return The string that should be displayed after the message.
	 */
	String getSuffix();

	/**
	 * @return The place where the message should be displayed on player screen.
	 */
	DisplayOption getDisplayOption();

	/**
	 * @return If the message should be displayed in italic.
	 */
	boolean isItalic();

	/**
	 * @return If the message should be displayed in bold.
	 */
	boolean isBold();

	/**
	 * @return The message color.
	 */
	EColor getColor();

	/**
	 * @return True if before to send a message to a specific player, the value of the game rule
	 *         {@link GameRule#SEND_COMMAND_FEEDBACK} should be evaluated in order to sent or not the message.
	 */
	boolean isSynchro();
}
