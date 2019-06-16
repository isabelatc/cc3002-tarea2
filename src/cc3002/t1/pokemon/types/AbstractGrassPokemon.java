package cc3002.t1.pokemon.types;

import cc3002.t1.abilities.IAbility;
import cc3002.t1.abilities.IAttack;
import cc3002.t1.pokemon.AbstractPokemon;
import cc3002.t1.pokemon.IPokemon;

import java.util.ArrayList;

/**
 * Class for a generic grass pokémon.
 *
 * @author Isabela Tellechea Coluccio
 */
public abstract class AbstractGrassPokemon extends AbstractPokemon implements IPokemon {

    /**
     * Constructor of a generic grass Pokémon card.
     *
     * @param name The name of the Pokémon.
     * @param id The identification number of the Pokémon (according to the Pokédex).
     * @param hp The initial hit points of the Pokémon.
     * @param abilityList The list of abilities the Pokémon can use.
     */
    public AbstractGrassPokemon(String name, int id, int hp, ArrayList<IAbility> abilityList) {
        super(name, id, hp, abilityList);
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public void usesAbility() {
        if (this.canUseAbility()) {
            this.getSelectedAbility().usedByGrassPokemon();
        }
    }

    @Override
    public void attackedByFightingPokemon(IAttack attack) {
        this.receivesNeutralAttack(attack);
    }

    @Override
    public void attackedByFirePokemon(IAttack attack) {
        this.receivesStrengthenedAttack(attack);
    }

    @Override
    public void attackedByGrassPokemon(IAttack attack) {
        this.receivesNeutralAttack(attack);
    }

    @Override
    public void attackedByLightningPokemon(IAttack attack) {
        this.receivesNeutralAttack(attack);
    }

    @Override
    public void attackedByPsychicPokemon(IAttack attack) {
        this.receivesNeutralAttack(attack);
    }

    @Override
    public void attackedByWaterPokemon(IAttack attack) {
        this.receivesWeakenedAttack(attack);
    }

}
