package cc3002.t1.trainercards;

import cc3002.t1.visitors.PlayCardVisitor;

public class ProfessorJuniper extends AbstractSupportCard {

    public ProfessorJuniper(String name, String description) {
        super(name, description);
    }

    public void isPlayed(PlayCardVisitor v) {
        v.visitProfessorJuniper(this);
    }
}
