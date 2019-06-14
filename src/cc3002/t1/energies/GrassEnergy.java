package cc3002.t1.energies;

import cc3002.t1.pokemon.IPokemon;

/**
 * Class of the grass energy cards. It inherites methods from its abstract superclass AbstractEnergy.
 *
 * @author Isabela Tellechea Coluccio
 */
public class GrassEnergy extends AbstractEnergy {

    /**
     * Creates a new instance of a grass energy card.
     *
     * @param name The name of the card.
     */
    public GrassEnergy(String name) {
        super(name);
    }

    /**
     * Empty constructor for a grass energy card.
     */
    public GrassEnergy() { super("A Grass Energy"); }

    @Override
    public void isAdded(IPokemon pokemon) {
        pokemon.getEnergyList().addGrassEnergy();
        this.getTrainer().removeFromHand(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof GrassEnergy) {
            return (((GrassEnergy) o).getCardName()).equals(this.getCardName());
        }
        return false;
    }

}