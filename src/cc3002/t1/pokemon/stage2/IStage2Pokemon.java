package cc3002.t1.pokemon.stage2;

import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableVisitor;

public interface IStage2Pokemon extends IPokemon {

    /**
     * @return The pokémon's preevolution ID, when it's in stage 1 or 2.
     */
    int getPreEvolutionID();

    @Override
    void isPlayed(PlayCardVisitor v);

    @Override
    boolean canBePlayed(PlayableVisitor v);

}
