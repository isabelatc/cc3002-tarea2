package cc3002.t1;

import java.util.List;

/**
 * Common interface for all the Pokémon trainers. They all should have an initial hand of cards,
 * which they can play to their bench, or later as an active Pokémon.
 *
 * @author Isabela Tellechea Coluccio
 */
public interface ITrainer {

    /**
     * @return A list of the Pokémon in the Trainer's bench.
     */
    List<IPokemon> getBench();

    /**
     * @return A list of the cards in the Trainer's hand.
     */
    List<ICard> getHand();

    /**
     * The trainer plays a Card.
     *
     * @param card The Card the trainer is going to play (could be an energy or a Pokémon).
     */
    void playCard(ICard card);

    /**
     * The trainer adds a Pokémon to their bench, if there's enough space.
     *
     * @param pokemon The Pokémon the trainer will add to their bench.
     */
    void addToBench(IPokemon pokemon);

    /**
     * The trainer plays a Pokémon card.
     *
     * @param pokemon The Pokémon the trainer is going to play (place in the bench).
     */
  //  void playPokemon(IPokemon pokemon);

    /**
     * The trainer plays an Energy card.
     *
     * @param energy The energy card the trainer is going to play (on the active Pokémon).
     */
  //  void playEnergy(IEnergy energy);

    /**
     * The trainer requests the total energies from a Pokémon.
     *
     * @param pokemon A Pokémon.
     *
     * @return A list of the Pokémon's energies.
     */
    List<Integer> getPokemonEnergies(IPokemon pokemon);

    /**
     * @return The current active Pokémon.
     */
    IPokemon getActivePokemon();

    /**
     * The trainer changes the current active Pokémon. It only applies when the Pokémon to be replaced is alive.
     *
     * @param pokemon The Pokémon that will replace the current active Pokémon.
     */
    void changeActivePokemon(IPokemon pokemon);

    /**
     * When the active Pokémon dies, this method replaces it with the first Pokémon in the bench.
     */
    void setActivePokemon();

    /**
     * The trainer asks for the list of attacks a Pokémon has.
     *
     * @param pokemon The Pokémon the trainer is asking about.
     * @return A list of the Pokémon's attacks.
     */
   // List<IAttack> getActivePokemonAttacks();
    List<IAttack> getPokemonAttacks(IPokemon pokemon);

    /**
     * The trainer selects an attack from the active Pokémon.
     *
     * @param index The position in the active Pokémon's list of attacks that contains the selected attack.
     *
     * @return The selected attack.
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

    /**
     *
     */




}
