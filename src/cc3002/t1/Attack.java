package cc3002.t1;

/**
 * Base class for an attack. The Pokémon cards use these objects. In this class, there can be found mainly
 * accessibility methods, so that their properties can be shared with other classes.
 *
 * @author Isabela Tellechea Coluccio
 */
public class Attack implements IAttack {

    private String name, description;
    private int baseDamage;
    private EnergyCounter energyCost;

    /**
     * Creates a new Attack.
     *
     * @param name Attack's name.
     * @param baseDamage The amount of base damage of the attack.
     * @param description A description of the attack.
     * @param fightingCost The amount of fighting energy cards a Pokémon needs to use this attack.
     * @param fireCost The amount of fire energy cards a Pokémon needs to use this attack.
     * @param grassCost The amount of grass energy cards a Pokémon needs to use this attack.
     * @param lightningCost The amount of lightning energy cards a Pokémon needs to use this attack.
     * @param psychicCost The amount of psychic energy cards a Pokémon needs to use this attack.
     * @param waterCost The amount of water energy cards a Pokémon needs to use this attack.
     */
    public Attack(String name, int baseDamage, String description,
                  int fightingCost, int fireCost, int grassCost, int lightningCost, int psychicCost, int waterCost) {
        this.name = name;
        this.baseDamage = baseDamage;
        this.description = description;
        this.energyCost = new EnergyCounter();
        energyCost.setFightingEnergy(fightingCost);
        energyCost.setFireEnergy(fireCost);
        energyCost.setGrassEnergy(grassCost);
        energyCost.setLightningEnergy(lightningCost);
        energyCost.setPsychicEnergy(psychicCost);
        energyCost.setWaterEnergy(waterCost);
    }

    @Override
    public String getAttackName() {
        return this.name;
    }

    @Override
    public int getBaseDamage() {
        return this.baseDamage;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public EnergyCounter getEnergyCosts() {
        return energyCost;
    }

    @Override
    public int getFightingCost() {
        return this.energyCost.getFightingEnergy();
    }

    @Override
    public int getFireCost() {
        return this.energyCost.getFireEnergy();
    }

    @Override
    public int getGrassCost() {
        return this.energyCost.getGrassEnergy();
    }

    @Override
    public int getLightningCost() {
        return this.energyCost.getLightningEnergy();
    }

    @Override
    public int getPsychicCost() {
        return this.energyCost.getPsychicEnergy();
    }

    @Override
    public int getWaterCost() {
        return this.energyCost.getWaterEnergy();
    }

}
