package cc3002.t1.pokemon.stage2;

import cc3002.t1.abilities.IAbility;
import cc3002.t1.pokemon.types.AbstractWaterPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableCardVisitor;

import java.util.ArrayList;

/**
 * Class for every stage 2 water pokémon.
 *
 * @author Isabela Tellechea Coluccio
 */
public class Stage2WaterPokemon extends AbstractWaterPokemon implements IStage2Pokemon {

    private int preEvolutionID;

    /**
     * The constructor of a stage 2 water pokémon. Initially, some of its parameters are empty, because they will be
     * added during the game. The Pokémon cannot have more than 4 abilities.
     *
     * @param name The name of the Pokémon.
     * @param id The identification number of the Pokémon (according to the Pokédex).
     * @param hp The initial hit points of the Pokémon.
     * @param abilityList The list of abilities the Pokémon can use. If it contains more than 4 abilities, only the first 4 will be stored.
     * @param preEvolutionID The ID of the Pokémon's pre evolution.
     */
    public Stage2WaterPokemon(String name, int id, int hp, ArrayList<IAbility> abilityList, int preEvolutionID) {
        super(name, id, hp, abilityList);
        this.preEvolutionID = preEvolutionID;
    }

    @Override
    public int getPreEvolutionID() {
        return this.preEvolutionID;
    }

    @Override
    public void isPlayed(PlayCardVisitor v) {
        v.visitStage2Pokemon(this);
    }

    @Override
    public boolean canBePlayed(PlayableCardVisitor v) {
        v.visitStage2Pokemon(this);
        return v.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Stage2WaterPokemon) {
            return (((Stage2WaterPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((Stage2WaterPokemon) o).getID() == this.getID() &&
                    ((Stage2WaterPokemon) o).getHP() == this.getHP() &&
                    (((Stage2WaterPokemon) o).getAbilityList()).equals(this.getAbilityList()) &&
                    (((Stage2WaterPokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }
}
