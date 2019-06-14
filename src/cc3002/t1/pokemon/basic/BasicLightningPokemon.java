package cc3002.t1.pokemon.basic;

import cc3002.t1.abilities.IAttack;
import cc3002.t1.pokemon.AbstractLightningPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableVisitor;

import java.util.ArrayList;

public class BasicLightningPokemon extends AbstractLightningPokemon implements IBasicPokemon {

    public BasicLightningPokemon(String name, int id, int hp, ArrayList<IAttack> attackList) {
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
        if (o instanceof BasicLightningPokemon) {
            return (((BasicLightningPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((BasicLightningPokemon) o).getID() == this.getID() &&
                    ((BasicLightningPokemon) o).getHP() == this.getHP() &&
                    (((BasicLightningPokemon) o).getAttacks()).equals(this.getAttacks()) &&
                    (((BasicLightningPokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }
}
