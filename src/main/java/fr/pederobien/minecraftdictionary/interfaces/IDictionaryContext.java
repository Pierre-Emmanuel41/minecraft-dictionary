package fr.pederobien.minecraftdictionary.interfaces;

import org.bukkit.plugin.Plugin;

public interface IDictionaryContext {

	/**
	 * Register the given dictionary for the given plugin. If a dictionary is already registered for locales supported by the given
	 * dictionary, the old dictionary is replace by the specified dictionary.
	 * 
	 * @param plugin     The plugin used as key to get the dictionary.
	 * @param dictionary The dictionary used to get message when an {@link IMessageEvent} arrives.
	 * 
	 * @return This dictionary context to register dictionaries easier.
	 */
	IDictionaryContext register(Plugin plugin, IDictionary dictionary);

	/**
	 * Unregister the given dictionary for the given plugin.
	 * 
	 * @param plugin     The plugin used as key to find and remove the dictionary.
	 * @param dictionary The dictionary to remove.
	 * 
	 * @return This dictionary context to unregister dictionaries easier.
	 */
	IDictionaryContext unregister(Plugin plugin, IDictionary dictionary);
}
