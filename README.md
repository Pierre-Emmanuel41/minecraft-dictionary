# Minecraft-dictionary

Nowadays, it is very nice when an application can support several languages in order to be used all around the world. Each company/developers has its own way to do so but, most of the time, the displayed messages are hardcoded and it is not easy to add another language. This project is a simple tool that provides an easy way to register/load dictionary for multi-languages application. The goal is to use code that represent a message on user screen. Using this, the developer does'nt know which message is displayed, but only the code associated to message.

# Register as maven dependency

It is easy to register this project as dependency for your own project. To do so, you need to download this project.
First, you need to download this project on your machine.

The easiest way to do so is to use the following git command line 

```git
git clone https://github.com/Pierre-Emmanuel41/minecraft-dictionary.git --recursive
```
Indeed, this project depends on differents projects : [persistence](https://github.com/Pierre-Emmanuel41/persistence), [dictionary](https://github.com/Pierre-Emmanuel41/dictionary) and [minecraft-managers](https://github.com/Pierre-Emmanuel41/minecraft-managers) and need to be downloaded on your machine to avoid compilation errors.

Then, you only need to run the file deploy.bat. This file will create the maven projects in your .m2 project

Finally, in the pom.xml of your project, you have to add the following lines :

```xml
<dependency>
	<groupId>fr.pederobien</groupId>
	<artifactId>minecraft-dictionary</artifactId>
	<version>1.0</version>
</dependency>
```
You can now use features provided by this api in you project.

To see how you can use thoses features, please have a look to [This tutorial](https://github.com/Pierre-Emmanuel41/minecraft-dictionary/blob/master/Tutorial.md)
