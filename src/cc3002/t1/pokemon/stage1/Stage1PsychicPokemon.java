package cc3002.t1.pokemon.stage1;

import cc3002.t1.abilities.IAbility;
import cc3002.t1.pokemon.types.AbstractPsychicPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableCardVisitor;

import java.util.ArrayList;

/**
 * Class for every stage 1 psychic pokémon.
 *
 * @author Isabela Tellechea Coluccio
 */
public class Stage1PsychicPokemon extends AbstractPsychicPokemon implements IStage1Pokemon {

    private int preEvolutionID;

    /**
     * The constructor of a stage 1 psychic pokémon. Initially, some of its parameters are empty, because they will be
     * added during the game. The Pokémon cannot have more than 4 abilities.
     *
     * @param name The name of the Pokémon.
     * @param id The identification number of the Pokémon (according to the Pokédex).
     * @param hp The initial hit points of the Pokémon.
     * @param abilityList The list of abilities the Pokémon can use. If it contains more than 4 abilities, only the first 4 will be stored.
     * @param preEvolutionID The ID of the Pokémon's pre evolution.
     */
    public Stage1PsychicPokemon(String name, int id, int hp, ArrayList<IAbility> abilityList, int preEvolutionID) {
        super(name, id, hp, abilityList);
        this.preEvolutionID = preEvolutionID;
    }

    @Override
    public int getPreEvolutionID() {
        return this.preEvolutionID;
    }

    @Override
    public void isPlayed(PlayCardVisitor v) {
        v.visitStage1Pokemon(this);
    }

    @Override
    public boolean canBePlayed(PlayableCardVisitor v) {
        v.visitStage1Pokemon(this);
        return v.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Stage1PsychicPokemon) {
            return (((Stage1PsychicPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((Stage1PsychicPokemon) o).getID() == this.getID() &&
                    ((Stage1PsychicPokemon) o).getHP() == this.getHP() &&
                    (((Stage1PsychicPokemon) o).getAbilityList()).equals(this.getAbilityList()) &&
                    (((Stage1PsychicPokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }
}
