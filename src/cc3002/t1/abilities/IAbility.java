package cc3002.t1.abilities;

import cc3002.t1.general.EnergyCounter;
import cc3002.t1.pokemon.IPokemon;

/**
 * The interface for every Ability. Their properties should be used by a Pokémon.
 *
 * @author Isabela Tellechea Coluccio
 */
public interface IAbility {

    /**
     * @return Ability's name.
     */
    String getAbilityName();

    /**
     * @return Ability's description.
     */
    String getDescription();

    /**
     * @return A dictionary structure with the Ability's energy costs.
     */
    EnergyCounter getEnergyCosts();

    /**
     * @return Ability's fighting energy cost.
     */
    int getFightingCost();

    /**
     * @return Ability's fire energy cost.
     */
    int getFireCost();

    /**
     * @return Ability's grass energy cost.
     */
    int getGrassCost();

    /**
     * @return Ability's lightning energy cost.
     */
    int getLightningCost();

    /**
     * @return Ability's psychic energy cost.
     */
    int getPsychicCost();

    /**
     * @return Ability's water energy cost.
     */
    int getWaterCost();

    /**
     * @return The Pokémon that uses the ability.
     */
    IPokemon getPokemon();

    /**
     * Sets the Pokémon parameter of the ability.
     *
     * @param pokemon The pokémon that will use the ability.
     */
    void setPokemon(IPokemon pokemon);

    /**
     * Executes the actions of this ability when it's used by a fighting pokémon.
     */
    void usedByFightingPokemon();

    /**
     * Executes the actions of this ability when it's used by a fire pokémon.
     */
    void usedByFirePokemon();

    /**
     * Executes the actions of this ability when it's used by a grass pokémon.
     */
    void usedByGrassPokemon();

    /**
     * Executes the actions of this ability when it's used by a lightning pokémon.
     */
    void usedByLightningPokemon();

    /**
     * Executes the actions of this ability when it's used by a psychic pokémon.
     */
    void usedByPsychicPokemon();

    /**
     * Executes the actions of this ability when it's used by a water pokémon.
     */
    void usedByWaterPokemon();

    /**
     * @return Ability's base damage (when it's an attack, returns -1 if not).
     */
    int getBaseDamage();

}
