package cc3002.t1.abilities;

import cc3002.t1.general.EnergyCounter;

/**
 * Base class for a generic attack. The Pokémon cards use these objects. If the constructor doesn't
 * provide a base damage, it's set by default on 10 HP.
 *
 * @author Isabela Tellechea Coluccio
 */
public abstract class AbstractAttack extends AbstractAbility implements IAttack {

    private int baseDamage;

    /**
     * Creates a new generic attack, with a default base damage of 10 HP.
     *
     * @param name Attack's name.
     * @param description A description of the attack.
     * @param energyCost The structure that contains the costs of every energy type required to attack.
     */
    public AbstractAttack(String name, String description, EnergyCounter energyCost) {
        super(name, description, energyCost);
        this.baseDamage = 10;
    }

    /**
     * Creates a new generic attack, with a given base damage.
     *
     * @param name Attack's name.
     * @param baseDamage The amount of base damage of the attack.
     * @param description A description of the attack.
     * @param energyCost The structure that contains the costs of every energy type required to attack.
     */
    public AbstractAttack(String name, int baseDamage, String description, EnergyCounter energyCost) {
        super(name, description, energyCost);
        this.baseDamage = baseDamage;
    }

    @Override
    public int getBaseDamage() {
        return this.baseDamage;
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
