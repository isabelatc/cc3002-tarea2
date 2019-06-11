package cc3002.t1.pokemon;

import cc3002.t1.*;
import cc3002.t1.energies.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class FightingPokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack20, attack30, attack40, attack50;
    private IPokemon mankey, squirtle, pikachu;
    private ITrainer trainer;
    private List<ICard> trainerDeck, auxTrainerDeck;


    @Before
    public void setUp() {

        aFire = new FireEnergy();
        aPsychic = new PsychicEnergy();
        aWater = new WaterEnergy();

        attack20 = new Attack("Attack 20", 20, "This attack has a base damage of 20",
                0, 0, 2, 0, 1, 0);
        attack30 = new Attack("Attack 30", 30, "This attack has a base damage of 30",
                0, 1, 0, 0, 0, 1);
        attack40 = new Attack("Attack 40", 40, "This attack has a base damage of 40",
                0, 1, 2, 0, 1, 0);
        attack50 = new Attack("Attack 50", 50, "This attack has a base damage of 50",
                0, 1, 0, 0, 1, 0);

        mankey = new FightingPokemon("Mankey", 56, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack20, attack50)));
        pikachu = new LightningPokemon("Pikachu", 25, 50,
                new ArrayList<>(Arrays.asList(attack20, attack30, attack40, attack50)));

        auxTrainerDeck = new ArrayList<>(Arrays.asList(mankey, squirtle, aFire, aPsychic, pikachu, aWater));
        trainerDeck = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (ICard card : auxTrainerDeck) {
                trainerDeck.add(card);
            }
        }

        Collections.shuffle(trainerDeck);

        trainer = new Trainer(trainerDeck);
        trainer.addToHand(mankey);
        trainer.addToHand(squirtle);
        trainer.addToHand(aFire);
        trainer.addToHand(aPsychic);
        trainer.addToHand(pikachu);
        trainer.addToHand(aWater);

    }

    @Test
    public void constructorTest() {
        assertEquals(56, mankey.getID());
        assertEquals(100, mankey.getHP());
        assertEquals(new EnergyCounter(), mankey.getEnergyList());
        assertEquals(0, mankey.getFightingEnergyAvailable());
        assertEquals(0, mankey.getFireEnergyAvailable());
        assertEquals(0, mankey.getGrassEnergyAvailable());
        assertEquals(0, mankey.getLightningEnergyAvailable());
        assertEquals(0, mankey.getPsychicEnergyAvailable());
        assertEquals(0, mankey.getWaterEnergyAvailable());
        assertEquals("Mankey", mankey.getCardName());
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), mankey.getAttacks());
        assertNull(mankey.getSelectedAttack());
        assertNull(mankey.getTrainer());
    }

    @Test
    public void setTrainerTest() {
        mankey.setTrainer(trainer);
        assertEquals(trainer, mankey.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        mankey.setTrainer(trainer);
        mankey.isPlayed();
        assertTrue(mankey.getTrainer().getBench().contains(mankey));
        assertFalse(mankey.getTrainer().getHand().contains(mankey));
    }

    @Test
    public void equalsTest() {
        IPokemon anotherMankey = new FightingPokemon("Mankey", 56, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
        assertEquals(anotherMankey, mankey);
        assertNotEquals(squirtle, mankey);
    }

    @Test
    public void updateHPTest() {
        mankey.updateHP(90);
        assertEquals(90, mankey.getHP());
    }

    @Test
    public void addEnergyToPokemonTest() {
        EnergyCounter mankeyCounter = mankey.getEnergyList();
        aFire.setTrainer(trainer);
        mankey.addEnergyToPokemon(aFire);
        mankeyCounter.addFireEnergy();
        assertEquals(mankeyCounter, mankey.getEnergyList());
    }

    @Test
    public void setAttackTest() {
        mankey.setAttack(0);
        assertEquals(attack30, mankey.getSelectedAttack());
        mankey.setAttack(3);
        assertEquals(attack30, mankey.getSelectedAttack());
    }

    @Test
    public void canAttackTest() {
        aFire.setTrainer(trainer);
        aWater.setTrainer(trainer);
        mankey.addEnergyToPokemon(aFire);
        mankey.addEnergyToPokemon(aWater);
        mankey.setAttack(0);
        assertTrue(mankey.canAttack());
        mankey.setAttack(1);
        assertFalse(mankey.canAttack());
    }

    @Test
    public void attackTest() {
        mankey.setAttack(0);
        mankey.attack(squirtle);
        assertEquals(100, squirtle.getHP());

        aFire.setTrainer(trainer);
        aPsychic.setTrainer(trainer);
        aWater.setTrainer(trainer);
        mankey.addEnergyToPokemon(aFire);
        mankey.addEnergyToPokemon(aPsychic);
        mankey.addEnergyToPokemon(aWater);

        mankey.setAttack(1);
        mankey.attack(squirtle);
        assertEquals(80, squirtle.getHP());

        pikachu.setTrainer(trainer);
        mankey.setAttack(0);
        mankey.attack(pikachu);
        assertEquals(0, pikachu.getHP());
    }

    @Test
    public void attackedByFightingPokemonTest() {
        mankey.attackedByFightingPokemon(attack40);
        assertEquals(60, mankey.getHP());
    }

    @Test
    public void attackedByFirePokemonTest() {
        mankey.attackedByFirePokemon(attack40);
        assertEquals(60, mankey.getHP());
    }

    @Test
    public void attackedByGrassPokemonTest() {
        mankey.attackedByGrassPokemon(attack40);
        assertEquals(20, mankey.getHP());
    }

    @Test
    public void attackedByLightningPokemonTest() {
        mankey.attackedByLightningPokemon(attack40);
        assertEquals(60, mankey.getHP());
    }

    @Test
    public void attackedByPsychicPokemonTest() {
        mankey.attackedByPsychicPokemon(attack40);
        assertEquals(20, mankey.getHP());
    }

    @Test
    public void attackedByWaterPokemonTest() {
        mankey.attackedByWaterPokemon(attack40);
        assertEquals(60, mankey.getHP());
    }

}