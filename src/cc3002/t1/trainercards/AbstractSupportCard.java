package cc3002.t1.trainercards;

import cc3002.t1.visitors.PlayCardVisitor;

public abstract class AbstractSupportCard extends AbstractTrainerCard {

    /**
     * Constructor of a support card. At the moment there is no trainer associated to it.
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
