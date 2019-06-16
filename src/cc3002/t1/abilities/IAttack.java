package cc3002.t1.abilities;

/**
 * The interface for every Attack. Their properties should be used by a Pokémon in a fight with another one.
 *
 * @author Isabela Tellechea Coluccio
 */
public interface IAttack extends IAbility {

    /**
     * @return Attack's base damage.
     */
    int getBaseDamage();

}
