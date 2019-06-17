package cc3002.t1.visitors;

import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.pokemon.basic.IBasicPokemon;
import cc3002.t1.pokemon.stage1.IStage1Pokemon;
import cc3002.t1.pokemon.stage2.IStage2Pokemon;

/**
 * Abstract class for the visitor that handles when a trainer plays cards.
 *
 * @author Isabela Tellechea Coluccio
 */
public abstract class AbstractCardsVisitor {

    /**
     * Execute the actions that happen when a basic pokémon is played.
     *
     * @param pokemon The pokémon being played.
     */
    public abstract void visitBasicPokemon(IBasicPokemon pokemon);

    /**
     * Execute the actions that happen when a non basic (stage 1 or 2) pokémon is played.
     *
     * @param pokemon The pokémon being played.
     */
    public abstract void visitNonBasicPokemon(IPokemon pokemon);

    /**
     * Execute the actions that happen when a stage 1 pokémon is played.
     *
     * @param pokemon The pokémon being played.
     */
    public abstract void visitStage1Pokemon(IStage1Pokemon pokemon);

    /**
     * Execute the actions that happen when a stage 2 pokémon is played.
     *
     * @param pokemon The pokémon being played.
     */
    public abstract void visitStage2Pokemon(IStage2Pokemon pokemon);

}
