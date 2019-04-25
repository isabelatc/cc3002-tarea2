package cc3002.t1;

import java.util.List;

public interface ITrainer {

    void playPokemon(Pokemon pokemon);
    List<Pokemon> getBench();
    List<ICard> getHand();
    Pokemon getActivePokemon();
}
