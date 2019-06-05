package cc3002.t1;

import java.util.List;

/**
 * Common interface for all the Pokémon trainers. They should all have an initial hand of cards,
 * which they can play to their bench, or later as an active Pokémon.
 *
 * @author Isabela Tellechea Coluccio
 */
public interface ITrainer {

    /**
     * @return A list of the cards in the Trainer's hand.
     */
    List<ICard> getHand();

    /**
     * @return A list of the Pokémon in the Trainer's bench.
     */
    List<IPokemon> getBench();

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
    EnergyCounter getPokemonEnergies(IPokemon pokemon);

    /**
     * The trainer asks for the list of attacks a Pokémon has.
     *
     * @param pokemon The Pokémon the trainer is asking about.
     * @return A list of the Pokémon's attacks.
     */
    List<IAttack> getPokemonAttacks(IPokemon pokemon);

    /**
     * This method represents the actions that happen when the trainer plays a card.
     *
     * @param card The card the trainer is going to play (could be an energy or a Pokémon).
     */
    void playCard(ICard card);

    /**
     * The trainer adds a Pokémon to their bench, if there's enough space.
     *
     * @param pokemon The Pokémon the trainer will add to their bench.
     */
    void addToBench(IPokemon pokemon);

    /**
     * The trainer changes the current active Pokémon, only if they're still alive.
     *
     * @param pokemon The Pokémon that will replace the current active Pokémon.
     */
    void changeActivePokemon(IPokemon pokemon);

    /**
     * When the active Pokémon is defeated, this method replaces it with the first Pokémon in the bench.
     */
    void setActivePokemon();

    /**
     * The trainer selects an attack from the active Pokémon. This will be the attack to be used against an
     * opponent.
     *
     * @param index The position in the active Pokémon's list of attacks that contains the selected attack.
     */
    void selectAttack(int index);

    /**
     * The active Pokémon from the trainer attacks the opponent's active Pokémon with their selected attack.
     *
     * @param opponent The opponent of the trainer.
     */
    void useAttack(ITrainer opponent);

    /**
     * Gets the opponent's list of Pokémon in their bench.
     *
     * @param opponent The opponent of the trainer.
     */
    List<IPokemon> getOppBench(ITrainer opponent);

    /**
     * Gets the opponent's active Pokémon.
     *
     * @param opponent The opponent of the trainer.
     */
    IPokemon getOppActivePokemon(ITrainer opponent);

}
