package cc3002.t1.general;

import cc3002.t1.abilities.IAttack;
import cc3002.t1.general.EnergyCounter;
import cc3002.t1.general.ICard;
import cc3002.t1.general.ITrainer;
import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.visitors.PlayCardVisitor;

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
    private List<ICard> deck, hand, discardPile, prizes;
    private List<IPokemon> bench;
    private IPokemon selectedPokemon;

    /**
     * Constructor for a new Trainer. Some of their properties are empty when initialized.
     *
     * @param deck Trainer's deck of cards.
     */
    public Trainer(List<ICard> deck) {
        this.activePokemon = null;
        if(deck.size() == 60) this.deck = deck;
        this.hand = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.prizes = new ArrayList<>();
        this.bench = new ArrayList<>();
        this.selectedPokemon = activePokemon;
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
    public List<ICard> getDiscardPile() {
        return this.discardPile;
    }

    @Override
    public List<ICard> getDeck() {
        return this.deck;
    }

    @Override
    public List<ICard> getPrizes() {
        return this.prizes;
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
    public IPokemon getSelectedPokemon() {
        return this.selectedPokemon;
    }

    @Override
    public void drawFromDeck() {
        if (getDeck().size() > 0) {
            ICard card = deck.get(0);
            deck.remove(0);
            this.addToHand(card);
        }
    }

    @Override
    public void playCard(ICard card) {
        if (hand.contains(card)) {
            card.setTrainer(this);
            PlayCardVisitor v = new PlayCardVisitor();
            card.isPlayed(v);
        }
    }

    @Override
    public void addToHand(ICard card) {
        hand.add(card);
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
    public void addToDiscardPile(ICard card) {
        discardPile.add(card);
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
    public void setSelectedPokemon(IPokemon pokemon) {
        if (bench.contains(pokemon) || activePokemon.equals(pokemon)) selectedPokemon = pokemon;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ITrainer) {

            List<ICard> theDeck = ((ITrainer) o).getDeck();
            List<ICard> thisDeck = this.getDeck();
            List<ICard> theDiscardPile = ((ITrainer) o).getDiscardPile();
            List<ICard> thisDiscardPile = this.getDiscardPile();
            List<ICard> theHand = ((ITrainer) o).getHand();
            List<ICard> thisHand = this.getHand();
            List<ICard> thePrizes = ((ITrainer) o).getPrizes();
            List<ICard> thisPrizes = this.getPrizes();
            List<IPokemon> theBench = ((ITrainer) o).getBench();
            List<IPokemon> thisBench = this.getBench();
            IPokemon thePokemon = ((ITrainer) o).getActivePokemon();
            IPokemon thisPokemon = this.getActivePokemon();

            boolean deckResult;
            boolean discardPileResult;
            boolean handResult;
            boolean prizesResult;
            boolean benchResult;
            boolean pokemonResult;

            if (theDeck == null || thisDeck == null) deckResult = (theDeck == null && thisDeck == null);
            else deckResult = (theDeck.equals(thisDeck));

            if (theDiscardPile == null || thisDiscardPile == null) discardPileResult = (theDiscardPile == null && thisDiscardPile == null);
            else discardPileResult = (theDiscardPile.equals(thisDiscardPile));

            if (theHand == null || thisHand == null) handResult = (theHand == null && thisHand == null);
            else handResult = (theHand.equals(thisHand));

            if (thePrizes == null || thisPrizes == null) prizesResult = (thePrizes == null && thisPrizes == null);
            else prizesResult = (thePrizes.equals(thisPrizes));

            if (theBench == null || thisBench == null) benchResult = (theBench == null && thisBench == null);
            else benchResult = (theBench.equals(thisBench));

            if (thePokemon == null || thisPokemon == null) pokemonResult = (thePokemon == null && thisPokemon == null);
            else pokemonResult = (thePokemon.equals(thisPokemon));

            return deckResult && discardPileResult && handResult && prizesResult && benchResult && pokemonResult;
        }
        return false;
    }

    @Override
    public boolean flipACoin() {
        return Math.random() < 0.5;
    }

    @Override
    public boolean isOnField(IPokemon pokemon) {
        if (pokemon.equals(getActivePokemon()) || getBench().contains(pokemon)) return true;
        return false;
    }

    @Override
    public void removeFromField(IPokemon pokemon) {
        if (this.isOnField(pokemon)) {
            if (pokemon.equals(getActivePokemon())) setActivePokemon();
            else getBench().remove(pokemon);
        }
    }

}
