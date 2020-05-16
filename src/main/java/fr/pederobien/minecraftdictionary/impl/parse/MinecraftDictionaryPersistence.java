package fr.pederobien.minecraftdictionary.impl.parse;

import java.nio.file.Path;

import fr.pederobien.dictionary.interfaces.IDictionary;
import fr.pederobien.persistence.impl.xml.AbstractXmlPersistence;

public class MinecraftDictionaryPersistence extends AbstractXmlPersistence<IDictionary> {

	public MinecraftDictionaryPersistence(Path path) {
		super(path);
		register(new MinecraftDictionaryPersistenceLoader());
	}

	@Override
	public boolean save() {
		return false;
	}
}
