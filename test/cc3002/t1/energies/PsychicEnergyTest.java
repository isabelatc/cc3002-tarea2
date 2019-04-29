package cc3002.t1.energies;

import cc3002.t1.*;
import cc3002.t1.pokemon.FirePokemon;
import cc3002.t1.pokemon.GrassPokemon;
import cc3002.t1.pokemon.WaterPokemon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PsychicEnergyTest {

    private IEnergy aPsychicEnergy;
    private IAttack attack10, attack20, attack30;
    private IPokemon charmander, squirtle, tangela;
    private ITrainer trainer;

    @Before
    public void setUp(){
        aPsychicEnergy = new PsychicEnergy("I'm a Psychic Energy!");

        attack10 = new Attack("Attack 10", 10, "This attack has a base damage of 10",
                0, 2, 0, 0, 0, 1);
        attack20 = new Attack("Attack 20", 20, "This attack has a base damage of 20",
                0, 0, 2, 0, 1, 0);
        attack30 = new Attack("Attack 30", 30, "This attack has a base damage of 30",
                0, 1, 2, 0, 0, 0);

        charmander = new FirePokemon("Charmander", 4, 100,
                new ArrayList<>(Arrays.asList(attack30, attack10)));
        squirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack30)));
        tangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack10)));

        trainer = new Trainer(new ArrayList<>(Arrays.asList(charmander, tangela, squirtle, aPsychicEnergy)));
    }

    @Test
    public void isPlayedTest() {
        aPsychicEnergy.isPlayed(trainer);
        assertEquals(new ArrayList<>(Arrays.asList(charmander, tangela, squirtle, aPsychicEnergy)), trainer.getHand());
    }

    @Test
    public void isAddedTest() {
        aPsychicEnergy.isAdded(charmander);
        assertEquals(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 1, 0)), charmander.getEnergies());
        assertNotEquals(new ArrayList<>(Arrays.asList(0, 1, 0, 0, 0, 0)), charmander.getEnergies());
        assertEquals(1, charmander.getPsychicEnergies());
    }

    @Test
    public void getCardNameTest() {
        assertEquals("I'm a Psychic Energy!", aPsychicEnergy.getCardName());
    }

    @Test
    public void equalsTest() {
        IEnergy anotherPsychic = new PsychicEnergy("I'm a Psychic Energy!");
        assertEquals(anotherPsychic, aPsychicEnergy);
    }
}