package fr.pederobien.minecraft.dictionary.impl;

import java.util.Locale;
import java.util.StringJoiner;

import org.bukkit.entity.Player;

import fr.pederobien.dictionary.impl.MessageEvent;
import fr.pederobien.minecraft.dictionary.interfaces.IMinecraftCode;
import fr.pederobien.minecraft.dictionary.interfaces.IMinecraftMessageEvent;
import fr.pederobien.minecraft.managers.EColor;
import fr.pederobien.minecraft.managers.PlayerManager;
import fr.pederobien.minecraft.managers.MessageManager.DisplayOption;

public class MinecraftMessageEvent extends MessageEvent implements IMinecraftMessageEvent {
	private Player player;
	private IMinecraftCode code;
	private DisplayOption displayOption;
	private boolean isItalic, isBold;
	private EColor color;

	/**
	 * Create a message event. This event is used to be send to a dictionary to get the translation associated to the given code.
	 * 
	 * @param player        Used to get its local.
	 * @param code          Used as key to get the right message in the right dictionary.
	 * @param displayOption The place where the message should be displayed on player screen.
	 * @param isItalic      If the message should be displayed in italic.
	 * @param isBold        If the message should be displayed in bold.
	 * @param color         The message color.
	 * @param args          Some arguments (optional) used for dynamic messages.
	 * 
	 * @return A message event based on the given parameter.
	 */
	public MinecraftMessageEvent(Player player, IMinecraftCode code, DisplayOption displayOption, boolean isItalic, boolean isBold, EColor color, Object... args) {
		super(player != null ? PlayerManager.getPlayerLocale(player) : Locale.ENGLISH, code, args);
		this.player = player;
		this.code = code;
		this.displayOption = displayOption;
		this.isItalic = isItalic;
		this.isBold = isBold;
		this.color = color;
	}

	/**
	 * Create a message event. This event is used to be send to a dictionary to get the translation associated to the given code.
	 * 
	 * @param code          Used as key to get the right message in the right dictionary.
	 * @param displayOption The place where the message should be displayed on player screen.
	 * @param isItalic      If the message should be displayed in italic.
	 * @param isBold        If the message should be displayed in bold.
	 * @param color         The message color.
	 * @param args          Some arguments (optional) used for dynamic messages.
	 * 
	 * @return A message event based on the given parameter.
	 */
	public MinecraftMessageEvent(IMinecraftCode code, DisplayOption displayOption, boolean isItalic, boolean isBold, EColor color, Object... args) {
		super(code, args);
		this.code = code;
		this.displayOption = displayOption;
		this.isItalic = isItalic;
		this.isBold = isBold;
		this.color = color;
	}

	/**
	 * Create a message event. This event is used to be send to a dictionary to get the translation associated to the given code.
	 * 
	 * @param player        Used to get its local.
	 * @param code          Used as key to get the right message in the right dictionary.
	 * @param displayOption The place where the message should be displayed on player screen.
	 * @param color         The message color.
	 * @param args          Some arguments (optional) used for dynamic messages.
	 * 
	 * @return A message event based on the given parameter.
	 */
	public MinecraftMessageEvent(Player player, IMinecraftCode code, DisplayOption displayOption, EColor color, Object... args) {
		this(player, code, displayOption, false, false, color, args);
	}

	/**
	 * Create a message event. This event is used to be send to a dictionary to get the translation associated to the given code.
	 * 
	 * @param code          Used as key to get the right message in the right dictionary.
	 * @param displayOption The place where the message should be displayed on player screen.
	 * @param color         The message color.
	 * @param args          Some arguments (optional) used for dynamic messages.
	 * 
	 * @return A message event based on the given parameter.
	 */
	public MinecraftMessageEvent(IMinecraftCode code, DisplayOption displayOption, EColor color, Object... args) {
		this(code, displayOption, false, false, color, args);
	}

