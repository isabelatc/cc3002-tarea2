package cc3002.t1.energies;

import cc3002.t1.general.ICard;
import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.visitors.PlayCardVisitor;

/**
 * Common interface for energy cards.
 */
public interface IEnergy extends ICard {

    /**
     * The current energy card is added to the Pokémon's energy collection.
     *
     * @param pokemon The Pokémon that gets a new energy card added to its collection.
     */
    void isAdded(IPokemon pokemon);

    @Override
    void isPlayed(PlayCardVisitor v);

    @Override
    boolean equals(Object o);

}
