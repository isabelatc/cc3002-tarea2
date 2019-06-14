package cc3002.t1.energies;

import cc3002.t1.abilities.Attack;
import cc3002.t1.abilities.IAttack;
import cc3002.t1.general.ICard;
import cc3002.t1.general.ITrainer;
import cc3002.t1.general.Trainer;
import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.pokemon.firePokemon.FirePokemon;
import cc3002.t1.pokemon.grassPokemon.GrassPokemon;
import cc3002.t1.pokemon.waterPokemon.WaterPokemon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class WaterEnergyTest {

    private IEnergy aWaterEnergy;
    private IAttack attack10, attack20, attack30;
    private IPokemon charmander, squirtle, tangela;
    private ITrainer trainer;
    private List<ICard> trainerDeck, auxTrainerDeck;

    @Before
    public void setUp(){
        aWaterEnergy = new WaterEnergy("I'm a Water Energy!");

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
        
        auxTrainerDeck = new ArrayList<>(Arrays.asList(charmander, tangela, squirtle, aWaterEnergy));
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
        trainer.addToHand(aWaterEnergy);
        
    }

    @Test
    public void getCardNameTest() {
        assertEquals("I'm a Water Energy!", aWaterEnergy.getCardName());
    }

    @Test
    public void getTrainerTest() {
        assertNull(aWaterEnergy.getTrainer());
    }

    @Test
    public void setTrainer() {
        aWaterEnergy.setTrainer(trainer);
        assertEquals(trainer, aWaterEnergy.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        aWaterEnergy.setTrainer(trainer);
        aWaterEnergy.isPlayed();
        assertEquals(4, trainer.getHand().size());
    }

    @Test
    public void isAddedTest() {
        aWaterEnergy.setTrainer(trainer);
        aWaterEnergy.isAdded(charmander);
        assertEquals(3, trainer.getHand().size());
        assertEquals(1, charmander.getWaterEnergyAvailable());
    }

    @Test
    public void equalsTest() {
        IEnergy anotherWater = new WaterEnergy("I'm a Water Energy!");
        assertEquals(anotherWater, aWaterEnergy);
    }
}