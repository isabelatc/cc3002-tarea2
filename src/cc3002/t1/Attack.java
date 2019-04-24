package cc3002.t1;

import java.util.ArrayList;

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
}