	/**
	 * Create a message event. This event is used to be send to a dictionary to get the translation associated to the given code.
	 * 
	 * @param player        Used to get its local.
	 * @param code          Used as key to get the right message in the right dictionary.
	 * @param displayOption The place where the message should be displayed on player screen.
	 * @param args          Some arguments (optional) used for dynamic messages.
	 * 
	 * @return A message event based on the given parameter.
	 */
	public MinecraftMessageEvent(Player player, IMinecraftCode code, DisplayOption displayOption, Object... args) {
		this(player, code, displayOption, false, false, EColor.WHITE, args);
	}

	/**
	 * Create a message event. This event is used to be send to a dictionary to get the translation associated to the given code.
	 * 
	 * @param player Used to get its local.
	 * @param code   Used as key to get the right message in the right dictionary.
	 * @param color  The message color.
	 * @param args   Some arguments (optional) used for dynamic messages.
	 * 
	 * @return A message event based on the given parameter.
	 */
	public MinecraftMessageEvent(Player player, IMinecraftCode code, EColor color, Object... args) {
		this(player, code, DisplayOption.CONSOLE, false, false, color, args);
	}

	/**
	 * Create a message event. This event is used to be send to a dictionary to get the translation associated to the given code.
	 * 
	 * @param code          Used as key to get the right message in the right dictionary.
	 * @param displayOption The place where the message should be displayed on player screen.
	 * @param args          Some arguments (optional) used for dynamic messages.
	 * 
	 * @return A message event based on the given parameter.
	 */
	public MinecraftMessageEvent(IMinecraftCode code, DisplayOption displayOption, Object... args) {
		this(code, displayOption, false, false, EColor.WHITE, args);
	}

	/**
	 * Create a message event. This event is used to be send to a dictionary to get the translation associated to the given code.
	 * 
	 * @param code  Used as key to get the right message in the right dictionary.
	 * @param color The message color.
	 * @param args  Some arguments (optional) used for dynamic messages.
	 * 
	 * @return A message event based on the given parameter.
	 */
	public MinecraftMessageEvent(IMinecraftCode code, EColor color, Object... args) {
		this(code, DisplayOption.CONSOLE, false, false, color, args);
	}

	/**
	 * Create a message event. This event is used to be send to a dictionary to get the translation associated to the given code.
	 * 
	 * @param player Used to get its local.
	 * @param code   Used as key to get the right message in the right dictionary.
	 * @param args   Some arguments (optional) used for dynamic messages.
	 * 
	 * @return A message event based on the given parameter.
	 */
	public MinecraftMessageEvent(Player player, IMinecraftCode code, Object... args) {
		this(player, code, DisplayOption.CONSOLE, false, false, EColor.WHITE, args);
	}

	/**
	 * Create a message event. This event is used to be send to a dictionary to get the translation associated to the given code. The
	 * default locale is {@link Locale#ENGLISH}.
	 * 
	 * @param code Used as key to get the right message in the right dictionary.
	 * @param args Some arguments (optional) used for dynamic messages.
	 * 
	 * @return A message event based on the given parameter.
	 */
	public MinecraftMessageEvent(IMinecraftCode code, Object... args) {
		this(code, DisplayOption.CONSOLE, false, false, EColor.WHITE, args);
	}

	@Override
	public Player getPlayer() {
		return player;
	}

	@Override
	public IMinecraftCode getCode() {
		return code;
	}

	@Override
	public DisplayOption getDisplayOption() {
		return displayOption;
	}

	@Override
	public boolean isItalic() {
		return isItalic;
	}

	@Override
	public boolean isBold() {
		return isBold;
	}

	@Override
	public EColor getColor() {
		return color;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",\n", "{", "}");
		joiner.add("Player=" + getPlayer().getName());
		joiner.add("Locale=" + getLocale());
		joiner.add("Code=" + getCode());
		joiner.add("displayOption=" + getDisplayOption());
		joiner.add("isItalic=" + isItalic());
		joiner.add("isBold=" + isBold);
		joiner.add("color=" + getColor());
		StringJoiner joinerBis = new StringJoiner(" ", "Args={", "}");
		for (Object arg : getArgs())
			joinerBis.add(arg.toString());
		return joiner.add(joinerBis.toString()).toString();
	}
}
