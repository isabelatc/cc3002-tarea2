package cc3002.t1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Attack implements IAttack {

    private String name, description;
    private int baseDamage, fightingCost, fireCost, grassCost, lightningCost, psychicCost, waterCost;

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
    public String getName() {
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
