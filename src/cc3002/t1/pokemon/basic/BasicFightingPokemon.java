package cc3002.t1.pokemon.basic;

import cc3002.t1.abilities.IAbility;
import cc3002.t1.pokemon.types.AbstractFightingPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableCardVisitor;

import java.util.ArrayList;

/**
 * Class for every basic fighting pokémon.
 *
 * @author Isabela Tellechea Coluccio
 */
public class BasicFightingPokemon extends AbstractFightingPokemon implements IBasicPokemon {

    /**
     * The constructor of a basic fighting pokémon. Initially, some of its parameters are empty, because they will be
     * added during the game. The Pokémon cannot have more than 4 abilities.
     *
     * @param name The name of the Pokémon.
     * @param id The identification number of the Pokémon (according to the Pokédex).
     * @param hp The initial hit points of the Pokémon.
     * @param abilityList The list of abilities the Pokémon can use. If it contains more than 4 abilities, only the first 4 will be stored.
     */
    public BasicFightingPokemon(String name, int id, int hp, ArrayList<IAbility> abilityList) {
        super(name, id, hp, abilityList);
    }

    @Override
    public void isPlayed(PlayCardVisitor v) {
        v.visitBasicPokemon(this);
    }

    @Override
    public boolean canBePlayed(PlayableCardVisitor v) {
        v.visitBasicPokemon(this);
        return v.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof BasicFightingPokemon) {
            return (((BasicFightingPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((BasicFightingPokemon) o).getID() == this.getID() &&
                    ((BasicFightingPokemon) o).getHP() == this.getHP() &&
                    (((BasicFightingPokemon) o).getAbilityList()).equals(this.getAbilityList()) &&
                    (((BasicFightingPokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }

}
