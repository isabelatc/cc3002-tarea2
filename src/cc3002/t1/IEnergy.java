package cc3002.t1;

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
    String getCardName();

    @Override
    ITrainer getTrainer();

    @Override
    void setTrainer(ITrainer trainer);

    @Override
    void isPlayed();

    @Override
    boolean equals(Object o);

}
