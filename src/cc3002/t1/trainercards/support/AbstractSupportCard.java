package cc3002.t1.trainercards.support;

import cc3002.t1.trainercards.AbstractTrainerCard;
import cc3002.t1.visitors.PlayCardVisitor;

/**
 * Abstract class for a generic support card.
 *
 * @author Isabela Tellechea Coluccio
 */
public abstract class AbstractSupportCard extends AbstractTrainerCard {

    /**
     * Constructor of a support card. At the moment there is no trainer associated to it. These cards are always
     * discarded after they're used.
     *
     * @param name The card's name.
     * @param description The card's description.
     */
    public AbstractSupportCard(String name, String description) {
        super(name, description);
    }

    @Override
    public abstract void isPlayed(PlayCardVisitor v);

}
