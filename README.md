# Presentation

Nowadays, it is very nice when an application can support several languages in order to be used all around the world. Each company/developers has its own way to do so but, most of the time, the displayed messages are hardcoded and it is not easy to add another language. This project is the continuity of the [dictionary](https://github.com/Pierre-Emmanuel41/dictionary.git) project which provides an architecture for translated messages.  
The goal of this project is to propose simple tools for minecraft to send/receive messages according to the nationality of players.

# Download

According to the Minecraft API version there is on the server, you should download this project by specifying the branch associated to the associated version if supported. To do so, you can use the following command line :

```git
git clone -b 3.0_MC_1.16.5-SNAPSHOT https://github.com/Pierre-Emmanuel41/minecraft-dictionary.git --recursive
```

and then double click on the deploy.bat file. This will deploy this project and all its dependencies on your computer. Which means it generates the folder associated to this project and its dependencies in your .m2 folder. Once this has been done, you can add the project as maven dependency on your maven project :

```xml
<dependency>
	<groupId>fr.pederobien</groupId>
	<artifactId>minecraft.dictionary</artifactId>
	<version>3.0_MC_1.16.5-SNAPSHOT</version>
</dependency>
```

To see how you can use thoses features, please have a look to [This tutorial](https://github.com/Pierre-Emmanuel41/minecraft-dictionary/blob/3.0_MC_1.16.5-SNAPSHOT/Tutorial.md)
