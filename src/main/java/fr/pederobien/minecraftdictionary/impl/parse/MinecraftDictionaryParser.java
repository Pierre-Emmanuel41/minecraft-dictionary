package fr.pederobien.minecraftdictionary.impl.parse;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import fr.pederobien.dictionary.interfaces.IDictionary;
import fr.pederobien.dictionary.interfaces.IDictionaryParser;
import fr.pederobien.persistence.interfaces.IPersistence;

public class MinecraftDictionaryParser implements IDictionaryParser {
	private IPersistence<IDictionary> persistence;

	public MinecraftDictionaryParser() {
		persistence = new MinecraftDictionaryPersistence(null);
	}

	@Override
	public IDictionary parse(Path path) throws FileNotFoundException {
		persistence.setPath(path.getParent());
		persistence.load(path.getFileName().toString());
		return persistence.get();
	}
}
