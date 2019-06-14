package cc3002.t1.pokemon.basic;

import cc3002.t1.abilities.IAttack;
import cc3002.t1.pokemon.AbstractGrassPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableVisitor;

import java.util.ArrayList;

public class BasicGrassPokemon extends AbstractGrassPokemon implements IBasicPokemon {

    public BasicGrassPokemon(String name, int id, int hp, ArrayList<IAttack> attackList) {
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
        if (o instanceof BasicGrassPokemon) {
            return (((BasicGrassPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((BasicGrassPokemon) o).getID() == this.getID() &&
                    ((BasicGrassPokemon) o).getHP() == this.getHP() &&
                    (((BasicGrassPokemon) o).getAttacks()).equals(this.getAttacks()) &&
                    (((BasicGrassPokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }
}