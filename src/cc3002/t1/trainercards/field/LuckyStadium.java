package cc3002.t1.trainercards.field;

import cc3002.t1.visitors.PlayCardVisitor;

/**
 * This class describes a specific implementation of a field card. Once per turn, the trainer who played this card
 * gets to flip a coin. If the result is heads, they can draw a card from their deck. Nothing happens otherwise.
 */
public class LuckyStadium extends AbstractFieldCard {

    /**
     * Constructor of a specific field card, Lucky Stadium.
     *
     * @param name The card's name.
     * @param description The card's description.
     */
    public LuckyStadium(String name, String description) {
        super(name, description);
    }

    @Override
    public void isPlayed(PlayCardVisitor v) {
        this.getTrainer().setActiveFieldCard(this);
        v.visitLuckyStadium(this);
    }

}
