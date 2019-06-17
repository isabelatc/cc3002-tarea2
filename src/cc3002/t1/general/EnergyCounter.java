package cc3002.t1.general;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Class of the structure that will be used to keep track of a card's energies.
 *
 * @author Isabela Tellechea Coluccio
 */
public class EnergyCounter {
    private Map<EnergyType, Integer> energies = new EnumMap<>(EnergyType.class);

    /**
     * Creates a new instance of an energy counter.
     */
    public EnergyCounter() {
        for (EnergyType energy : EnergyType.values()) {
            energies.put(energy, 0);
        }
    }

    /**
     * Set the value of a specific energy type to the one provided.
     *
     * @param value The value the counter will have after the execution.
     * @param type The energy type to be changed.
     */
    public void setEnergyByType(int value, EnergyType type) {
        energies.put(type, value);
    }

    /**
     * Sets the value of the fighting energy count to the one provided.
     *
     * @param value The value the fighting energy count will have after the execution.
     */
    public void setFightingEnergy(int value) {
        setEnergyByType(value, EnergyType.FIGHTING);
    }

    /**
     * Sets the value of the fire energy count to the one provided.
     *
     * @param value The value the fire energy count will have after the execution.
     */
    public void setFireEnergy(int value) {
        setEnergyByType(value, EnergyType.FIRE);
    }

    /**
     * Sets the value of the grass energy count to the one provided.
     *
     * @param value The value the grass energy count will have after the execution.
     */
    public void setGrassEnergy(int value) {
        setEnergyByType(value, EnergyType.GRASS);
    }

    /**
     * Sets the value of the lightning energy count to the one provided.
     *
     * @param value The value the lightning energy count will have after the execution.
     */
    public void setLightningEnergy(int value) {
        setEnergyByType(value, EnergyType.LIGHTNING);
    }

    /**
     * Sets the value of the psychic energy count to the one provided.
     *
     * @param value The value the psychic energy count will have after the execution.
     */
    public void setPsychicEnergy(int value) {
        setEnergyByType(value, EnergyType.PSYCHIC);
    }

    /**
     * Sets the value of the water energy count to the one provided.
     *
     * @param value The value the water energy count will have after the execution.
     */
    public void setWaterEnergy(int value) { setEnergyByType(value, EnergyType.WATER); }

    /**
     * @return The dictionary that represents the amount of energy of the counter.
     */
    public Map<EnergyType, Integer> getEnergies() {
        return energies;
    }

    /**
     * @param type The energy type that's being asked for.
     * @return The amount of energy associated to the type.
     */
    public int getEnergyByType(EnergyType type) {
        return energies.get(type);
    }

    /**
     * @return The amount of fighting energy of the counter.
     */
    public int getFightingEnergy() {
        return getEnergyByType(EnergyType.FIGHTING);
    }

    /**
     * @return The amount of fire energy of the counter.
     */
    public int getFireEnergy() {
        return getEnergyByType(EnergyType.FIRE);
    }

    /**
     * @return The amount of grass energy of the counter.
     */
    public int getGrassEnergy() {
        return getEnergyByType(EnergyType.GRASS);
    }

    /**
     * @return The amount of lightning energy of the counter.
     */
    public int getLightningEnergy() { return getEnergyByType(EnergyType.LIGHTNING); }

    /**
     * @return The amount of psychic energy of the counter.
     */
    public int getPsychicEnergy() {
        return getEnergyByType(EnergyType.PSYCHIC);
    }

    /**
     * @return The amount of water energy of the counter.
     */
    public int getWaterEnergy() {
        return getEnergyByType(EnergyType.WATER);
    }

    /**
     * Adds a value to the number of energy of a certain type to the counter.
     *
     * @param value The amount of energy cards that will be added.
     * @param type The type of the energies to add;
     */
    public void addToEnergyType(int value, EnergyType type) {
        energies.put(type, energies.get(type) + value);
    }

    /**
     * Adds a value to the number of fighting energy of the counter.
     *
     * @param value The amount of energy cards that will be added.
     */
    public void addFightingEnergy(int value) {
        addToEnergyType(value, EnergyType.FIGHTING);
    }

    /**
     * Adds a value to the number of fire energy of the counter.
     *
     * @param value The amount of energy cards that will be added.
     */
    public void addFireEnergy(int value) {
        addToEnergyType(value, EnergyType.FIRE);
    }

    /**
     * Adds a value to the number of grass energy of the counter.
     *
     * @param value The amount of energy cards that will be added.
     */
    public void addGrassEnergy(int value) {
        addToEnergyType(value, EnergyType.GRASS);
    }

    /**
     * Adds a value to the number of lightning energy of the counter.
     *
     * @param value The amount of energy cards that will be added.
     */
    public void addLightningEnergy(int value) {
        addToEnergyType(value, EnergyType.LIGHTNING);
    }

    /**
     * Adds a value to the number of psychic energy of the counter.
     *
     * @param value The amount of energy cards that will be added.
     */
    public void addPsychicEnergy(int value) {
        addToEnergyType(value, EnergyType.PSYCHIC);
    }

    /**
     * Adds a value to the number of water energy of the counter.
     *
     * @param value The amount of energy cards that will be added.
     */
    public void addWaterEnergy(int value) {
        addToEnergyType(value, EnergyType.WATER);
    }

    /**
     * Compares two energy counters to check if one is greater than the other.
     *
     * @param counter The other energy counter.
     *
     * @return true if this energy counter is greater than the parameter, false otherwise.
     */
    public boolean greaterOrEqual(EnergyCounter counter) {
        for (EnergyType energy : EnergyType.values()) {
            if (energies.get(energy) < counter.getEnergies().get(energy)) return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof EnergyCounter) {
            return (((EnergyCounter) o).getEnergies()).equals(this.getEnergies());
        }
        return false;
    }

}
