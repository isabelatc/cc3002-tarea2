package cc3002.t1.visitors;

import cc3002.t1.abilities.ElectricShock;
import cc3002.t1.abilities.EnergyBurn;
import cc3002.t1.general.EnergyCounter;
import cc3002.t1.general.EnergyType;
import cc3002.t1.pokemon.IPokemon;

import java.util.Random;

/**
 * Class for the visitor that implements the usage of abilities by pokémon.
 *
 * @author Isabela Tellechea Coluccio
 */
public class UseAbilityVisitor extends AbstractAbilitiesVisitor {

    private EnergyType pokemonType;

    @Override
    public void electricShockEffect(ElectricShock electricShock) {
        Random rand = new Random();
        boolean heads = electricShock.getPokemon().getTrainer().flipACoin(rand);
        if (!heads) electricShock.getPokemon().suffersEffectiveDamage(electricShock.getSelfDamage());
    }

    @Override
    public void electricShockByFightingPokemon(ElectricShock electricShock) {
        electricShockEffect(electricShock);
        electricShock.getPokemon().getOpponent().attackedByFightingPokemon(electricShock);
    }

    @Override
    public void electricShockByFirePokemon(ElectricShock electricShock) {
        electricShockEffect(electricShock);
        electricShock.getPokemon().getOpponent().attackedByFirePokemon(electricShock);
    }

    @Override
    public void electricShockByGrassPokemon(ElectricShock electricShock) {
        electricShockEffect(electricShock);
        electricShock.getPokemon().getOpponent().attackedByGrassPokemon(electricShock);
    }

    @Override
    public void electricShockByLightningPokemon(ElectricShock electricShock) {
        electricShockEffect(electricShock);
        electricShock.getPokemon().getOpponent().attackedByLightningPokemon(electricShock);
    }

    @Override
    public void electricShockByPsychicPokemon(ElectricShock electricShock) {
        electricShockEffect(electricShock);
        electricShock.getPokemon().getOpponent().attackedByPsychicPokemon(electricShock);
    }

    @Override
    public void electricShockByWaterPokemon(ElectricShock electricShock) {
        electricShockEffect(electricShock);
        electricShock.getPokemon().getOpponent().attackedByWaterPokemon(electricShock);
    }

    @Override
    public void energyBurnEffect(EnergyBurn energyBurn) {
        IPokemon pokemon = energyBurn.getPokemon();
        EnergyCounter energies = pokemon.getEnergyList();
        for (EnergyType energy : EnergyType.values()) {
            if (energy!=pokemonType) {
                energies.addToEnergyType(energies.getEnergyByType(energy), pokemonType);
                energies.setEnergyByType(0, pokemonType);
            }
        }
    }

    @Override
    public void energyBurnByFightingPokemon(EnergyBurn energyBurn) {
        pokemonType = EnergyType.FIGHTING;
        energyBurnEffect(energyBurn);
    }

    @Override
    public void energyBurnByFirePokemon(EnergyBurn energyBurn) {
        pokemonType = EnergyType.FIRE;
        energyBurnEffect(energyBurn);
    }

    @Override
    public void energyBurnByGrassPokemon(EnergyBurn energyBurn) {
        pokemonType = EnergyType.GRASS;
        energyBurnEffect(energyBurn);
    }

    @Override
    public void energyBurnByLightningPokemon(EnergyBurn energyBurn) {
        pokemonType = EnergyType.LIGHTNING;
        energyBurnEffect(energyBurn);
    }

    @Override
    public void energyBurnByPsychicPokemon(EnergyBurn energyBurn) {
        pokemonType = EnergyType.PSYCHIC;
        energyBurnEffect(energyBurn);
    }

    @Override
    public void energyBurnByWaterPokemon(EnergyBurn energyBurn) {
        pokemonType = EnergyType.WATER;
        energyBurnEffect(energyBurn);
    }

}
