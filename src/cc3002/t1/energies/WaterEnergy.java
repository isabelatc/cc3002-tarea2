package cc3002.t1.energies;

import cc3002.t1.AbstractEnergy;
import cc3002.t1.IPokemon;
import cc3002.t1.ITrainer;

/**
 * Class of the water energy cards. It inherites methods from its abstract superclass AbstractEnergy.
 *
 * @author Isabela Tellechea Coluccio
 */
public class WaterEnergy extends AbstractEnergy {

    /**
     * Creates a new instance of a water energy card.
     *
     * @param name The name of the card.
     */
    public WaterEnergy(String name) {
        super(name);
    }

    /**
     * Empty constructor for a water energy card.
     */
    public WaterEnergy() { super("A Water Energy"); }

    @Override
    public void isPlayed(ITrainer trainer) {
        super.isPlayed(trainer);
    }

    @Override
    public void isAdded(IPokemon pokemon) {
        pokemon.addWaterEnergy();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof WaterEnergy) {
            return (((WaterEnergy) o).getCardName()).equals(this.getCardName());
        }
        return false;
    }

}