package cc3002.t1.trainercards.support;

import cc3002.t1.visitors.PlayCardVisitor;

/**
 * This class describes a specific implementation of a support card. It allows the trainer to discard their
 * entire hand and draw 7 new cards from their deck.
 */
public class ProfessorJuniper extends AbstractSupportCard {

    /**
     * Constructor of a specific support card, Professor Juniper.
     *
     * @param name The card's name.
     * @param description The card's description.
     */
    public ProfessorJuniper(String name, String description) {
        super(name, description);
    }

    @Override
    public void isPlayed(PlayCardVisitor v) {
        v.visitProfessorJuniper(this);
    }

}
