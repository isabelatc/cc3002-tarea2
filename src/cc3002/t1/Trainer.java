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
     * Constructor for a new Trainer. Some of their properties are empty when initialized.
     *
     * @param hand Trainer's hand of cards.
     */
    public Trainer(List<ICard> hand) {
        this.activePokemon = null;
        this.bench = new ArrayList<>();
        this.hand = hand;
    }

    @Override
    public List<ICard> getHand() {
        return this.hand;
    }

    @Override
    public List<IPokemon> getBench() {
        return this.bench;
    }

    @Override
    public IPokemon getActivePokemon() { return this.activePokemon; }

    @Override
    public EnergyCounter getPokemonEnergy(IPokemon pokemon) {
        return pokemon.getEnergyList();
    }

    @Override
    public List<IAttack> getPokemonAttacks(IPokemon pokemon) {
        return pokemon.getAttacks();
    }

    @Override
    public void playCard(ICard card) {
        if (hand.contains(card)) {
            card.setTrainer(this);
            card.isPlayed();
        }
    }

    @Override
    public void removeFromHand(ICard card) {
        hand.remove(card);
    }

    @Override
    public void addToBench(IPokemon pokemon) {
        if (bench.size() < 5) {
            if (hand.contains(pokemon)) {
                this.removeFromHand(pokemon);
                bench.add(pokemon);
            }
        }
    }

    @Override
    public void changeActivePokemon(IPokemon pokemon) {
        if (bench.contains(pokemon)) {
            bench.remove(pokemon);
            if (activePokemon != null) this.addToBench(activePokemon);
            activePokemon = pokemon;
        }
    }

    @Override
    public void setActivePokemon() {
        if (bench.size() > 0) {
            activePokemon = bench.get(0);
            bench.remove(0);
        }
    }

    @Override
    public void selectAttack(int index) {
        activePokemon.setAttack(index);
    }

    @Override
    public void useAttack(ITrainer opponent) {
        activePokemon.attack(opponent.getActivePokemon());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ITrainer) {

            List<ICard> theHand = ((ITrainer) o).getHand();
            List<ICard> thisHand = this.getHand();
            List<IPokemon> theBench = ((ITrainer) o).getBench();
            List<IPokemon> thisBench = this.getBench();
            IPokemon thePokemon = ((ITrainer) o).getActivePokemon();
            IPokemon thisPokemon = this.getActivePokemon();
            boolean handResult;
            boolean benchResult;
            boolean pokemonResult;

            if (theHand == null || thisHand == null) handResult = (theHand == null && thisHand == null);
            else handResult = (theHand.equals(thisHand));

            if (theBench == null || thisBench == null) benchResult = (theBench == null && thisBench == null);
            else benchResult = (theBench.equals(thisBench));

            if (thePokemon == null || thisPokemon == null) pokemonResult = (thePokemon == null && thisPokemon == null);
            else pokemonResult = (thePokemon.equals(thisPokemon));

            return handResult && benchResult && pokemonResult;
        }
        return false;
    }
}
