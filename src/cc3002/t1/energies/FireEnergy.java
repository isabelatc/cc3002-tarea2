package cc3002.t1.energies;

import cc3002.t1.pokemon.IPokemon;

/**
 * Class of the fire energy cards. It inherites methods from its abstract superclass AbstractEnergy.
 *
 * @author Isabela Tellechea Coluccio
 */
public class FireEnergy extends AbstractEnergy {

    /**
     * Creates a new instance of a fire energy card.
     *
     * @param name The name of the card.
     */
    public FireEnergy(String name) {
        super(name);
    }

    /**
     * Empty constructor for a fire energy card.
     */
    public FireEnergy() { super("A Fire Energy"); }

    @Override
    public void isAdded(IPokemon pokemon) {
        pokemon.getEnergyList().addFireEnergy(1);
        this.getTrainer().removeFromHand(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FireEnergy) {
            return (((FireEnergy) o).getCardName()).equals(this.getCardName());
        }
        return false;
    }

}