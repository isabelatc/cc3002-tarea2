package cc3002.t1.pokemon.basic;

import cc3002.t1.abilities.IAttack;
import cc3002.t1.pokemon.AbstractWaterPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableVisitor;

import java.util.ArrayList;

public class BasicWaterPokemon extends AbstractWaterPokemon implements IBasicPokemon {

    public BasicWaterPokemon(String name, int id, int hp, ArrayList<IAttack> attackList) {
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
        if (o instanceof BasicWaterPokemon) {
            return (((BasicWaterPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((BasicWaterPokemon) o).getID() == this.getID() &&
                    ((BasicWaterPokemon) o).getHP() == this.getHP() &&
                    (((BasicWaterPokemon) o).getAttacks()).equals(this.getAttacks()) &&
                    (((BasicWaterPokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }
}
