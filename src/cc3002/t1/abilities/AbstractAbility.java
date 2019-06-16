package cc3002.t1.abilities;

import cc3002.t1.general.EnergyCounter;
import cc3002.t1.pokemon.IPokemon;

/**
 * Base class for a generic ability. The Pokémon cards use these objects.
 *
 * @author Isabela Tellechea Coluccio
 */
public abstract class AbstractAbility implements IAbility {

    private String name, description;
    private EnergyCounter energyCost;
    private IPokemon pokemon;

    /**
     * Constructor for a generic ability.
     *
     * @param name Ability's name.
     * @param description A description of the ability.
     * @param energyCost The structure that contains the costs of every energy type required to use the ability.
     */
    public AbstractAbility(String name, String description, EnergyCounter energyCost) {
        this.name = name;
        this.description = description;
        this.energyCost = energyCost;
        this.pokemon = null;
    }

    @Override
    public String getAbilityName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public EnergyCounter getEnergyCosts() {
        return energyCost;
    }

    @Override
    public int getFightingCost() {
        return this.energyCost.getFightingEnergy();
    }

    @Override
    public int getFireCost() {
        return this.energyCost.getFireEnergy();
    }

    @Override
    public int getGrassCost() {
        return this.energyCost.getGrassEnergy();
    }

    @Override
    public int getLightningCost() {
        return this.energyCost.getLightningEnergy();
    }

    @Override
    public int getPsychicCost() {
        return this.energyCost.getPsychicEnergy();
    }

    @Override
    public int getWaterCost() {
        return this.energyCost.getWaterEnergy();
    }

    @Override
    public IPokemon getPokemon() {
        return this.pokemon;
    }

    @Override
    public void setPokemon(IPokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public abstract void usedByFightingPokemon();

    @Override
    public abstract void usedByFirePokemon();

    @Override
    public abstract void usedByGrassPokemon();

    @Override
    public abstract void usedByLightningPokemon();

    @Override
    public abstract void usedByPsychicPokemon();

    @Override
    public abstract void usedByWaterPokemon();

}
