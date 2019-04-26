package cc3002.t1;

import java.util.List;

public class Trainer implements ITrainer {

    private IPokemon activePokemon;
    private List<ICard> bench;
    private List<IPokemon> hand;

    /**
     * Creates a new Trainer. Some of their properties are empty when initialized.
     *
     * @param hand Trainer's hand of cards.
     */
    public Trainer(List<IPokemon> hand) {
        this.activePokemon = null;
        this.bench = null;
        this.hand = hand;
    }

    @Override
    public List<IPokemon> getBench() {
        return this.bench;
    }

    @Override
    public List<ICard> getHand() {
        return this.hand;
    }

    @Override
    public void playPokemon(IPokemon pokemon) {

    }

    @Override
    public void playEnergy(IEnergy energy) {

    }

    @Override
    public List<Integer> getPokemonEnergies(IPokemon pokemon) {
        return null;
    }

    @Override
    public IPokemon getActivePokemon() {
        return null;
    }

    @Override
    public void changeActivePokemon(IPokemon pokemon) {

    }

    @Override
    public void setActivePokemon() {

    }

    @Override
    public List<IAttack> getActivePokemonAttacks() {

        return null;
    }

    @Override
    public IAttack selectAttack(int index) {
        return null;
    }

}
