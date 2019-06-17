package cc3002.t1.visitors;

import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.pokemon.basic.IBasicPokemon;
import cc3002.t1.pokemon.stage1.IStage1Pokemon;
import cc3002.t1.pokemon.stage2.IStage2Pokemon;

/**
 * Class of the visitor that checks if a card can be played.
 *
 * @author Isabela Tellechea Coluccio
 */
public class PlayableCardVisitor extends AbstractCardsVisitor {

    private boolean value;
    private int preID = -1;

    @Override
    public void visitBasicPokemon(IBasicPokemon pokemon) {
        value = true;
    }

    @Override
    public void visitNonBasicPokemon(IPokemon pokemon) {
        if (pokemon.getTrainer().getActivePokemon() != null && preID == pokemon.getTrainer().getActivePokemon().getID()) {
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

    /**
     * @return The value of the visitor, true if the card can be played, and false otherwise.
     */
    public boolean getValue() { return this.value; }

    /**
     * @return The ID of the preevolution associated to the card being played (only changes if the card is a
     * non basic pokémon).
     */
    public int getPreID() {
        return this.preID;
    }

}
