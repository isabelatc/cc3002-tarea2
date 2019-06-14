package cc3002.t1.pokemon.stage2;

import cc3002.t1.abilities.IAttack;
import cc3002.t1.pokemon.AbstractLightningPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableVisitor;

import java.util.ArrayList;

public class Stage2LightningPokemon extends AbstractLightningPokemon implements IStage2Pokemon {

    private int preEvolutionID;

    public Stage2LightningPokemon(String name, int id, int hp, ArrayList<IAttack> attackList, int preEvolutionID) {
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
        if (o instanceof Stage2LightningPokemon) {
            return (((Stage2LightningPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((Stage2LightningPokemon) o).getID() == this.getID() &&
                    ((Stage2LightningPokemon) o).getHP() == this.getHP() &&
                    (((Stage2LightningPokemon) o).getAttacks()).equals(this.getAttacks()) &&
                    (((Stage2LightningPokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }
}
