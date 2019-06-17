package cc3002.t1.energies;

import cc3002.t1.abilities.ElectricShock;
import cc3002.t1.abilities.IAbility;
import cc3002.t1.general.EnergyCounter;
import cc3002.t1.general.ICard;
import cc3002.t1.general.ITrainer;
import cc3002.t1.general.Trainer;
import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.pokemon.basic.BasicFirePokemon;
import cc3002.t1.pokemon.basic.BasicGrassPokemon;
import cc3002.t1.pokemon.basic.BasicWaterPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class FightingEnergyTest {

    private IEnergy aFightingEnergy;
    private IPokemon charmander, squirtle, tangela;
    private ITrainer trainer;
    private List<ICard> trainerDeck, auxTrainerDeck;
    private IAbility electricShock10, electricShock20, electricShock30;
    private EnergyCounter count10, count20, count30;

    @Before
    public void setUp(){

        aFightingEnergy = new FightingEnergy("I'm a Fighting Energy!");

        count10 = new EnergyCounter();
        count10.setFireEnergy(2);
        count10.setWaterEnergy(1);

        count20 = new EnergyCounter();
        count20.setGrassEnergy(2);
        count20.setPsychicEnergy(1);

        count30 = new EnergyCounter();
        count30.setFireEnergy(1);
        count30.setGrassEnergy(2);

        electricShock10 = new ElectricShock("Electric Shock 10", 10, "This attack has a base damage of 10", count10);
        electricShock20 = new ElectricShock("Electric Shock 20", 20, "This attack has a base damage of 20", count20);
        electricShock30 = new ElectricShock("Electric Shock 30", 30, "This attack has a base damage of 30", count30);

        charmander = new BasicFirePokemon("Charmander", 4, 100,
                new ArrayList<>(Arrays.asList(electricShock30, electricShock10)));
        squirtle = new BasicWaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(electricShock10, electricShock20, electricShock30)));
        tangela = new BasicGrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(electricShock10, electricShock20)));

        auxTrainerDeck = new ArrayList<>(Arrays.asList(charmander, tangela, squirtle, aFightingEnergy));
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
        trainer.addToHand(aFightingEnergy);

    }

    @Test
    public void getCardNameTest() {
        assertEquals("I'm a Fighting Energy!", aFightingEnergy.getCardName());
    }

    @Test
    public void getTrainerTest() {
        assertNull(aFightingEnergy.getTrainer());
    }

    @Test
    public void setTrainer() {
        aFightingEnergy.setTrainer(trainer);
        assertEquals(trainer, aFightingEnergy.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        aFightingEnergy.setTrainer(trainer);
        PlayCardVisitor v = new PlayCardVisitor();
        aFightingEnergy.isPlayed(v);
        assertEquals(4, trainer.getHand().size());
    }

    @Test
    public void isAddedTest() {
        aFightingEnergy.setTrainer(trainer);
        aFightingEnergy.isAdded(charmander);
        assertEquals(3, trainer.getHand().size());
        assertEquals(1, charmander.getFightingEnergyAvailable());
    }

    @Test
    public void equalsTest() {
        IEnergy anotherFighting = new FightingEnergy("I'm a Fighting Energy!");
        assertEquals(anotherFighting, aFightingEnergy);
    }

}