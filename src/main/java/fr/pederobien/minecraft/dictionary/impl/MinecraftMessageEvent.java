package fr.pederobien.minecraft.dictionary.impl;

import java.util.Locale;
import java.util.StringJoiner;

import org.bukkit.GameRule;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.pederobien.dictionary.impl.MessageEvent;
import fr.pederobien.minecraft.dictionary.interfaces.IMinecraftCode;
import fr.pederobien.minecraft.dictionary.interfaces.IMinecraftMessageEvent;
import fr.pederobien.minecraft.dictionary.interfaces.IPlayerGroup;
import fr.pederobien.minecraft.managers.EColor;
import fr.pederobien.minecraft.managers.MessageManager.DisplayOption;
import fr.pederobien.minecraft.managers.PlayerManager;

public class MinecraftMessageEvent extends MessageEvent implements IMinecraftMessageEvent {
	private Player player;
	private IMinecraftCode code;
	private String prefix, suffix;
	private DisplayOption displayOption;
	private boolean isItalic, isBold, isSynchro;
	private EColor color;

	/**
	 * Create a message event. This event is used to be send to a dictionary to get the translation associated to the given code.
	 * 
	 * @param player        Used to get its local.
	 * @param code          Used as key to get the right message in the right dictionary.
	 * @param prefix        The string to display before the message.
	 * @param suffix        The string to display after the message.
	 * @param displayOption The place where the message should be displayed on player screen.
	 * @param isSynchro     If the message should be sent if and only if the {@link GameRule#SEND_COMMAND_FEEDBACK} value is true.
	 * @param isItalic      If the message should be displayed in italic.
	 * @param isBold        If the message should be displayed in bold.
	 * @param color         The message color.
	 * @param args          Some arguments (optional) used for dynamic messages.
	 * 
	 * @return A message event based on the given parameter.
	 */
	private MinecraftMessageEvent(Locale locale, IMinecraftCode code, String prefix, String suffix, DisplayOption displayOption, boolean isSynchro, boolean isItalic,
			boolean isBold, EColor color, Object... args) {
		super(locale, code.value(), args);
		this.code = code;
		this.prefix = prefix;
		this.suffix = suffix;
		this.displayOption = displayOption;
		this.isSynchro = isSynchro;
		this.isItalic = isItalic;
		this.isBold = isBold;
		this.color = color;
	}

	/**
	 * Creates a new minecraft message event builder instance with default parameters.
	 * 
	 * @param sender The entity that run a command.
	 * @param code   The code used to get a translated message.
	 * 
	 * @return A new instance of a minecraft message event builder.
	 */
	public static MinecraftMessageEventBuilder builder(CommandSender sender, IMinecraftCode code) {
		return new MinecraftMessageEventBuilder(code);
	}

	/**
	 * Creates a new minecraft message event builder instance with default parameters.
	 * 
	 * @param code The code used to get a translated message.
	 * 
	 * @return A new instance of a minecraft message event builder.
	 */
	public static MinecraftMessageEventBuilder builder(IMinecraftCode code) {
		return new MinecraftMessageEventBuilder(code);
	}

