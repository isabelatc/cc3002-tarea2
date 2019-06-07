Project 1: Pokémon TCG: Basic Elements
======
This project is implemented for the Design and Programming Methodologies course (CC3002), lectured by the Computer Science Department in Universidad de Chile. 
In this case, the purpose of the project is to implement, using the programming language Java, a basic game of Pokémon. For now the coding will be only related to create the most important classes and interfaces, from which the rest of the game can be developed in the future. Academically, the most relevant tool to learn is the use of the design pattern double dispatch, as it will be implemented in numerous methods from the project.

### Running the Project
It is recommended to run the project using the IDE IntelliJ for Java, considering the code was developed with that tool. Once it's installed, the next step is to download every file from this repository, save it in a directory and open it as a project in IntelliJ. 
After that, there will be two main directories, src (which contains the programs for the project) and test (which contains the test classes for the methods).

### More on the Implementation
The [UML Diagram code](../master/uml-pokemon.uml) (or its [.png version](../master/pokemondiagram.png)) of the project omits some classes for easier understanding. Specifically, the WaterPokemon and the WaterEnergy classes are the ones representing six other classes for each category (Pokémon and Energy). That means, there are ten classes not showed, which are: FightingPokemon, FirePokemon, GrassPokemon, LightningPokemon, PsychicPokemon, FightingEnergy, FireEnergy, GrassEnergy, LightningEnergy and PsychicEnergy. The dependencies for each class are equivalent to the ones that are shown.
