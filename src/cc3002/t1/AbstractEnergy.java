package cc3002.t1;

public abstract class AbstractEnergy extends AbstractCard implements ICard, IEnergy {

    /**
     * Creates an energy card. At the moment there is no trainer associated to it.
     *
     * @param name The card's name.
     */
    protected AbstractEnergy(String name) {
        super(name);
    }

    @Override
    public void isPlayed() {
        if (this.getTrainer().getActivePokemon() != null) {
            this.getTrainer().getActivePokemon().addEnergyToPokemon(this);
        }
    }

    @Override
    public abstract void isAdded(IPokemon pokemon);

    @Override
    public abstract boolean equals(Object o);

}
