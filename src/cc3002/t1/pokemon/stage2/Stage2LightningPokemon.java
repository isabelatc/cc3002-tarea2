package cc3002.t1.pokemon.stage2;

import cc3002.t1.abilities.IAbility;
import cc3002.t1.pokemon.types.AbstractLightningPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import cc3002.t1.visitors.PlayableCardVisitor;

import java.util.ArrayList;

/**
 * Class for every stage 2 lightning pokémon.
 *
 * @author Isabela Tellechea Coluccio
 */
public class Stage2LightningPokemon extends AbstractLightningPokemon implements IStage2Pokemon {

    private int preEvolutionID;

    /**
     * The constructor of a stage 2 lightning pokémon. Initially, some of its parameters are empty, because they will be
     * added during the game. The Pokémon cannot have more than 4 abilities.
     *
     * @param name The name of the Pokémon.
     * @param id The identification number of the Pokémon (according to the Pokédex).
     * @param hp The initial hit points of the Pokémon.
     * @param abilityList The list of abilities the Pokémon can use. If it contains more than 4 abilities, only the first 4 will be stored.
     * @param preEvolutionID The ID of the Pokémon's pre evolution.
     */
    public Stage2LightningPokemon(String name, int id, int hp, ArrayList<IAbility> abilityList, int preEvolutionID) {
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
        if (o instanceof Stage2LightningPokemon) {
            return (((Stage2LightningPokemon) o).getCardName()).equals(this.getCardName()) &&
                    ((Stage2LightningPokemon) o).getID() == this.getID() &&
                    ((Stage2LightningPokemon) o).getHP() == this.getHP() &&
                    (((Stage2LightningPokemon) o).getAbilityList()).equals(this.getAbilityList()) &&
                    (((Stage2LightningPokemon) o).getEnergyList()).equals(this.getEnergyList());
        }
        return false;
    }
}
