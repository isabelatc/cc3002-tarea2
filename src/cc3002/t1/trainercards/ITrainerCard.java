package cc3002.t1.trainercards;

import cc3002.t1.general.ICard;
import cc3002.t1.visitors.PlayCardVisitor;

/**
 * Common Interface for trainer cards.
 */
public interface ITrainerCard extends ICard {

    /**
     * @return The trainer card's description.
     */
    String getTCDescription();

    @Override
    void isPlayed(PlayCardVisitor v);
}
