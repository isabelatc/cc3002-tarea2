package cc3002.t1;

public abstract class AbstractEnergy implements ICard, IEnergy {

    private String name;
    private ITrainer trainer;

    /**
     * Creates an energy card.
     *
     * @param name The card's name.
     */
    protected AbstractEnergy(String name) {
        this.name = name;
        this.trainer = null;
    }

    @Override
    public void isPlayed() {
        this.isAdded(this.getTrainer().getActivePokemon());
    }

    @Override
    public abstract void isAdded(IPokemon pokemon);

    @Override
    public String getCardName() {
        return this.name;
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public ITrainer getTrainer() {
        return trainer;
    }

    @Override
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

}
