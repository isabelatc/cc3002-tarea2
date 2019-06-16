package cc3002.t1.trainercards.object;

import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.trainercards.AbstractTrainerCard;
import cc3002.t1.visitors.PlayCardVisitor;

/**
 * Abstract class for generic object cards.
 *
 * @author Isabela Tellechea Coluccio
 */
public abstract class AbstractObjectCard extends AbstractTrainerCard {

    private boolean instantEffect;
    private IPokemon associatedPokemon;

    /**
     * Constructor of an object card. At the moment there is no trainer associated to it, and by default its
     * effect is not instant (once it's played, it remains with the Pokémon it affects). If it has an instant
     * effect, then after used it's discarded.
     *
     * @param name The card's name.
     * @param description The card's description.
     */
    public AbstractObjectCard(String name, String description) {
        super(name, description);
        this.instantEffect = false;
        associatedPokemon = null;
    }

    @Override
    public abstract void isPlayed(PlayCardVisitor v);

    /**
     * This method applies when the ability does not have an instant effect, and it remains with the pokémon
     * that played it.
     */
    public void NonInstantAction() {
        if (!instantEffect) {
            associatedPokemon = this.getTrainer().getSelectedPokemon();
            associatedPokemon.associateCard(this);
        }
    }

    /**
     * @return True if the effect is instant, false otherwise.
     */
    public boolean isInstantEffect() {
        return this.instantEffect;
    }

}
