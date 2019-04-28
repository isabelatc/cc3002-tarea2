package cc3002.t1;

import java.util.List;

public interface IPokemon extends ICard {

    /**
     * @return The identification number of the Pokémon Card.
     */
    int getID();

    /**
     * @return Hit points of the Pokémon Card.
     */
    int getHP();

    /**
     * @return A list that contains the Pokémon available energies in the order: fighting, fire, grass, lightning, psychic and water.
     */
    List<Integer> getEnergies();

    /**
     * @return The number of fighting energies that have been assigned to the Pokémon.
     */
    int getFightingEnergies();

    /**
     * @return The number of fire energies that have been assigned to the Pokémon.
     */
    int getFireEnergies();

    /**
     * @return The number of grass energies that have been assigned to the Pokémon.
     */
    int getGrassEnergies();

    /**
     * @return The number of lightning energies that have been assigned to the Pokémon.
     */
    int getLightningEnergies();

    /**
     * @return The number of psychic energies that have been assigned to the Pokémon.
     */
    int getPsychicEnergies();

    /**
     * @return The number of water energies that have been assigned to the Pokémon.
     */
    int getWaterEnergies();

    @Override
    String getCardName();

    /**
     * @return A list of the Pokémon attacks.
     */
    List<IAttack> getAttacks();

    /**
     * @return The attack the trainer has selected for the Pokémon to use.
     */
    IAttack getSelectedAttack();

    @Override
    boolean equals(Object o);

    /**
     * Includes one fighting energy to the available ones of the Pokémon.
     */
    void addFightingEnergy();

    /**
     * Includes one fire energy to the available ones of the Pokémon.
     */
    void addFireEnergy();

    /**
     * Includes one grass energy to the available ones of the Pokémon.
     */
    void addGrassEnergy();

    /**
     * Includes one lightning energy to the available ones of the Pokémon.
     */
    void addLightningEnergy();

    /**
     * Includes one psychic energy to the available ones of the Pokémon.
     */
    void addPsychicEnergy();

    /**
     * Includes one water energy to the available ones of the Pokémon.
     */
    void addWaterEnergy();

    /**
     * Sets the selected attack parameter of the Pokémon to one of its attacks.
     *
     * @param index The position of the attack to be set (in the attack list of the Pokémon).
     */
    void selectAttack(int index);

    /**
     * The current Pokémon attacks another with its selected attack.
     *
     * @param other The Pokémon that is attacked.
     */
    void attack(IPokemon other);

    /**
     * This method decides how to reduce the HP of the Pokémon, considering it was attacked by a fighting opponent.
     *
     * @param attack The attack the opponent uses to hurt the Pokémon.
     */
    void attackedByFightingPokemon(IAttack attack);

    /**
     * This method decides how to reduce the HP of the Pokémon, considering it was attacked by a fire opponent.
     *
     * @param attack The attack the opponent uses to hurt the Pokémon.
     */
    void attackedByFirePokemon(IAttack attack);

    /**
     * This method decides how to reduce the HP of the Pokémon, considering it was attacked by a grass opponent.
     *
     * @param attack The attack the opponent uses to hurt the Pokémon.
     */
    void attackedByGrassPokemon(IAttack attack);

    /**
     * This method decides how to reduce the HP of the Pokémon, considering it was attacked by a lightning opponent.
     *
     * @param attack The attack the opponent uses to hurt the Pokémon.
     */
    void attackedByLightningPokemon(IAttack attack);

    /**
     * This method decides how to reduce the HP of the Pokémon, considering it was attacked by a psychic opponent.
     *
     * @param attack The attack the opponent uses to hurt the Pokémon.
     */
    void attackedByPsychicPokemon(IAttack attack);

    /**
     * This method decides how to reduce the HP of the Pokémon, considering it was attacked by a water opponent.
     *
     * @param attack The attack the opponent uses to hurt the Pokémon.
     */
    void attackedByWaterPokemon(IAttack attack);

    /**
     * This method effectively reduces the HP of the Pokémon, substracting the base damage of the attack.
     *
     * @param attack The attack the opponent uses to hurt the Pokémon.
     */
    void receivesNeutralAttack(IAttack attack);

    /**
     * This method effectively reduces the HP of the Pokémon, substracting the base damage of the attack, duplicated.
     *
     * @param attack The attack the opponent uses to hurt the Pokémon.
     */
    void receivesStrengthenedAttack(IAttack attack);

    /**
     * This method effectively reduces the HP of the Pokémon, substracting the base damage of the attack, minus 30 points.
     *
     * @param attack The attack the opponent uses to hurt the Pokémon.
     */
    void receivesWeakenedAttack(IAttack attack);

}
