package cc3002.t1.energies;

import cc3002.t1.general.AbstractCard;
import cc3002.t1.general.ICard;
import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.visitors.PlayCardVisitor;

/**
 * Class for a generic energy card.
 *
 * @author Isabela Tellechea Coluccio
 */
public abstract class AbstractEnergy extends AbstractCard implements ICard, IEnergy {

    /**
     * Constructor of a generic energy card. At the moment there is no trainer associated to it.
     *
     * @param name The card's name.
     */
    protected AbstractEnergy(String name) {
        super(name);
    }

    @Override
    public void isPlayed(PlayCardVisitor v) {
        v.visitEnergy(this);
    }

    @Override
    public abstract void isAdded(IPokemon pokemon);

    @Override
    public abstract boolean equals(Object o);

}
