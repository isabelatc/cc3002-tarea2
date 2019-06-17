package cc3002.t1.abilities;

import cc3002.t1.general.EnergyCounter;
import cc3002.t1.visitors.UseAbilityVisitor;

/**
 * Class for a specific attack, Electric Shock. When used, the trainer flips a coin, if the result is tails, the Pokémon
 * that attacked receives damage. The self damage by default is 10 HP.
 */
public class ElectricShock extends AbstractAttack {

    private int selfDamage;

    /**
     * Constructor for an Electric Shock attack. The self damage is set by default at 10 HP.
     *
     * @param name Attack's name.
     * @param baseDamage The amount of base damage of the attack.
     * @param description A description of the attack.
     * @param energyCost The structure that contains the costs of every energy type required to attack.
     */
    public ElectricShock(String name, int baseDamage, String description, EnergyCounter energyCost) {
        super(name, baseDamage, description, energyCost);
        this.selfDamage = 10;
    }

    /**
     * Another constructor, but this time self damage is given.
     *
     * @param name AbstractAttack's name.
     * @param baseDamage The amount of base damage of the usesAbility.
     * @param description A description of the usesAbility.
     * @param energyCost The structure that contains the costs of every energy type required to usesAbility.
     * @param selfDamage The damage the Pokémon may inflict itself.
     */
    public ElectricShock(String name, int baseDamage, String description, EnergyCounter energyCost, int selfDamage) {
        super(name, baseDamage, description, energyCost);
        this.selfDamage = selfDamage;
    }

    public int getSelfDamage() {
        return this.selfDamage;
    }

    @Override
    public void usedByFightingPokemon() {
        UseAbilityVisitor v = new UseAbilityVisitor();
        v.electricShockByFightingPokemon(this);
    }

    @Override
    public void usedByFirePokemon() {
        UseAbilityVisitor v = new UseAbilityVisitor();
        v.electricShockByFirePokemon(this);
    }

    @Override
    public void usedByGrassPokemon() {
        UseAbilityVisitor v = new UseAbilityVisitor();
        v.electricShockByGrassPokemon(this);
    }

    @Override
    public void usedByLightningPokemon() {
        UseAbilityVisitor v = new UseAbilityVisitor();
        v.electricShockByLightningPokemon(this);
    }

    @Override
    public void usedByPsychicPokemon() {
        UseAbilityVisitor v = new UseAbilityVisitor();
        v.electricShockByPsychicPokemon(this);
    }

    @Override
    public void usedByWaterPokemon() {
        UseAbilityVisitor v = new UseAbilityVisitor();
        v.electricShockByWaterPokemon(this);
    }
}