	@Override
	public IMinecraftCode getMinecraftCode() {
		return code;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public String getSuffix() {
		return suffix;
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
	public boolean isSynchro() {
		return isSynchro;
	}

	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(",\n", "{", "}");
		joiner.add("player=" + player.getName());
		joiner.add("locale=" + getLocale());
		joiner.add("code=" + getCode());
		joiner.add("prefix=" + prefix);
		joiner.add("suffix=" + suffix);
		joiner.add("displayOption=" + displayOption);
		joiner.add("isItalic=" + isItalic);
		joiner.add("isBold=" + isBold);
		joiner.add("color=" + color);
		StringJoiner joinerBis = new StringJoiner(" ", "args={", "}");
		for (Object arg : getArgs())
			joinerBis.add(arg.toString());
		return joiner.add(joinerBis.toString()).toString();
	}

	public static class MinecraftMessageEventBuilder {
		private Locale locale;
		private IMinecraftCode code;
		private String prefix, suffix;
		private DisplayOption displayOption;
		private boolean isItalic, isBold, isSynchro;
		private EColor color;

		public MinecraftMessageEventBuilder(CommandSender sender, IMinecraftCode code) {
			this.code = code;
			if (sender == null)
				locale = Locale.ENGLISH;
			else
				locale = sender instanceof Player ? PlayerManager.getPlayerLocale(((Player) sender)) : Locale.ENGLISH;
			displayOption = DisplayOption.CONSOLE;
			isItalic = false;
			isBold = false;
			isSynchro = false;
			color = EColor.WHITE;
		}

		public MinecraftMessageEventBuilder(IMinecraftCode code) {
			this(null, code);
		}

		/**
		 * Set the code players group.
		 * 
		 * @param group The group of players that should receive the underlying message.
		 * 
		 * @return This builder.
		 */
		public MinecraftMessageEventBuilder withGroup(IPlayerGroup group) {
			code.setGroup(group);
			return this;
		}

		/**
		 * Set the event locale.
		 * 
		 * @param locale The locale used to display a translated message.
		 * 
		 * @return This builder.
		 */
		public MinecraftMessageEventBuilder withLocale(Locale locale) {
			this.locale = locale;
			return this;
		}

		/**
		 * Set the event prefix.
		 * 
		 * @param prefix The string to display before the message.
		 * 
		 * @return This builder.
		 */
		public MinecraftMessageEventBuilder withPrefix(String prefix) {
			this.prefix = prefix;
			return this;
		}

		/**
		 * Set the event prefix.
		 * 
		 * @param prefix The string to display before the message.
		 * @param color  The suffix color.
		 * 
		 * @return This builder.
		 */
		public MinecraftMessageEventBuilder withPrefix(String prefix, EColor color) {
			this.prefix = color.getInColor(prefix);
			return this;
		}

		/**
		 * Set the event suffix.
		 * 
		 * @param suffix The string to display after the message.
		 * 
		 * @return This builder.
		 */
		public MinecraftMessageEventBuilder withSuffix(String suffix) {
			this.suffix = suffix;
			return this;
		}

		/**
		 * Set the event suffix.
		 * 
		 * @param suffix The string to display after the message.
		 * @param color  The suffix color.
		 * 
		 * @return This builder.
		 */
		public MinecraftMessageEventBuilder withSuffix(String suffix, EColor color) {
			this.suffix = color.getInColor(suffix);
			return this;
		}

		/**
		 * Set the event display option.
		 * 
		 * @param displayOption The place where the message should be displayed on player screen.
		 * 
		 * @return This builder.
		 */
		public MinecraftMessageEventBuilder withDisplayOption(DisplayOption displayOption) {
			this.displayOption = displayOption;
			return this;
		}

		/**
		 * Set the event synchro tag.
		 * 
		 * @param isSynchro If the message should be sent if and only if the {@link GameRule#SEND_COMMAND_FEEDBACK} value is true.
		 * 
		 * @return This builder.
		 */
		public MinecraftMessageEventBuilder withSynchro(boolean isSynchro) {
			this.isSynchro = isSynchro;
			return this;
		}

		/**
		 * Set the event italic tag.
		 * 
		 * @param isItalic If the message should be displayed in italic.
		 * 
		 * @return This builder.
		 */
		public MinecraftMessageEventBuilder withItalic(boolean isItalic) {
			this.isItalic = isItalic;
			return this;
		}

		/**
		 * Set the event bold tag.
		 * 
		 * @param isBold If the message should be displayed in bold.
		 * 
		 * @return This builder.
		 */
		public MinecraftMessageEventBuilder withBold(boolean isBold) {
			this.isBold = isBold;
			return this;
		}

		/**
		 * Set the event message color.
		 * 
		 * @param color The message color.
		 * 
		 * @return This builder.
		 */
		public MinecraftMessageEventBuilder withColor(EColor color) {
			this.color = color;
			return this;
		}

		/**
		 * Creates a minecraft message event based on the parameter of this builder.
		 * 
		 * @param args Some arguments (optional) used for dynamic messages.
		 * 
		 * @return the created event.
		 * 
		 * @throws IllegalStateException If the code associated to this builder is null.
		 */
		public IMinecraftMessageEvent build(Object... args) {
			if (code == null)
				throw new IllegalStateException("Cannot build a MinecraftMessageEvent without a specified message code");

			return new MinecraftMessageEvent(locale, code, prefix, suffix, displayOption, isSynchro, isItalic, isBold, color, args);
		}
	}
}
