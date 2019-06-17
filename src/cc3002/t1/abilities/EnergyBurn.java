package cc3002.t1.abilities;

import cc3002.t1.general.EnergyCounter;
import cc3002.t1.visitors.UseAbilityVisitor;

/**
 * Class for a specific ability, Energy Burn. Its effect consists in converting all the energy cards the pokémon
 * has into energies of the same type of the pokémon.
 *
 * @author Isabela Tellechea Coluccio
 */
public class EnergyBurn extends AbstractAbility {

    /**
     * Constructor for an Energy Burn ability.
     *
     * @param name Ability's name.
     * @param description A description of the ability.
     * @param energyCost The structure that contains the costs of every energy type required to use the ability.
     */
    public EnergyBurn(String name, String description, EnergyCounter energyCost) {
        super(name, description, energyCost);
    }

    @Override
    public void usedByFightingPokemon() {
        UseAbilityVisitor v = new UseAbilityVisitor();
        v.energyBurnByFightingPokemon(this);
    }

    @Override
    public void usedByFirePokemon() {
        UseAbilityVisitor v = new UseAbilityVisitor();
        v.energyBurnByFirePokemon(this);
    }

    @Override
    public void usedByGrassPokemon() {
        UseAbilityVisitor v = new UseAbilityVisitor();
        v.energyBurnByGrassPokemon(this);
    }

    @Override
    public void usedByLightningPokemon() {
        UseAbilityVisitor v = new UseAbilityVisitor();
        v.energyBurnByLightningPokemon(this);
    }

    @Override
    public void usedByPsychicPokemon() {
        UseAbilityVisitor v = new UseAbilityVisitor();
        v.energyBurnByPsychicPokemon(this);
    }

    @Override
    public void usedByWaterPokemon() {
        UseAbilityVisitor v = new UseAbilityVisitor();
        v.energyBurnByWaterPokemon(this);
    }
}
