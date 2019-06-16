package cc3002.t1.trainercards.field;

import cc3002.t1.trainercards.AbstractTrainerCard;
import cc3002.t1.visitors.PlayCardVisitor;

/**
 * Abstract class for generic field cards.
 *
 * @author Isabela Tellechea Coluccio
 */
public abstract class AbstractFieldCard extends AbstractTrainerCard {

    /**
     * Constructor of a field card. At the moment there is no trainer associated to it. These cards remain in the
     * field once they are played and can only be removed by playing another field card (it replaces it), or any card
     * with that effect.
     *
     * @param name The card's name.
     * @param description The card's description.
     */
    public AbstractFieldCard(String name, String description) {
        super(name, description);
    }

    @Override
    public abstract void isPlayed(PlayCardVisitor v);

}
