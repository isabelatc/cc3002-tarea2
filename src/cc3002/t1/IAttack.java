package cc3002.t1;

import java.util.List;

/**
 * The interface for every attack. They are supposed to have many internal properties, and should be used
 * by a Pokémon in a fight with another one.
 *
 * @author Isabela Tellechea Coluccio
 */
public interface IAttack {

    /**
     * @return Attack's name.
     */
    String getAttackName();

    /**
     * @return Attack's base damage.
     */
    int getBaseDamage();

    /**
     * @return Attack's description.
     */
    String getDescription();

    /**
     * @return A list of the attack's energy costs, in the order: fighting, fire, grass, lightning, psychic and water.
     */

    EnergyCounter getEnergyCosts();

    /**
     * @return Attack's fighting energy cost.
     */
    int getFightingCost();

    /**
     * @return Attack's fire energy cost.
     */
    int getFireCost();

    /**
     * @return Attack's grass energy cost.
     */
    int getGrassCost();

    /**
     * @return Attack's lightning energy cost.
     */
    int getLightningCost();

    /**
     * @return Attack's psychic energy cost.
     */
    int getPsychicCost();

    /**
     * @return Attack's water energy cost.
     */
    int getWaterCost();

}
