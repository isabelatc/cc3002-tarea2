package cc3002.t1.pokemon.basic;

import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableCardVisitor;

/**
 * Common interface for all basic pokémon.
 *
 * @author Isabela Tellechea Coluccio
 */
public interface IBasicPokemon extends IPokemon {

    @Override
    void isPlayed(PlayCardVisitor v);

    @Override
    boolean canBePlayed(PlayableCardVisitor v);

}
