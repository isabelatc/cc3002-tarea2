package cc3002.t1.pokemon.stage2;

import cc3002.t1.abilities.IAttack;
import cc3002.t1.pokemon.AbstractFightingPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableVisitor;

import java.util.ArrayList;

public class Stage2FightingPokemon extends AbstractFightingPokemon implements IStage2Pokemon {

    private int preEvolutionID;

    public Stage2FightingPokemon(String name, int id, int hp, ArrayList<IAttack> attackList, int preEvolutionID) {
        super(name, id, hp, attackList);
        this.preEvolutionID = preEvolutionID;
    }

    @Override
    public int getPreEvolutionID() {
        return this.preEvolutionID;
    }

    @Override
    public void isPlayed(PlayCardVisitor v) {
        v.visitStage2Pokemon(this);
    }

    @Override
    public boolean canBePlayed(PlayableVisitor v) {
        v.visitStage2Pokemon(this);
        return v.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Stage2FightingPokemon) {
            return (((Stage2FightingPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((Stage2FightingPokemon) o).getID() == this.getID() &&
                    ((Stage2FightingPokemon) o).getHP() == this.getHP() &&
                    (((Stage2FightingPokemon) o).getAttacks()).equals(this.getAttacks()) &&
                    (((Stage2FightingPokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }

}
