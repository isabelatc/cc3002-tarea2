package cc3002.t1;

import java.util.List;

public interface ITrainer {

    void playPokemon(Pokemon pokemon);
    List<Pokemon> getBench();
    List<ICard> getHand();
    Pokemon getActivePokemon();

    void setActivePokemon();

    void changeActivePokemon(Pokemon abra);

    void getActivePokemonAttacks();

    IAttack selectAttack(int index);

    IAttack getSelectedAttack();
}
