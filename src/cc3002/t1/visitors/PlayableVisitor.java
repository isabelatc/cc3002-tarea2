package cc3002.t1.visitors;

import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.pokemon.basic.IBasicPokemon;
import cc3002.t1.pokemon.stage1.IStage1Pokemon;
import cc3002.t1.pokemon.stage2.IStage2Pokemon;

public class PlayableVisitor extends Visitor{

    private boolean value;
    private int preID;

    @Override
    public void visitBasicPokemon(IBasicPokemon pokemon) {
        value = true;
    }

    @Override
    public void visitNonBasicPokemon(IPokemon pokemon) {
        if (preID == pokemon.getTrainer().getActivePokemon().getID()) {
            value = true;
            pokemon.getTrainer().setSelectedPokemon(pokemon.getTrainer().getActivePokemon());
            return;
        }
        for (IPokemon benchedPokemon : pokemon.getTrainer().getBench()) {
            if (preID == benchedPokemon.getID()) {
                value = true;
                pokemon.getTrainer().setSelectedPokemon(benchedPokemon);
                return;
            }
        }
        value = false;
    }

    @Override
    public void visitStage1Pokemon(IStage1Pokemon pokemon) {
        preID = pokemon.getPreEvolutionID();
        this.visitNonBasicPokemon(pokemon);
    }

    @Override
    public void visitStage2Pokemon(IStage2Pokemon pokemon) {
        preID = pokemon.getPreEvolutionID();
        this.visitNonBasicPokemon(pokemon);
    }

    public boolean getValue() { return this.value; }

}
