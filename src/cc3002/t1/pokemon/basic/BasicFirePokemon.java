package cc3002.t1.pokemon.basic;

import cc3002.t1.abilities.IAttack;
import cc3002.t1.pokemon.AbstractFirePokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableVisitor;

import java.util.ArrayList;

public class BasicFirePokemon extends AbstractFirePokemon implements IBasicPokemon {

    public BasicFirePokemon(String name, int id, int hp, ArrayList<IAttack> attackList) {
        super(name, id, hp, attackList);
    }

    @Override
    public void isPlayed(PlayCardVisitor v) {
        v.visitBasicPokemon(this);
    }

    @Override
    public boolean canBePlayed(PlayableVisitor v) {
        v.visitBasicPokemon(this);
        return v.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BasicFirePokemon) {
            return (((BasicFirePokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((BasicFirePokemon) o).getID() == this.getID() &&
                    ((BasicFirePokemon) o).getHP() == this.getHP() &&
                    (((BasicFirePokemon) o).getAttacks()).equals(this.getAttacks()) &&
                    (((BasicFirePokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }
}
