package cc3002.t1.visitors;

import cc3002.t1.abilities.ElectricShock;
import cc3002.t1.abilities.EnergyBurn;

/**
 * Abstract class for the visitor that handles the pokémon's abilities.
 *
 * @author Isabela Tellechea Coluccio
 */
public abstract class AbstractAbilitiesVisitor {

    /**
     * Executes the electric shock ability's effect.
     *
     * @param electricShock The ability.
     */
    public abstract void electricShockEffect(ElectricShock electricShock);

    /**
     * Executes the electric shock ability's effect when performed by a fighting pokémon.
     *
     * @param electricShock The ability.
     */
    public abstract void electricShockByFightingPokemon(ElectricShock electricShock);

    /**
     * Executes the electric shock ability's effect when performed by a fire pokémon.
     *
     * @param electricShock The ability.
     */
    public abstract void electricShockByFirePokemon(ElectricShock electricShock);

    /**
     * Executes the electric shock ability's effect when performed by a grass pokémon.
     *
     * @param electricShock The ability.
     */
    public abstract void electricShockByGrassPokemon(ElectricShock electricShock);

    /**
     * Executes the electric shock ability's effect when performed by a lightning pokémon.
     *
     * @param electricShock The ability.
     */
    public abstract void electricShockByLightningPokemon(ElectricShock electricShock);

    /**
     * Executes the electric shock ability's effect when performed by a psychic pokémon.
     *
     * @param electricShock The ability.
     */
    public abstract void electricShockByPsychicPokemon(ElectricShock electricShock);

    /**
     * Executes the electric shock ability's effect when performed by a water pokémon.
     *
     * @param electricShock The ability.
     */
    public abstract void electricShockByWaterPokemon(ElectricShock electricShock);

    /**
     * Executes the energy burn ability's effect.
     *
     * @param energyBurn The ability.
     */
    public abstract void energyBurnEffect(EnergyBurn energyBurn);

    /**
     * Executes the energy burn ability's effect when performed by a fighting pokémon.
     *
     * @param energyBurn The ability.
     */
    public abstract void energyBurnByFightingPokemon(EnergyBurn energyBurn);

    /**
     * Executes the energy burn ability's effect when performed by a fire pokémon.
     *
     * @param energyBurn The ability.
     */
    public abstract void energyBurnByFirePokemon(EnergyBurn energyBurn);

    /**
     * Executes the energy burn ability's effect when performed by a grass pokémon.
     *
     * @param energyBurn The ability.
     */
    public abstract void energyBurnByGrassPokemon(EnergyBurn energyBurn);

    /**
     * Executes the energy burn ability's effect when performed by a lightning pokémon.
     *
     * @param energyBurn The ability.
     */
    public abstract void energyBurnByLightningPokemon(EnergyBurn energyBurn);

    /**
     * Executes the energy burn ability's effect when performed by a psychic pokémon.
     *
     * @param energyBurn The ability.
     */
    public abstract void energyBurnByPsychicPokemon(EnergyBurn energyBurn);

    /**
     * Executes the energy burn ability's effect when performed by a water pokémon.
     *
     * @param energyBurn The ability.
     */
    public abstract void energyBurnByWaterPokemon(EnergyBurn energyBurn);

}
