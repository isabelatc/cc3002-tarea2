package cc3002.t1.trainercards.object;

import cc3002.t1.visitors.PlayCardVisitor;

/**
 * This class describes a specific implementation of an object card. The trainer flips a coin, if it's heads, they
 * choose a Pokémon in the field and adds it along with all its associated cards to their hand.
 */
public class SuperScoopUp extends AbstractObjectCard {

    /**
     * Constructor of a specific object card, Super Scoop Up.
     *
     * @param name The card's name.
     * @param description The card's description.
     */
    public SuperScoopUp(String name, String description) {
        super(name, description);
    }

    @Override
    public void isPlayed(PlayCardVisitor v) {
        v.visitSuperScoopUp(this);
    }

}
