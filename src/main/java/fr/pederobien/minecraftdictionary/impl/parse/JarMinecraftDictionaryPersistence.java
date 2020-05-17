package fr.pederobien.minecraftdictionary.impl.parse;

import java.io.IOException;
import java.util.jar.JarFile;

import org.w3c.dom.Document;

import fr.pederobien.dictionary.interfaces.IDictionary;
import fr.pederobien.persistence.impl.xml.AbstractXmlPersistence;

public class JarMinecraftDictionaryPersistence extends AbstractXmlPersistence<IDictionary> {
	private String name;

	protected JarMinecraftDictionaryPersistence(String name) {
		super(null);
		this.name = name;
		register(new MinecraftDictionaryPersistenceLoader());
	}

	@Override
	protected Document createDoc(Object... objects) throws IOException {
		JarFile jar = null;
		try {
			jar = new JarFile(getPath().toFile());
			return parse(jar.getInputStream(jar.getJarEntry(name)));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (jar != null)
				try {
					jar.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	@Override
	public boolean save() {
		return false;
	}

	/**
	 * Set the name of the dictionary to parse;
	 * 
	 * @param name The name of the dictionary to launch.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
