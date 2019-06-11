package cc3002.t1.energies;

import cc3002.t1.*;
import cc3002.t1.pokemon.FirePokemon;
import cc3002.t1.pokemon.GrassPokemon;
import cc3002.t1.pokemon.WaterPokemon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class FireEnergyTest {

    private IEnergy aFireEnergy;
    private IAttack attack10, attack20, attack30;
    private IPokemon charmander, squirtle, tangela;
    private ITrainer trainer;
    private List<ICard> trainerDeck, auxTrainerDeck;

    @Before
    public void setUp(){
        aFireEnergy = new FireEnergy("I'm a Fire Energy!");

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

        auxTrainerDeck = new ArrayList<>(Arrays.asList(charmander, tangela, squirtle, aFireEnergy));
        trainerDeck = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            for (ICard card : auxTrainerDeck) {
                trainerDeck.add(card);
            }
        }

        Collections.shuffle(trainerDeck);

        trainer = new Trainer(trainerDeck);
        trainer.addToHand(charmander);
        trainer.addToHand(tangela);
        trainer.addToHand(squirtle);
        trainer.addToHand(aFireEnergy);

    }

    @Test
    public void getCardNameTest() {
        assertEquals("I'm a Fire Energy!", aFireEnergy.getCardName());
    }

    @Test
    public void getTrainerTest() {
        assertNull(aFireEnergy.getTrainer());
    }

    @Test
    public void setTrainer() {
        aFireEnergy.setTrainer(trainer);
        assertEquals(trainer, aFireEnergy.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        aFireEnergy.setTrainer(trainer);
        aFireEnergy.isPlayed();
        assertEquals(4, trainer.getHand().size());
    }

    @Test
    public void isAddedTest() {
        aFireEnergy.setTrainer(trainer);
        aFireEnergy.isAdded(charmander);
        assertEquals(3, trainer.getHand().size());
        assertEquals(1, charmander.getFireEnergyAvailable());
    }

    @Test
    public void equalsTest() {
        IEnergy anotherFire = new FireEnergy("I'm a Fire Energy!");
        assertEquals(anotherFire, aFireEnergy);
    }
}