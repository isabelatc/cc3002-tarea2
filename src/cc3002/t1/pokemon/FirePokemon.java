package cc3002.t1.pokemon;

import cc3002.t1.AbstractPokemon;
import cc3002.t1.IAttack;
import cc3002.t1.IPokemon;

import java.util.ArrayList;

public class FirePokemon extends AbstractPokemon {

    /**
     * Creates a new instance of a fire Pokémon.
     *
     * @param name The name of the Pokémon.
     * @param id The identification number of the Pokémon (according to the Pokédex).
     * @param hp The initial hit points of the Pokémon.
     * @param attackList The list of attacks the Pokémon can use.
     */
    public FirePokemon(String name, int id, int hp, ArrayList<IAttack> attackList) {
        super(name, id, hp, attackList);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FirePokemon) {
            return (((FirePokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((FirePokemon) o).getID() == this.getID() &&
                    ((FirePokemon) o).getHP() == this.getHP() &&
                    (((FirePokemon) o).getAttacks()).equals(this.getAttacks()) &&
                    (((FirePokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }

    @Override
    public void attack(IPokemon other) {
        if (this.canAttack()) {
            other.attackedByFirePokemon(this.getSelectedAttack());
        }
    }

    @Override
    public void attackedByFightingPokemon(IAttack attack) {
        this.receivesNeutralAttack(attack);
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
        this.receivesStrengthenedAttack(attack);
    }

}
