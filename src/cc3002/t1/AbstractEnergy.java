package cc3002.t1;

public abstract class AbstractEnergy implements ICard, IEnergy {

    private String name;
    private ITrainer trainer;

    /**
     * Creates an energy card. At the moment there is no trainer associated to it.
     *
     * @param name The card's name.
     */
    protected AbstractEnergy(String name) {
        this.name = name;
        this.trainer = null;
    }

    @Override
    public String getCardName() {
        return this.name;
    }

    @Override
    public ITrainer getTrainer() {
        return this.trainer;
    }

    @Override
    public void setTrainer(ITrainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public void isPlayed() {
        if (trainer.getActivePokemon() != null) {
            trainer.getActivePokemon().addEnergyToPokemon(this);
        }
    }

    @Override
    public abstract void isAdded(IPokemon pokemon);

    @Override
    public abstract boolean equals(Object o);

}
