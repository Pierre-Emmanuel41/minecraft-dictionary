package fr.pederobien.minecraftdictionary.impl.parse;

import java.util.Locale;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fr.pederobien.dictionary.impl.AbstractDictionary;
import fr.pederobien.dictionary.impl.AbstractMessage;
import fr.pederobien.dictionary.impl.persistence.DictionaryXmlTag;
import fr.pederobien.dictionary.interfaces.IDictionary;
import fr.pederobien.dictionary.interfaces.IMessageCode;
import fr.pederobien.minecraftdictionary.impl.Permission;
import fr.pederobien.minecraftdictionary.interfaces.IMinecraftMessageCode;
import fr.pederobien.persistence.impl.xml.AbstractXmlPersistenceLoader;
import fr.pederobien.persistence.interfaces.xml.IXmlPersistenceLoader;

public class MinecraftDictionaryPersistenceLoader extends AbstractXmlPersistenceLoader<IDictionary> {
	private IDictionary dictionary;

	protected MinecraftDictionaryPersistenceLoader() {
		super(1.0);
	}

	@Override
	protected IDictionary create() {
		return null;
	}

	@Override
	public IXmlPersistenceLoader<IDictionary> load(Element root) {
		// Getting dictionary's locales
		NodeList locales = getElementsByTagName(root, DictionaryXmlTag.LOCALE);
		Locale[] dictionaryLocales = new Locale[locales.getLength()];
		for (int i = 0; i < locales.getLength(); i++)
			dictionaryLocales[i] = Locale.forLanguageTag(locales.item(i).getChildNodes().item(0).getNodeValue());

		dictionary = new AbstractDictionary(dictionaryLocales);

		// Getting dictionary's messages
		NodeList messages = getElementsByTagName(root, DictionaryXmlTag.MESSAGE);
		for (int i = 0; i < messages.getLength(); i++) {
			// Getting code properties
			Node code = getElementsByTagName((Element) messages.item(i), DictionaryXmlTag.CODE).item(0);
			String permission = getStringAttribute((Element) code, MinecraftDictionaryXmlTag.Permission);
			IMinecraftMessageCode dictionaryCode = new MessageCode(code.getChildNodes().item(0).getNodeValue(), permission);

			// Getting message properties
			Node message = getElementsByTagName((Element) messages.item(i), DictionaryXmlTag.MESSAGE_VALUE).item(0);
			String dictionaryMessage = message.getChildNodes().item(0).getNodeValue();
			dictionary.register(new Message(dictionaryCode, dictionaryMessage));
		}
		return this;
	}

	@Override
	public IDictionary get() {
		return dictionary;
	}

	private class Message extends AbstractMessage {
		private String message;

		protected Message(IMinecraftMessageCode code, String message) {
			super(code);
			this.message = message;
		}

		@Override
		public String getMessage(Object... args) {
			return String.format(message, args);
		}

		@Override
		public String toString() {
			return "Message={" + getCode() + ", value=" + message + "}";
		}
	}

	private class MessageCode implements IMinecraftMessageCode {
		private String value;
		private Permission permission;

		protected MessageCode(String value, String permission) {
			this.value = value;
			try {
				if (permission == null)
					this.permission = Permission.OPERATORS;
				else
					this.permission = Permission.valueOf(permission);
			} catch (IllegalArgumentException e) {
				this.permission = Permission.OPERATORS;
			}
		}

		@Override
		public String value() {
			return value;
		}

		@Override
		public Permission getPermission() {
			return permission;
		}

		@Override
		public void setPermission(Permission permission) {
			this.permission = permission;
		}

		@Override
		public String toString() {
			return "Code={Value=" + value() + ",Permission=" + permission + "}";
		}

		@Override
		public int hashCode() {
			return value.hashCode() ^ 31 + permission.toString().hashCode() ^ 31;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || !(obj instanceof IMessageCode))
				return false;
			IMessageCode other = (IMessageCode) obj;
			return value.equals(other.value());
		}
	}
}
