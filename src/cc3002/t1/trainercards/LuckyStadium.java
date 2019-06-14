package cc3002.t1.trainercards;

import cc3002.t1.visitors.PlayCardVisitor;

public class LuckyStadium extends AbstractFieldCard {

    public LuckyStadium(String name, String description) {
        super(name, description);
    }

    public void isPlayed(PlayCardVisitor v) {
        v.visitLuckyStadium(this);
    }

}
