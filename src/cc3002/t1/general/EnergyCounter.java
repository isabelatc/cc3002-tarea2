package cc3002.t1.general;

import java.util.EnumMap;
import java.util.Map;

/**
 * Class of the structure that will be used to keep track of a card's energies.
 *
 * @author Isabela Tellechea Coluccio
 */
public class EnergyCounter {
    private Map<EnergyType, Integer> energies = new EnumMap<>(EnergyType.class);

    /**
     * Creates a new instance for an energy counter.
     */
    public EnergyCounter() {
        for (EnergyType energy : EnergyType.values()) {
            energies.put(energy, 0);
        }
    }

    /**
     * Sets the value of the fighting energy count to the one provided.
     *
     * @param value The value the fighting energy count will have after the execution.
     */
    public void setFightingEnergy(int value) {
        energies.put(EnergyType.FIGHTING, value);
    }

    /**
     * Sets the value of the fire energy count to the one provided.
     *
     * @param value The value the fire energy count will have after the execution.
     */
    public void setFireEnergy(int value) {
        energies.put(EnergyType.FIRE, value);
    }

    /**
     * Sets the value of the grass energy count to the one provided.
     *
     * @param value The value the grass energy count will have after the execution.
     */
    public void setGrassEnergy(int value) {
        energies.put(EnergyType.GRASS, value);
    }

    /**
     * Sets the value of the lightning energy count to the one provided.
     *
     * @param value The value the lightning energy count will have after the execution.
     */
    public void setLightningEnergy(int value) {
        energies.put(EnergyType.LIGHTNING, value);
    }

    /**
     * Sets the value of the psychic energy count to the one provided.
     *
     * @param value The value the psychic energy count will have after the execution.
     */
    public void setPsychicEnergy(int value) {
        energies.put(EnergyType.PSYCHIC, value);
    }

    /**
     * Sets the value of the water energy count to the one provided.
     *
     * @param value The value the water energy count will have after the execution.
     */
    public void setWaterEnergy(int value) { energies.put(EnergyType.WATER, value); }

    /**
     * @return The dictionary that represents the amount of energy of the counter.
     */
    public Map<EnergyType, Integer> getEnergies() {
        return energies;
    }

    /**
     * @return The amount of fighting energy of the counter.
     */
    public int getFightingEnergy() {
        return energies.get(EnergyType.FIGHTING);
    }

    /**
     * @return The amount of fire energy of the counter.
     */
    public int getFireEnergy() {
        return energies.get(EnergyType.FIRE);
    }

    /**
     * @return The amount of grass energy of the counter.
     */
    public int getGrassEnergy() {
        return energies.get(EnergyType.GRASS);
    }

    /**
     * @return The amount of lightning energy of the counter.
     */
    public int getLightningEnergy() { return energies.get(EnergyType.LIGHTNING); }

    /**
     * @return The amount of psychic energy of the counter.
     */
    public int getPsychicEnergy() {
        return energies.get(EnergyType.PSYCHIC);
    }

    /**
     * @return The amount of water energy of the counter.
     */
    public int getWaterEnergy() {
        return energies.get(EnergyType.WATER);
    }

    /**
     * Adds 1 to the number of fighting energy of the counter.
     */
    public void addFightingEnergy() {
        energies.put(EnergyType.FIGHTING, energies.get(EnergyType.FIGHTING) + 1);
    }

    /**
     * Adds 1 to the number of fire energy of the counter.
     */
    public void addFireEnergy() {
        energies.put(EnergyType.FIRE, energies.get(EnergyType.FIRE) + 1);
    }

    /**
     * Adds 1 to the number of grass energy of the counter.
     */
    public void addGrassEnergy() {
        energies.put(EnergyType.GRASS, energies.get(EnergyType.GRASS) + 1);
    }

    /**
     * Adds 1 to the number of lightning energy of the counter.
     */
    public void addLightningEnergy() {
        energies.put(EnergyType.LIGHTNING, energies.get(EnergyType.LIGHTNING) + 1);
    }

    /**
     * Adds 1 to the number of psychic energy of the counter.
     */
    public void addPsychicEnergy() {
        energies.put(EnergyType.PSYCHIC, energies.get(EnergyType.PSYCHIC) + 1);
    }

    /**
     * Adds 1 to the number of water energy of the counter.
     */
    public void addWaterEnergy() {
        energies.put(EnergyType.WATER, energies.get(EnergyType.WATER) + 1);
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
