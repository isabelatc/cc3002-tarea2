package cc3002.t1;

/**
 * Common interface for energy cards. It refers to a method in which the card is played by a trainer, and to another one
 * in which the card is added to the available energies of a Pokémon.
 */
public interface IEnergy extends ICard {

    @Override
    boolean equals(Object o);

    /**
     * It queues the card to be added to the energies of the trainer's active Pokémon. Essentially it removes the energy from
     * the trainer's hand.
     *
     * @param trainer The trainer that plays the energy card.
     */
    void isPlayed(ITrainer trainer);

    /**
     * The current energy card adds 1 to the amount of the corresponding available energies of the argument Pokémon.
     *
     * @param pokemon The Pokémon card that gets a new energy card added to its collection.
     */
    void isAdded(IPokemon pokemon);

}
