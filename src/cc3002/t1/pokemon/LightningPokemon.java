package cc3002.t1.pokemon;

import cc3002.t1.AbstractPokemon;
import cc3002.t1.IAttack;
import cc3002.t1.IPokemon;

import java.util.ArrayList;
import java.util.List;

public class LightningPokemon extends AbstractPokemon {

    /**
     * Creates a new instance of a lightning Pokémon.
     *
     * @param name The name of the Pokémon.
     * @param id The identification number of the Pokémon (according to the Pokédex).
     * @param hp The initial hit points of the Pokémon.
     * @param attackList The list of attacks the Pokémon can use.
     */
    public LightningPokemon(String name, int id, int hp, ArrayList<IAttack> attackList) {
        super(name, id, hp, attackList);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LightningPokemon) {
            return (((LightningPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((LightningPokemon) o).getID() == this.getID() &&
                    ((LightningPokemon) o).getHP() == this.getHP() &&
                    (((LightningPokemon) o).getAttacks()).equals(this.getAttacks()) &&
                    (((LightningPokemon) o).getEnergies()).equals(this.getEnergies());
        }
        return false;
    }

    @Override
    public void attack(IPokemon other) {
        if (this.canAttack()) {
            other.attackedByLightningPokemon(getSelectedAttack());
        }
    }

    @Override
    public void attackedByFightingPokemon(IAttack attack) {
        this.receivesStrengthenedAttack(attack);
    }

    @Override
    public void attackedByFirePokemon(IAttack attack) {
        this.receivesNeutralAttack(attack);
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
        this.receivesNeutralAttack(attack);
    }
}

