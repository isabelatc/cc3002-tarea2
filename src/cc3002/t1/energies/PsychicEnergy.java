package cc3002.t1.energies;

import cc3002.t1.pokemon.IPokemon;

/**
 * Class of the psychic energy cards. It inherites methods from its abstract superclass AbstractEnergy.
 *
 * @author Isabela Tellechea Coluccio
 */
public class PsychicEnergy extends AbstractEnergy {

    /**
     * Creates a new instance of a psychic energy card.
     *
     * @param name The name of the card.
     */
    public PsychicEnergy(String name) {
        super(name);
    }

    /**
     * Empty constructor for a psychic energy card.
     */
    public PsychicEnergy() { super("A Psychic Energy"); }

    @Override
    public void isAdded(IPokemon pokemon) {
        pokemon.getEnergyList().addPsychicEnergy(1);
        this.getTrainer().removeFromHand(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PsychicEnergy) {
            return (((PsychicEnergy) o).getCardName()).equals(this.getCardName());
        }
        return false;
    }

}