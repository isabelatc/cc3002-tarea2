package cc3002.t1;

/**
 * Common interface for energy cards. It refers to a method in which the card is played by a trainer, and to another one
 * in which the card is added to the available energies of a Pokémon.
 */
public interface IEnergy extends ICard {

    @Override
    boolean equals(Object o);

    /**
     * The current energy card is added to the Pokémon's energy collection.
     *
     * @param pokemon The Pokémon card that gets a new energy card added to its collection.
     */
    void isAdded(IPokemon pokemon);

}
