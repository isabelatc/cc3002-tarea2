package cc3002.t1;

public abstract class AbstractEnergy implements ICard, IEnergy {

    private String name;

    /**
     * Creates an energy card.
     *
     * @param name The card's name.
     */
    protected AbstractEnergy(String name) { this.name = name; }

    @Override
    public void isPlayed(ITrainer trainer) {
        trainer.playEnergy(this);
    }

    @Override
    public abstract void isAdded(IPokemon pokemon);

    @Override
    public String getCardName() {
        return this.name;
    }

    @Override
    public abstract boolean equals(Object o);
}
