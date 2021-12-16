package fr.pederobien.minecraft.dictionary.interfaces;

import org.bukkit.entity.Player;

import fr.pederobien.dictionary.interfaces.IMessageEvent;
import fr.pederobien.minecraft.managers.EColor;
import fr.pederobien.minecraft.managers.MessageManager.DisplayOption;

public interface IMinecraftMessageEvent extends IMessageEvent {

	/**
	 * @return The player to send the message.
	 */
	Player getPlayer();

	/**
	 * @return A minecraft message event which is a simple simple message event with permission.
	 * 
	 * @see IMessageEvent
	 */
	IMinecraftCode getMinecraftCode();

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
}
