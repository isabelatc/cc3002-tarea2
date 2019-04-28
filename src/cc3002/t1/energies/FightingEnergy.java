package cc3002.t1.energies;

import cc3002.t1.AbstractEnergy;
import cc3002.t1.IPokemon;
import cc3002.t1.ITrainer;

/**
 * Class of the fighting energy cards. It inherites methods from its abstract superclass AbstractEnergy.
 *
 * @author Isabela Tellechea Coluccio
 */
public class FightingEnergy extends AbstractEnergy {

    /**
     * Creates a new instance of a fighting energy card.
     *
     * @param name The name of the card.
     */
    public FightingEnergy(String name) {
        super(name);
    }

    /**
     * Empty constructor for a fighting energy card.
     */
    public FightingEnergy() { super("A Fighting Energy"); }

    @Override
    public void isPlayed(ITrainer trainer) {
        super.isPlayed(trainer);
    }

    @Override
    public void isAdded(IPokemon pokemon) {
        pokemon.addFightingEnergy();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof FightingEnergy) {
            return (((FightingEnergy) o).getCardName()).equals(this.getCardName());
        }
        return false;
    }

}
