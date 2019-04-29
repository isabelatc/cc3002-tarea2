package cc3002.t1;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the Pokémon trainers. It has methods to access to their properties, and for the necessary
 * actions to play their turns.
 *
 * @author Isabela Tellechea Coluccio
 */
public class Trainer implements ITrainer {

    private IPokemon activePokemon;
    private List<IPokemon> bench;
    private List<ICard> hand;

    /**
     * Creates a new Trainer. Some of their properties are empty when initialized.
     *
     * @param hand Trainer's hand of cards.
     */
    public Trainer(List<ICard> hand) {
        this.activePokemon = null;
        this.bench = new ArrayList<>();
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
        if (bench.size() == 5) {
            return;
        }
        else {
            if (hand.contains(pokemon)) {
                hand.remove(pokemon);
                bench.add(pokemon);
                return;
            }
        }
    }

    @Override
    public void playEnergy(IEnergy energy) {
        if (hand.contains(energy) && activePokemon != null) {
            hand.remove(energy);
            energy.isAdded(activePokemon);
            return;
        }
    }

    @Override
    public List<Integer> getPokemonEnergies(IPokemon pokemon) {
        return pokemon.getEnergies();
    }

    @Override
    public IPokemon getActivePokemon() { return this.activePokemon; }

    @Override
    public void changeActivePokemon(IPokemon pokemon) {
        if (bench.contains(pokemon)) {
            bench.remove(pokemon);
            bench.add(activePokemon);
            activePokemon = pokemon;
            return;
        }
    }

    @Override
    public void setActivePokemon() {
        if (bench.size() != 0) {
            activePokemon = bench.get(0);
            bench.remove(0);
        }
    }

    @Override
    public List<IAttack> getActivePokemonAttacks() {
        return activePokemon.getAttacks();
    }

    @Override
    public void selectPokemonAttack(int index) {
        activePokemon.selectAttack(index);
    }

}
