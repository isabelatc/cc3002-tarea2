package cc3002.t1.trainercards;

import cc3002.t1.general.AbstractCard;
import cc3002.t1.general.ICard;
import cc3002.t1.visitors.PlayCardVisitor;

public abstract class AbstractTrainerCard extends AbstractCard implements ICard, ITrainerCard {

    private String description;

    /**
     * Constructor of a generic trainer card. At the moment there is no trainer associated to it.
     *
     * @param name The card's name.
     * @param description The card's description.
     */
    public AbstractTrainerCard(String name, String description) {
        super(name);
        this.description = description;
    }

    @Override
    public abstract void isPlayed(PlayCardVisitor v);

    public String getTCDescription() {
        return this.description;
    }

}
