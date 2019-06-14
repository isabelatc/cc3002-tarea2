package cc3002.t1.pokemon.stage1;

import cc3002.t1.abilities.IAttack;
import cc3002.t1.pokemon.AbstractFightingPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableVisitor;

import java.util.ArrayList;

public class Stage1FightingPokemon extends AbstractFightingPokemon implements IStage1Pokemon {

    private int preEvolutionID;

    public Stage1FightingPokemon(String name, int id, int hp, ArrayList<IAttack> attackList, int preEvolutionID) {
        super(name, id, hp, attackList);
        this.preEvolutionID = preEvolutionID;
    }

    @Override
    public int getPreEvolutionID() {
        return this.preEvolutionID;
    }

    @Override
    public void isPlayed(PlayCardVisitor v) {
        v.visitStage1Pokemon(this);
    }

    @Override
    public boolean canBePlayed(PlayableVisitor v) {
        v.visitStage1Pokemon(this);
        return v.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Stage1FightingPokemon) {
            return (((Stage1FightingPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((Stage1FightingPokemon) o).getID() == this.getID() &&
                    ((Stage1FightingPokemon) o).getHP() == this.getHP() &&
                    (((Stage1FightingPokemon) o).getAttacks()).equals(this.getAttacks()) &&
                    (((Stage1FightingPokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }
}
