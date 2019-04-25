package cc3002.t1;

import java.util.List;

public class Trainer implements ITrainer {

    private List<Pokemon> bench;
    private List<ICard> hand;

    @Override
    public void playPokemon(Pokemon pokemon) {

    }

    @Override
    public List<Pokemon> getBench() {
        return null;
    }

    @Override
    public List<ICard> getHand() {
        return null;
    }

    @Override
    public Pokemon getActivePokemon() {
        return null;
    }
}
