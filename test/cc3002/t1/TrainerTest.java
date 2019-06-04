package cc3002.t1;

import cc3002.t1.energies.FireEnergy;
import cc3002.t1.energies.GrassEnergy;
import cc3002.t1.energies.PsychicEnergy;
import cc3002.t1.pokemon.FirePokemon;
import cc3002.t1.pokemon.GrassPokemon;
import cc3002.t1.pokemon.PsychicPokemon;
import cc3002.t1.pokemon.WaterPokemon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TrainerTest {

    private IEnergy aGrassEnergy, aFireEnergy, aPsychicEnergy;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon charmander, squirtle, tangela, abra, anotherSquirtle, anotherTangela, anotherAbra;
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
        abra = new PsychicPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(attack20, attack30, attack40, attack50)));
        anotherSquirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack30)));
        anotherTangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack20, attack40, attack50)));
        anotherAbra = new PsychicPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack30, attack40, attack50)));

        trainer = new Trainer(new ArrayList<>(Arrays.asList(anotherSquirtle, aGrassEnergy, charmander, abra, tangela, aFireEnergy, squirtle, aPsychicEnergy, anotherAbra, anotherTangela)));
    }

    @Test
    public void getHandTest() {
        assertEquals(10, trainer.getHand().size());
    }

    @Test
    public void getBenchTest() {
        assertEquals(0, trainer.getBench().size());
    }

    @Test
    public void playPokemonTest() {
        trainer.playPokemon(charmander);
        trainer.playPokemon(squirtle);
        trainer.playPokemon(abra);
        trainer.playPokemon(anotherSquirtle);
        trainer.playPokemon(tangela);
        assertEquals(5, trainer.getBench().size());
        assertEquals(5, trainer.getHand().size());

    }

    @Test
    public void playEnergyTest() {
        trainer.playPokemon(charmander);
        trainer.setActivePokemon();
        trainer.playEnergy(aGrassEnergy);
        IPokemon activePokemon = trainer.getActivePokemon();
        assertEquals(new ArrayList<>(Arrays.asList(0, 0, 1, 0, 0, 0)), trainer.getPokemonEnergies(activePokemon));
        assertNotEquals(new ArrayList<>(Arrays.asList(0, 1, 0, 0, 0, 0)), trainer.getPokemonEnergies(activePokemon));
        assertEquals(8, trainer.getHand().size());
    }

    @Test
    public void getActivePokemonTest() {
        assertNull(trainer.getActivePokemon());
        trainer.playPokemon(charmander);
        trainer.setActivePokemon();
        assertEquals(charmander, trainer.getActivePokemon());
    }

    @Test
    public void changeActivePokemonTest() {
        trainer.playPokemon(charmander);
        trainer.playPokemon(abra);
        trainer.setActivePokemon();
        trainer.changeActivePokemon(abra);
        assertEquals(abra, trainer.getActivePokemon());
        assertTrue(trainer.getBench().contains(charmander));
        assertFalse(trainer.getBench().contains(abra));
        assertEquals(1, trainer.getBench().size());
    }

    @Test
    public void setActivePokemonTest() {
        trainer.playPokemon(squirtle);
        trainer.setActivePokemon();
        assertEquals(squirtle, trainer.getActivePokemon());
        assertEquals(0, trainer.getBench().size());
    }

    @Test
    public void getActivePokemonAttacksTest() {
        trainer.playPokemon(squirtle);
        trainer.setActivePokemon();
        assertEquals(3, trainer.getActivePokemonAttacks().size());
    }

    @Test
    public void selectAttackTest() {
        trainer.playPokemon(squirtle);
        trainer.setActivePokemon();
        trainer.selectAttack(1);
        assertEquals(attack20, squirtle.getSelectedAttack());
    }

}