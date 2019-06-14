package cc3002.t1.pokemon.basic;

import cc3002.t1.abilities.IAttack;
import cc3002.t1.pokemon.AbstractPsychicPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableVisitor;

import java.util.ArrayList;

public class BasicPsychicPokemon extends AbstractPsychicPokemon implements IBasicPokemon {

    public BasicPsychicPokemon(String name, int id, int hp, ArrayList<IAttack> attackList) {
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
        if (o instanceof BasicPsychicPokemon) {
            return (((BasicPsychicPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((BasicPsychicPokemon) o).getID() == this.getID() &&
                    ((BasicPsychicPokemon) o).getHP() == this.getHP() &&
                    (((BasicPsychicPokemon) o).getAttacks()).equals(this.getAttacks()) &&
                    (((BasicPsychicPokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }
}
