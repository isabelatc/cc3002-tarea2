package cc3002.t1;

import cc3002.t1.energies.FireEnergy;
import cc3002.t1.energies.GrassEnergy;
import cc3002.t1.energies.PsychicEnergy;
import cc3002.t1.pokemon.FirePokemon;
import cc3002.t1.pokemon.GrassPokemon;
import cc3002.t1.pokemon.WaterPokemon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TrainerTest {

    private ICard aGrassEnergy, aFireEnergy, aPsychicEnergy;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon charmander, squirtle, tangela, abra, anotherCharmander, anotherSquirtle, anotherTangela, anotherAbra;
    private ITrainer trainer;

    @Before
    public void setUp() {

        aGrassEnergy = new GrassEnergy();
        aFireEnergy = new FireEnergy();
        aPsychicEnergy = new PsychicEnergy();

        attack10 = new Attack("Attack 10", 10, "This attack has a base damage of 10",
                0, 2, 0, 0, 0, 1);
        attack20 = new Attack("Attack 20", 20, "This attack has a base damage of 20",
                0, 0, 2, 0, 1, 0);
        attack30 = new Attack("Attack 30", 30, "This attack has a base damage of 30",
                0, 1, 2, 0, 0, 0);
        attack40 = new Attack("Attack 40", 40, "This attack has a base damage of 40",
                0, 1, 2, 0, 1, 0);
        attack50 = new Attack("Attack 50", 50, "This attack has a base damage of 50",
                0, 2, 0, 0, 3, 0);

        charmander = new FirePokemon("Charmander", 4, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack10, attack30, attack40, attack50)));
        abra = new GrassPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(attack20, attack30, attack40, attack50)));
        anotherCharmander = new FirePokemon("Charmander", 4, 100,
                new ArrayList<>(Arrays.asList(attack20)));
        anotherSquirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack30)));
        anotherTangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack20, attack40, attack50)));
        anotherAbra = new GrassPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack30, attack40, attack50)));

        trainer = new Trainer(charmander, new ArrayList<>Arrays.asList(squirtle, abra, anotherSquirtle, anotherCharmander),
                new ArrayList<ICard>(Arrays.asList(aGrassEnergy, tangela, aFireEnergy, aPsychicEnergy, anotherAbra, anotherTangela)));
    }

    @Test
    public void getHandTest() {
        assertEquals(new ArrayList<ICard>(Arrays.asList(aGrassEnergy, tangela, aFireEnergy, aPsychicEnergy, anotherAbra, anotherTangela), trainer.getHand()));
    }

    @Test
    public void getBenchTest() {
        assertEquals(new ArrayList<>(Arrays.asList(squirtle, abra, anotherSquirtle, anotherCharmander), trainer.getBench()));
    }

    @Test
    public void playPokemonTest() {
        trainer.playPokemon(tangela);
        assertEquals(new ArrayList<>(Arrays.asList(squirtle, abra, anotherSquirtle, anotherCharmander, tangela), trainer.getBench()));
        assertEquals(new ArrayList<ICard>(Arrays.asList(aGrassEnergy, aFireEnergy, aPsychicEnergy, anotherAbra, anotherTangela), trainer.getHand()));
        trainer.playPokemon(squirtle);
        assertEquals(new ArrayList<>(Arrays.asList(squirtle, abra, anotherSquirtle, anotherCharmander, tangela), trainer.getBench()));
        assertEquals(new ArrayList<ICard>(Arrays.asList(aGrassEnergy, aFireEnergy, aPsychicEnergy, anotherAbra, anotherTangela), trainer.getHand()));
        trainer.playPokemon(anotherAbra);
        assertEquals(new ArrayList<>(Arrays.asList(squirtle, abra, anotherSquirtle, anotherCharmander, tangela), trainer.getBench()));
        assertEquals(new ArrayList<ICard>(Arrays.asList(aGrassEnergy, aFireEnergy, aPsychicEnergy, anotherAbra, anotherTangela), trainer.getHand()));
    }

    @Test
    public void playEnergyTest() {
        trainer.playEnergy(aGrassEnergy);
        IPokemon activePokemon = trainer.getActivePokemon();
        assertEquals(new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0)), trainer.getPokemonEnergies(activePokemon));
    }

    @Test
    public void getActivePokemonTest() {
        assertEquals(charmander, trainer.getActivePokemon());
    }

    @Test
    public void changeActivePokemonTest() {
        trainer.changeActivePokemon(abra);
        assertEquals(abra, trainer.getActivePokemon());
        assertEquals(new ArrayList<>(Arrays.asList(squirtle, anotherSquirtle, anotherCharmander, tangela, charmander), trainer.getBench()));
    }

    @Test
    public void setActivePokemonTest() {
        trainer.setActivePokemon();
        assertEquals(squirtle, trainer.getActivePokemon());
        assertEquals(new ArrayList<>(Arrays.asList(anotherSquirtle, anotherCharmander, tangela, charmander), trainer.getBench()));
    }

    @Test
    public void getActivePokemonAttacksTest() {
        assertEquals(new ArrayList<IAttack>(Arrays.asList(attack10, attack20, attack50), trainer.getActivePokemonAttacks()));
    }

    @Test
    public void selectAttackTest() {
        assertEquals(attack20, trainer.selectAttack(1));
    }

}