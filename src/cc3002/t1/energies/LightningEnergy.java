package cc3002.t1.energies;

import cc3002.t1.AbstractEnergy;
import cc3002.t1.IPokemon;
import cc3002.t1.ITrainer;

/**
 * Class of the lightning energy cards. It inherites methods from its abstract superclass AbstractEnergy.
 *
 * @author Isabela Tellechea Coluccio
 */
public class LightningEnergy extends AbstractEnergy {

    /**
     * Creates a new instance of a lightning energy card.
     *
     * @param name The name of the card.
     */
    public LightningEnergy(String name) {
        super(name);
    }

    /**
     * Empty constructor for a lightning energy card.
     */
    public LightningEnergy() { super("A Lightning Energy"); }

    @Override
    public void isPlayed(ITrainer trainer) {
        super.isPlayed(trainer);
    }

    @Override
    public void isAdded(IPokemon pokemon) {
        pokemon.addLightningEnergy();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LightningEnergy) {
            return (((LightningEnergy) o).getCardName()).equals(this.getCardName());
        }
        return false;
    }

}