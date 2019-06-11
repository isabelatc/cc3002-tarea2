package cc3002.t1;

public abstract class AbstractCard implements ICard {

    private String name;
    private ITrainer trainer;

    /**
     * Constructor for a generic card. Its trainer attribute remains null until it's set when the card is played.
     *
     * @param name The name of the card.
     */
    public AbstractCard(String name) {
        this.name = name;
        this.trainer = null;
    }

    @Override
    public String getCardName() {
        return name;
    }

    @Override
    public ITrainer getTrainer() {
        return trainer;
    }

    @Override
    public void setTrainer(ITrainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public abstract void isPlayed();

}
