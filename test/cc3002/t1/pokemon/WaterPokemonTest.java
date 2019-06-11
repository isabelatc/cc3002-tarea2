package cc3002.t1.pokemon;

import cc3002.t1.*;
import cc3002.t1.energies.FireEnergy;
import cc3002.t1.energies.PsychicEnergy;
import cc3002.t1.energies.WaterEnergy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class WaterPokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon squirtle, charmander, tangela;
    private ITrainer trainer;
    private List<ICard> trainerDeck, auxTrainerDeck;

    @Before
    public void setUp() {

        aFire = new FireEnergy();
        aPsychic = new PsychicEnergy();
        aWater = new WaterEnergy();

        attack10 = new Attack("Attack 10", 10, "This attack has a base damage of 10",
                0, 2, 0, 0, 0, 1);
        attack20 = new Attack("Attack 20", 20, "This attack has a base damage of 20",
                0, 0, 2, 0, 1, 0);
        attack30 = new Attack("Attack 30", 30, "This attack has a base damage of 30",
                0, 1, 0, 0, 0, 1);
        attack40 = new Attack("Attack 40", 40, "This attack has a base damage of 40",
                0, 1, 2, 0, 1, 0);
        attack50 = new Attack("Attack 50", 50, "This attack has a base damage of 50",
                0, 1, 0, 0, 1, 0);

        squirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        charmander = new FirePokemon("Charmander", 4, 90,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack30, attack40, attack50)));

        auxTrainerDeck = new ArrayList<>(Arrays.asList(squirtle, charmander, aFire, aPsychic, tangela, aWater));
        trainerDeck = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (ICard card : auxTrainerDeck) {
                trainerDeck.add(card);
            }
        }

        Collections.shuffle(trainerDeck);

        trainer = new Trainer(trainerDeck);
        trainer.addToHand(squirtle);
        trainer.addToHand(charmander);
        trainer.addToHand(aFire);
        trainer.addToHand(aPsychic);
        trainer.addToHand(tangela);
        trainer.addToHand(aWater);

    }

    @Test
    public void constructorTest() {
        assertEquals(7, squirtle.getID());
        assertEquals(100, squirtle.getHP());
        assertEquals(new EnergyCounter(), squirtle.getEnergyList());
        assertEquals(0, squirtle.getFightingEnergyAvailable());
        assertEquals(0, squirtle.getFireEnergyAvailable());
        assertEquals(0, squirtle.getGrassEnergyAvailable());
        assertEquals(0, squirtle.getLightningEnergyAvailable());
        assertEquals(0, squirtle.getPsychicEnergyAvailable());
        assertEquals(0, squirtle.getWaterEnergyAvailable());
        assertEquals("Squirtle", squirtle.getCardName());
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), squirtle.getAttacks());
        assertNull(squirtle.getSelectedAttack());
    }

    @Test
    public void setTrainerTest() {
        squirtle.setTrainer(trainer);
        assertEquals(trainer, squirtle.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        squirtle.setTrainer(trainer);
        squirtle.isPlayed();
        assertTrue(squirtle.getTrainer().getBench().contains(squirtle));
        assertFalse(squirtle.getTrainer().getHand().contains(squirtle));
    }

    @Test
    public void equalsTest() {
        IPokemon anotherSquirtle = new WaterPokemon("Squirtle", 7, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
        assertEquals(anotherSquirtle, squirtle);
        assertNotEquals(charmander, squirtle);
    }

    @Test
    public void updateHPTest() {
        squirtle.updateHP(90);
        assertEquals(90, squirtle.getHP());
    }

    @Test
    public void addEnergyToPokemonTest() {
        EnergyCounter squirtleCounter = squirtle.getEnergyList();
        aFire.setTrainer(trainer);
        squirtle.addEnergyToPokemon(aFire);
        squirtleCounter.addFireEnergy();
        assertEquals(squirtleCounter, squirtle.getEnergyList());
    }

    @Test
    public void setAttackTest() {
        squirtle.setAttack(0);
        assertEquals(attack30, squirtle.getSelectedAttack());
        squirtle.setAttack(3);
        assertEquals(attack30, squirtle.getSelectedAttack());
    }

    @Test
    public void canAttackTest() {
        aFire.setTrainer(trainer);
        aWater.setTrainer(trainer);
        squirtle.addEnergyToPokemon(aFire);
        squirtle.addEnergyToPokemon(aWater);
        squirtle.setAttack(0);
        assertTrue(squirtle.canAttack());
        squirtle.setAttack(1);
        assertFalse(squirtle.canAttack());
    }

    @Test
    public void attackTest() {
        squirtle.setAttack(0);
        squirtle.attack(charmander);
        assertEquals(90, charmander.getHP());

        aFire.setTrainer(trainer);
        aPsychic.setTrainer(trainer);
        aWater.setTrainer(trainer);
        squirtle.addEnergyToPokemon(aFire);
        squirtle.addEnergyToPokemon(aPsychic);
        squirtle.addEnergyToPokemon(aWater);

        charmander.setTrainer(trainer);
        squirtle.setAttack(1);
        squirtle.attack(charmander);
        assertEquals(0, charmander.getHP());

        squirtle.setAttack(1);
        squirtle.attack(tangela);
        assertEquals(80, tangela.getHP());
    }

    @Test
    public void attackedByFightingPokemon() {
        squirtle.attackedByFightingPokemon(attack40);
        assertEquals(90, squirtle.getHP());
    }

    @Test
    public void attackedByFirePokemon() {
        squirtle.attackedByFirePokemon(attack40);
        assertEquals(60, squirtle.getHP());
    }

    @Test
    public void attackedByGrassPokemon() {
        squirtle.attackedByGrassPokemon(attack40);
        assertEquals(20, squirtle.getHP());
    }

    @Test
    public void attackedByLightningPokemon() {
        squirtle.attackedByLightningPokemon(attack40);
        assertEquals(20, squirtle.getHP());
    }

    @Test
    public void attackedByPsychicPokemon() {
        squirtle.attackedByPsychicPokemon(attack40);
        assertEquals(60, squirtle.getHP());
    }

    @Test
    public void attackedByWaterPokemon() {
        squirtle.attackedByWaterPokemon(attack40);
        assertEquals(60, squirtle.getHP());
    }

}