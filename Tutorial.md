# How to use this API

This simple tool allows you to easily create, load, register and/or unregister dictionaries in which messages for users are stored. First we need a <code>NotificationCenter</code>. This object is mainly used to get access to the <code>dictionaryContext</code>. The dictionary context  allows to store dictionaries for different languages. Finally, a <code>Dictionary</code> stores messages for users according to their code. Thus, the dictionary context allows to know in which language the message must be translated and the code allows to know which is the associated translation.

# Dictionary files

This API provides you the possibility to store dictionaries in files, to parse and register them using a special <code>IDictionaryParser</code>. Its also possible to use the default minecraft dictionary parser <code>MinecraftDictionaryParser</code> furnished by this API but the file has to be well formed in order to be parsed successfully. If you want to use this parser, the dictionary should looks like the following :

```xml
	<?xml version="1.0" encoding="UTF-8"?>
	<dictionary>
		<name>English</name>
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

Be careful, the value associated to the tag "locale" should correspond to the standard [IETF BCP 47](https://tools.ietf.org/html/bcp47) in order to be well interpreted by the java [Locale](https://docs.oracle.com/javase/7/docs/api/java/util/Locale.html) class (see forLanguageTag).

Then to parse the dictionary file, you simply need to register a dictionary using its.

```java
	public static void main(String[] args) {
		IMinecraftNotificationCenter center = NotificationCenter.getInstance();
		try {
			center.getDictionaryContext().register(Paths.get(Main.class.getResource("/DictionaryName.xml").toURI()));
			System.out.println(center.getDictionaryContext().getDictionary(Locale.ENGLISH).get());
		} catch (FileNotFoundException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
```

For this example, I put the file DictionaryName.xml in the folder src/main/resources. The output for this program is the following line :

```
	{Dictionary={languages={en, en_EN, en_GB, en_UK, en_US, en_CA}}}
```

This means that the parsed dictionary has been successfully created and the following java Locale : en, en_EN, en_GB, en_UK etc.. are supported by this dictionary.

If the dictionary format is different from above, you can still provide your own dictionary parser :

```java
	// The parser and the path has to be defined to avoid compilation errors
	NotificationCenter.getInstance().getDictionaryContext().register(parser, path);
```

Until now, we can register dictionary from different type of file. But we cannot get access to the stored messages in order to display translated messages to the user.

# Translated messages

To get messages, wee need to use a <code>MinecraftMessageEvent</code>. This event provides all needed informations for the dictionary context, the dictionary, the stored messages and the player whose receive the message. There is a lot of constructors for this class. Those constructors can be usefull if you want to modify the default style of the message. The most important class associated to this event is the implementation of the interface <code>IMinecraftMessageCode</code>. This interface provides two additional methods comparing to the IMessageCode : getPermission and setPermission. Indeed, when a plugin send a message, a group must be specified : All, OPERATORS or SENDER. The following code represent one possible implementation of this interface. The message associated to the <code>CODE_1</code> will only be sent to the operators of the server and the message associated to the <code>CODE_2</code> will be sent to all players.

```java
	public enum EMessageCode implements IMinecraftMessageCode {
	CODE_1, CODE_2(Permission.ALL);
	
	private Permission permission;
	
	private EMessageCode() {
		this(Permission.OPERATORS);
	}
	
	private EMessageCode(Permission permission) {
		this.permission = permission;
	}
	
	@Override
	public void setPermission(Permission permission) {
		this.permission = permission;
	}
	
	@Override
	public Permission getPermission() {
		return permission;
	}

	@Override
	public String value() {
		return toString();
	}
}
```

To send a message to a group of players, we only need to call the method <code>sendMessage(IMinecraftMessageEvent event)</code>

```java
	public static void main(String[] args) {
			IMinecraftNotificationCenter center = NotificationCenter.getInstance();
			try {
				center.getDictionaryContext().register(Paths.get(Main.class.getResource("/DictionaryName.xml").toURI()));
				
				// Send to each operator currently logged in the server the message associated to the code CODE_1.
				// Result : Each operator receives "Static Message 1".
				center.sendMessage(new MinecraftMessageEvent(EMessageCode.Code_1));
				
				// Send to each players currently logged in the server the message associated to the code CODE_2.
				// Result : Each player receives "Dynamic Message : Hello world".
				center.sendMessage(new MinecraftMessageEvent(EMessageCode.Code_1, "Hello world"));
			} catch (FileNotFoundException | URISyntaxException e) {
				e.printStackTrace();
			}
		}
```

You don't need to think about the nationality of the different players. The API tries to get the message translated in the player language but if there is no message in this language then the API send the English message associated to the code.

If some informations are missing, I invite you to read the README.md and the Tutorial/md of the [dictionary](https://github.com/Pierre-Emmanuel41/dictionary) project because this project is considered as an extension of it.