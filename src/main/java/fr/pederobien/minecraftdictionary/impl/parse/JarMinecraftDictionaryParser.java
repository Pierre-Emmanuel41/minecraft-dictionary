package fr.pederobien.minecraftdictionary.impl.parse;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import fr.pederobien.dictionary.interfaces.IDictionary;
import fr.pederobien.dictionary.interfaces.IDictionaryParser;
import fr.pederobien.persistence.interfaces.IPersistence;

public class JarMinecraftDictionaryParser implements IDictionaryParser {
	private IPersistence<IDictionary> persistence;

	/**
	 * Create a dictionary parser when the dictionary file is in a jar file.
	 * 
	 * @param name The name of the dictionary.
	 */
	public JarMinecraftDictionaryParser(String name) {
		persistence = new JarMinecraftDictionaryPersistence(name);
	}

	/**
	 * The path to the jar file.
	 */
	@Override
	public IDictionary parse(Path path) throws FileNotFoundException {
		persistence.setPath(path);
		persistence.load(path.getFileName().toString());
		return persistence.get();
	}
}
