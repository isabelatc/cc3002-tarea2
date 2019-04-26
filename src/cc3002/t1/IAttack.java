package cc3002.t1;

import java.util.List;

public interface IAttack {

    String getName();

    int getBaseDamage();

    String getDescription();

    List<Integer> getEnergyCosts();

    int getFightingCost();

    int getFireCost();

    int getGrassCost();

    int getLightningCost();

    int getPsychicCost();

    int getWaterCost();

}
