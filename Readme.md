#Movie Rental Application

This is an application which stroes movies and tracks  the following

Rentals
Reviews
Avaliable Movies



The list of files in the program.

* Readme.md   : A file documenting the program.
* pom.xml     : A file used by a tool called Maven to manage the project.

Java Files:
* MovieCli.java     : Cli (Command Line Intefrace)
* Util.java         : A Class that have utility methods.  StringBuilder is part of core java and is an already defined class.


Static vs Instance
Static:
Variables should be declared static when defined.
Instance is a default declaration.

When you change static variables in one instance.  It changes for all instances.

When you change an instance varialbe the change is only made in that instance.

Examples of instances is one movie in a collection of movies.  Eg: Movie Superman in the collection of movies ( Superman, Spiderman, Batman)

##What is the advantage of using an Interface?

It becomes a lot easier when managing large projects.  When we want to change the implementation of a perticular class we could change it without changing the code that calls the class.

Arraylist is an Implementation of a List (List is an Interface)

Hashmap is an Implementation of a Map (Map is an Interface)


while(true) : is an infinite loop.