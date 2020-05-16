package fr.pederobien.minecraftdictionary.impl.parse;

import java.io.IOException;
import java.nio.file.Path;

import org.w3c.dom.Document;

import fr.pederobien.dictionary.interfaces.IDictionary;
import fr.pederobien.persistence.impl.xml.AbstractXmlPersistence;

public class MinecraftDictionaryPersistence extends AbstractXmlPersistence<IDictionary> {

	/**
	 * @param path The path to the dictionary xml file.
	 */
	public MinecraftDictionaryPersistence(Path path) {
		super(path);
		register(new MinecraftDictionaryPersistenceLoader());
	}

	@Override
	protected Document createDoc(Object... objects) throws IOException {
		return parseFromFileName((String) objects[0]);
	}

	@Override
	public boolean save() {
		return false;
	}
}
