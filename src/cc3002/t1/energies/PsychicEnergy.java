package cc3002.t1.energies;

import cc3002.t1.AbstractEnergy;
import cc3002.t1.IPokemon;
import cc3002.t1.ITrainer;

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
    public void isPlayed(ITrainer trainer) {
        super.isPlayed(trainer);
    }

    @Override
    public void isAdded(IPokemon pokemon) {
        pokemon.addPsychicEnergy();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof PsychicEnergy) {
            return (((PsychicEnergy) o).getCardName()).equals(this.getCardName());
        }
        return false;
    }

}