package cc3002.t1.general;

import cc3002.t1.abilities.IAbility;
import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.trainercards.ITrainerCard;
import cc3002.t1.trainercards.field.AbstractFieldCard;

import java.util.List;
import java.util.Random;

/**
 * Common interface for all the Pokémon trainers. They should all have an initial deck of cards.
 *
 * @author Isabela Tellechea Coluccio
 */
public interface ITrainer {

    /**
     * @return A list of the cards in the trainer's hand.
     */
    List<ICard> getHand();

    /**
     * @return A list of the Pokémon in the trainer's bench.
     */
    List<IPokemon> getBench();

    /**
     * @return A list of the cards in the trainer's discard pile.
     */
    List<ICard> getDiscardPile();

    /**
     * @return The size of the current deck of the trainer.
     */
    List<ICard> getDeck();

    /**
     * @return The size of the list of prize cards the trainer hasn't revealed.
     */
    List<ICard> getPrizes();

    /**
     * @return The current active Pokémon.
     */
    IPokemon getActivePokemon();

    /**
     * The trainer requests the total energies from a Pokémon.
     *
     * @param pokemon A Pokémon, usually it will be the trainer's active one.
     *
     * @return A dictionary structure listing the Pokémon's energies.
     */
    EnergyCounter getPokemonEnergy(IPokemon pokemon);

    /**
     * The trainer asks for the list of abilities a Pokémon has.
     *
     * @param pokemon The Pokémon the trainer is asking about.
     * @return A list of the Pokémon's abilities.
     */
    List<IAbility> getPokemonAbilities(IPokemon pokemon);

    /**
     * @return The trainer's selected pokémon.
     */
    IPokemon getSelectedPokemon();

    /**
     * @return The current field card of the trainer, if there is one.
     */
    ITrainerCard getActiveFieldCard();

    /**
     * @return True if the action was successful, false otherwise.
     */
    boolean getActionSuccessful();

    /**
     * @return The trainer that this one is going to play against with.
     */
    ITrainer getAdversary();

    /**
     * Sets the value of this trainer's adversary.
     *
     * @param adversary The trainer this one is playing against with.
     */
    void setAdversary(ITrainer adversary);

    /**
     * Changes the value of the parameter that indicates if an action was successful or not.
     *
     * @param value True if the action was successful, false otherwise.
     */
    void setActionSuccessful(boolean value);

    /**
     * Changes current active field card.
     *
     * @param card The new active field card.
     */
    void setActiveFieldCard(ITrainerCard card);

    /**
     * The trainer draws the first card of their deck and adds it to their hand.
     */
    void drawFromDeck();

    /**
     * This method represents the actions that happen when the trainer plays a card.
     *
     * @param card The card the trainer is going to play (could be an energy or a Pokémon).
     */
    void playCard(ICard card);

    /**
     * The trainer adds a card to their hand.
     *
     * @param card The card that will be added to the trainer's hand.
     */
    void addToHand(ICard card);

    /**
     * The trainer removes a card from their hand.
     *
     * @param card The card that will be removed from the trainer's hand.
     */
    void removeFromHand(ICard card);

    /**
     * The trainer adds a Pokémon to their bench, if there's enough space.
     *
     * @param pokemon The Pokémon the trainer will add to their bench.
     */
    void addToBench(IPokemon pokemon);

    /**
     * The trainer adds a card to their discard pile.
     *
     * @param card The card that will be added to their discard pile.
     */
    void addToDiscardPile(ICard card);

    /**
     * The trainer changes the current active pokémon, or sets a specific one in that position.
     *
     * @param pokemon The Pokémon that will replace the current active Pokémon, or be set as such.
     */
    void changeActivePokemon(IPokemon pokemon);

    /**
     * When there is no active Pokémon, this method sets the first Pokémon in the bench as the active one.
     */
    void setActivePokemon();

    /**
     * The trainer selects an ability from the active Pokémon. This will be the ability to be used by the pokémon.
     *
     * @param index The position in the active Pokémon's list of abilities that contains the selected ability.
     */
    void selectAbility(int index);

    /**
     * The active Pokémon from the trainer uses its selected ability.
     */
    void useAbility();

    /**
     * This method updates the selected pokémon parameter. The new selected one can only be a pokémon from the
     * trainer's bench or their active pokémon.
     *
     * @param pokemon The pokémon that's being selected.
     */
    void setSelectedPokemon(IPokemon pokemon);

    @Override
    boolean equals(Object o);

    /**
     * The trainer flips a coin.
     *
     * @param rand Used for randomizing the flipping.
     * @return true if heads, false if tails.
     */
    boolean flipACoin(Random rand);

    /**
     * The trainer checks is a pokémon is on their field.
     *
     * @param pokemon Pokémon to be checked.
     * @return true if the pokémon is on the field, false otherwise.
     */
    boolean isOnField(IPokemon pokemon);

    /**
     * The trainer removes a pokémon from the field.
     *
     * @param pokemon The pokémon that will be removed.
     */
    void removeFromField(IPokemon pokemon);

}
