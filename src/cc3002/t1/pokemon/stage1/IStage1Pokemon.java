package cc3002.t1.pokemon.stage1;

import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableCardVisitor;

/**
 * Common interface for stage 1 pokémon.
 *
 * @author Isabela Tellechea Coluccio
 */
public interface IStage1Pokemon extends IPokemon {

    /**
     * @return The pokémon's preevolution ID, when it's in stage 1 or 2.
     */
    int getPreEvolutionID();

    @Override
    void isPlayed(PlayCardVisitor v);

    @Override
    boolean canBePlayed(PlayableCardVisitor v);

}
