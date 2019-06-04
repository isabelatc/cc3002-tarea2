package cc3002.t1;

public interface ICard {

    /**
     * @return The name of the current card (could be a Pokémon or an energy).
     */
    String getCardName();

    /**
     * Sets the trainer of the card.
     *
     * @param trainer The card now belongs to this trainer.
     */
    void setTrainer(Trainer trainer);

    /**
     * The card is played by its trainer.
     */
    void isPlayed();

    /**
     * @return The trainer associated to the card.
     */
    ITrainer getTrainer();
}
