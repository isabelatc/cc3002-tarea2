package cc3002.t1;

import java.util.EnumMap;
import java.util.Map;

public class EnergyCounter {
    private Map<EnergyType, Integer> energies = new EnumMap<>(EnergyType.class);

    public EnergyCounter() {
        for (EnergyType energy : EnergyType.values()) {
            energies.put(energy, 0);
        }
    }

    public void setFightingEnergy(int value) {
        energies.put(EnergyType.FIGHTING, value);
    }

    public void setFireEnergy(int value) {
        energies.put(EnergyType.FIRE, value);
    }

    public void setGrassEnergy(int value) {
        energies.put(EnergyType.GRASS, value);
    }

    public void setLightningEnergy(int value) {
        energies.put(EnergyType.LIGHTNING, value);
    }

    public void setPsychicEnergy(int value) {
        energies.put(EnergyType.PSYCHIC, value);
    }

    public void setWaterEnergy(int value) { energies.put(EnergyType.WATER, value); }

    public Map<EnergyType, Integer> getEnergies() {
        return energies;
    }

    public int getFightingEnergy() {
        return energies.get(EnergyType.FIGHTING);
    }

    public int getFireEnergy() {
        return energies.get(EnergyType.FIRE);
    }

    public int getGrassEnergy() {
        return energies.get(EnergyType.GRASS);
    }

    public int getLightningEnergy() { return energies.get(EnergyType.LIGHTNING); }

    public int getPsychicEnergy() {
        return energies.get(EnergyType.PSYCHIC);
    }

    public int getWaterEnergy() {
        return energies.get(EnergyType.WATER);
    }

    public void addFightingEnergy() {
        energies.put(EnergyType.FIGHTING, energies.get(EnergyType.FIGHTING) + 1);
    }

    public void addFireEnergy() {
        energies.put(EnergyType.FIRE, energies.get(EnergyType.FIRE) + 1);
    }

    public void addGrassEnergy() {
        energies.put(EnergyType.GRASS, energies.get(EnergyType.GRASS) + 1);
    }

    public void addLightningEnergy() {
        energies.put(EnergyType.LIGHTNING, energies.get(EnergyType.LIGHTNING) + 1);
    }

    public void addPsychicEnergy() {
        energies.put(EnergyType.PSYCHIC, energies.get(EnergyType.PSYCHIC) + 1);
    }

    public void addWaterEnergy() {
        energies.put(EnergyType.WATER, energies.get(EnergyType.WATER) + 1);
    }

    public boolean greaterOrEqual(EnergyCounter counter) {
        for (EnergyType energy : EnergyType.values()) {
            if (energies.get(energy) < counter.getEnergies().get(energy)) return false;
        }
        return true;
    }

}
