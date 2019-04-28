package cc3002.t1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Base class for an attack. The Pokémon cards use these objects. In this class, there can be found mainly
 * accessibility methods, so that their properties can be shared with other classes.
 *
 * @author Isabela Tellechea Coluccio
 */
public class Attack implements IAttack {

    private String name, description;
    private int baseDamage, fightingCost, fireCost, grassCost, lightningCost, psychicCost, waterCost;

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
        this.fightingCost = fightingCost;
        this.fireCost = fireCost;
        this.grassCost = grassCost;
        this.lightningCost = lightningCost;
        this.psychicCost = psychicCost;
        this.waterCost = waterCost;
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
    public List<Integer> getEnergyCosts() {
        return new ArrayList<>(Arrays.asList(fightingCost, fireCost, grassCost, lightningCost, psychicCost, waterCost));
    }

    @Override
    public int getFightingCost() {
        return this.fightingCost;
    }

    @Override
    public int getFireCost() {
        return this.fireCost;
    }

    @Override
    public int getGrassCost() {
        return this.fireCost;
    }

    @Override
    public int getLightningCost() {
        return this.lightningCost;
    }

    @Override
    public int getPsychicCost() {
        return this.psychicCost;
    }

    @Override
    public int getWaterCost() {
        return this.waterCost;
    }

}
