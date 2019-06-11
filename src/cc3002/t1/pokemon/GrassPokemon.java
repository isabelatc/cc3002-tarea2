package cc3002.t1.pokemon;

import cc3002.t1.AbstractPokemon;
import cc3002.t1.IAttack;
import cc3002.t1.IPokemon;

import java.util.ArrayList;

/**
 * Class of the grass pokémon cards. It inherites methods from its abstract superclass AbstractPokemon.
 *
 * @author Isabela Tellechea Coluccio
 */
public class GrassPokemon extends AbstractPokemon {

    /**
     * Creates a new instance of a grass Pokémon.
     *
     * @param name The name of the Pokémon.
     * @param id The identification number of the Pokémon (according to the Pokédex).
     * @param hp The initial hit points of the Pokémon.
     * @param attackList The list of attacks the Pokémon can use.
     */
    public GrassPokemon(String name, int id, int hp, ArrayList<IAttack> attackList) {
        super(name, id, hp, attackList);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GrassPokemon) {
            return (((GrassPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((GrassPokemon) o).getID() == this.getID() &&
                    ((GrassPokemon) o).getHP() == this.getHP() &&
                    (((GrassPokemon) o).getAttacks()).equals(this.getAttacks()) &&
                    (((GrassPokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }

    @Override
    public void attack(IPokemon other) {
        if (this.canAttack()) {
            other.attackedByGrassPokemon(this.getSelectedAttack());
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
