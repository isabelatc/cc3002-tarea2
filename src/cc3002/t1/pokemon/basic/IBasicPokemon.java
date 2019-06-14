package cc3002.t1.pokemon.basic;

import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableVisitor;

public interface IBasicPokemon extends IPokemon {

    @Override
    void isPlayed(PlayCardVisitor v);

    @Override
    boolean canBePlayed(PlayableVisitor v);

}
