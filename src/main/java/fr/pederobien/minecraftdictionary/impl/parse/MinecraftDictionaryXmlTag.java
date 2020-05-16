package fr.pederobien.minecraftdictionary.impl.parse;

public enum MinecraftDictionaryXmlTag {
	Permission("permission");

	private String name;

	private MinecraftDictionaryXmlTag(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
