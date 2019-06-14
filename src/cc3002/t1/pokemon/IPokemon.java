package cc3002.t1.pokemon;

import cc3002.t1.general.EnergyCounter;
import cc3002.t1.general.ICard;
import cc3002.t1.abilities.IAttack;
import cc3002.t1.energies.IEnergy;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableVisitor;

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
     * @return A dictionary structure that lists the Pokémon available energies.
     */
    EnergyCounter getEnergyList();

    /**
     * @return The number of fighting energies that have been assigned to the Pokémon.
     */
    int getFightingEnergyAvailable();

    /**
     * @return The number of fire energies that have been assigned to the Pokémon.
     */
    int getFireEnergyAvailable();

    /**
     * @return The number of grass energies that have been assigned to the Pokémon.
     */
    int getGrassEnergyAvailable();

    /**
     * @return The number of lightning energies that have been assigned to the Pokémon.
     */
    int getLightningEnergyAvailable();

    /**
     * @return The number of psychic energies that have been assigned to the Pokémon.
     */
    int getPsychicEnergyAvailable();

    /**
     * @return The number of water energies that have been assigned to the Pokémon.
     */
    int getWaterEnergyAvailable();

    /**
     * @return A list of the Pokémon attacks.
     */
    List<IAttack> getAttacks();

    /**
     * @return The attack the trainer has selected for the Pokémon to use.
     */
    IAttack getSelectedAttack();

    @Override
    void isPlayed(PlayCardVisitor v);

    @Override
    boolean equals(Object o);

    /**
     * This method allows to know if a Pokémon can be played. A basic Pokémon can be played always, but Pokémon
     * with other stages require a preevolution to be in the field. This method uses a visitor pattern.
     *
     * @param v The visitor that will implement the method.
     * @return True if the Pokémon can be played, false otherwise.
     */
    boolean canBePlayed(PlayableVisitor v);

    /**
     * Updates the HP of the Pokémon, after a fight.
     *
     * @param newHP The updated hit points of the Pokémon.
     */
    void updateHP(int newHP);

    /**
     * Includes one energy to the available ones of the Pokémon.
     */
    void addEnergyToPokemon(IEnergy energy);

    /**
     * Sets the selected attack parameter of the Pokémon to one of their attacks.
     *
     * @param index The position of the attack to be set (in the attack list of the Pokémon).
     */
    void setAttack(int index);

    /**
     * Checks if, with their current energy count, the Pokémon can attack with the selected attack.
     *
     * @return A boolean statement, if it can attack (true) or if it can't (false).
     */
    boolean canAttack();

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
     * The damage made by the attack received is effectively substracted to the Pokémon's HP.
     *
     * @param damage The effective damage received by the Pokémon.
     */
    void suffersEffectiveDamage(int damage);

    /**
     * This method calculates the effective damage (in this case, the base damage) the attack makes to the Pokémon,
     * so it can be substracted from the Pokémon's HP later.
     *
     * @param attack The attack the opponent uses to hurt the Pokémon.
     */
    void receivesNeutralAttack(IAttack attack);

    /**
     * This method calculates the effective damage (the base damage, duplicated) the attack makes to the Pokémon,
     * so it can be substracted from the Pokémon's HP later.
     *
     * @param attack The attack the opponent uses to hurt the Pokémon.
     */
    void receivesStrengthenedAttack(IAttack attack);

    /**
     * This method calculates the effective damage (the base damage, minus 30 points) the attack makes to the Pokémon,
     * so it can be substracted from the Pokémon's HP later.
     *
     * @param attack The attack the opponent uses to hurt the Pokémon.
     */
    void receivesWeakenedAttack(IAttack attack);

    /**
     * This pokémon transfers their energy to the other pokémon.
     *
     * @param other The pokémon that will receive the energy amounts from this pokémon.
     */
    void transferEnergy(IPokemon other);

    /**
     * When necessary, this Pokémon replaces the other Pokémon, taking its place in the field.
     *
     * @param other The pokémon that will be replaced; it will be added to the discard pile.
     */
    void replacePokemon(IPokemon other);

    /**
     * @return The list of cards the Pokémon has associated to it.
     */
    List<ICard> getAssociatedCards();

}
