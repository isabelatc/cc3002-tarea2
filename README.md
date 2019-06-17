Project 2: Pokémon TCG: Electric Boogaloo
======
This project is completed for the Design and Programming Methodologies course (CC3002), lectured by the Computer Science Department at Universidad de Chile. 
In this case, the purpose of the project is to implement, using the programming language Java, a basic game of Pokémon. The current implementation is the second and final (at least for now) update on the game, adding more features to it than it was done in the first project. The most important and basic classes and interfaces were previously created on the first instance, and everything that didn't work according to expectations was corrected before starting the second version of development.
Academically, it's relevant to note that the code is full of applications of some of the design patterns studied in this course, such as Double Dispatch, Visitor and Template. This increases its value, since the implementation of every class and method was carefully optimized, in order to make it more readable, understandable and extensible, so it's easier to make it grow in the future.

### Running the Project
It is recommended to run the project using the IDE IntelliJ for Java, considering the code was developed with that tool. Once that's installed, the next step is to download every file from this repository, save it in a directory and open it as a project in IntelliJ. 
After that, there will be two main directories, src (which contains the programs for the project) and test (which contains the test classes for the methods). From there it's possible to navigate through every file, and do anything with them.

### More on the Implementation
The [UML Diagram code](../master/uml-pokemon.uml) (or its [.png version](../master/pokemondiagram.png)) of the project omits some classes for easier understanding. Specifically, the WaterPokemon and the WaterEnergy classes are the ones representing six other classes for each category (Pokémon and Energy). That means, there are ten classes not showed, which are: FightingPokemon, FirePokemon, GrassPokemon, LightningPokemon, PsychicPokemon, FightingEnergy, FireEnergy, GrassEnergy, LightningEnergy and PsychicEnergy. The dependencies for each class are equivalent to the ones that are shown.
