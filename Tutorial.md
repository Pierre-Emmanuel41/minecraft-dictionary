# How to use this API

Please see in details [This tutorial](https://github.com/Pierre-Emmanuel41/dictionary/blob/master/Tutorial.md) in order to understand the tools proposed by the dictionary project.

# Minecraft specific

To get messages, we need to use a <code>MinecraftMessageEvent</code>. This event provides all needed informations for the dictionary context, the dictionary, the stored messages and the group of players that will receive the message. There is a lot of constructors for this class. Those constructors can be usefull if you want to modify the default style of the message. The most important class associated to this event is the implementation of the interface <code>IMinecraftMessageCode</code>. This interface provides two additional methods comparing to the IMessageCode : getGroup and setGroup. Indeed, when a plugin send a message, a group must be specified. There are two already implemented group : ALL and OPERATORS and can be found [here](https://github.com/Pierre-Emmanuel41/minecraft-dictionary/tree/master/src/main/java/fr/pederobien/minecraftdictionary/impl/PlayerGroup.java). The following code represent one possible implementation of this interface. The message associated to the <code>CODE_1</code> will only be sent to the operators of the server and the message associated to the <code>CODE_2</code> will be sent to all players.

```java
	public enum EMessageCode implements IMinecraftMessageCode {
	CODE_1, CODE_2(PlayerGroup.ALL);
	
	private IPlayerGroup group;
	
	private EMessageCode() {
		this(PlayerGroup.OPERATORS);
	}
	
	private EMessageCode(IPlayerGroup group) {
		this.group = group;
	}
	
	@Override
	public void setGroup(IPlayerGroup group) {
		this.group = group;
	}
	
	@Override
	public IPlayerGroup getGroup() {
		return group;
	}

	@Override
	public String value() {
		return toString();
	}
}
```

To send a message to a group of players, we only need to call the method <code>sendMessage(IMinecraftMessageEvent event)</code>

But first, let's say we have two dictionaries : one for English, the other one for French :

```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<dictionary>
		<version>1.0</version>
		<locales>
			<locale>en</locale>
			<locale>en-EN</locale>
			<locale>en-GB</locale>
			<locale>en-UK</locale>
			<locale>en-US</locale>
			<locale>en-CA</locale>
		</locales>
		<messages>
			<message>
				<code>CODE_1</code>
				<value>Static Message 1</value>
			</message>
			<message>
				<code>CODE_2</code>
				<value>Dynamic Message : %s</value>
			</message>
		</messages>
	</dictionary>
```

```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<dictionary>
		<version>1.0</version>
		<locales>
			<locale>fr</locale>
			<locale>fr-FR</locale>
			<locale>fr-CA</locale>
		</locales>
		<messages>
			<message>
				<code>CODE_1</code>
				<value>Message statique 1</value>
			</message>
			<message>
				<code>CODE_2</code>
				<value>Message dynamique : %s</value>
			</message>
		</messages>
	</dictionary>
```

```java
	public static void main(String[] args) {
			MinecraftDictionaryContext context = MinecraftDictionaryContext.getInstance();
			try {
				context.register(Paths.get(Main.class.getResource("/English.xml").toURI()));
				context.register(Paths.get(Main.class.getResource("/French.xml").toURI()));
				
				// Send to each operator currently logged in the server the message associated to the code CODE_1.
				// Result : Each operator receives "Static Message 1".
				center.send(new MinecraftMessageEvent(EMessageCode.Code_1));
				
				// Send to each players currently logged in the server the message associated to the code CODE_2.
				// Result : Each player receives "Dynamic Message : Hello world".
				center.send(new MinecraftMessageEvent(EMessageCode.Code_1, "Hello world"));
			} catch (FileNotFoundException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
```

You don't need to think about the nationality of the different players. The API tries to get the message translated in the player language but if there is no message in this language then the API send the English message associated to the code.