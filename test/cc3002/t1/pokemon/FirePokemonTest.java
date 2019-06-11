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

public class FirePokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon charmander, squirtle, tangela;
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

        charmander = new FirePokemon("Charmander", 4, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack30, attack40, attack50)));

        auxTrainerDeck = new ArrayList<>(Arrays.asList(charmander, squirtle, aFire, aPsychic, tangela, aWater));
        trainerDeck = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (ICard card : auxTrainerDeck) {
                trainerDeck.add(card);
            }
        }

        Collections.shuffle(trainerDeck);

        trainer = new Trainer(trainerDeck);
        trainer.addToHand(charmander);
        trainer.addToHand(squirtle);
        trainer.addToHand(aFire);
        trainer.addToHand(aPsychic);
        trainer.addToHand(tangela);
        trainer.addToHand(aWater);

    }

    @Test
    public void constructorTest() {
        assertEquals(4, charmander.getID());
        assertEquals(100, charmander.getHP());
        assertEquals(new EnergyCounter(), charmander.getEnergyList());
        assertEquals(0, charmander.getFightingEnergyAvailable());
        assertEquals(0, charmander.getFireEnergyAvailable());
        assertEquals(0, charmander.getGrassEnergyAvailable());
        assertEquals(0, charmander.getLightningEnergyAvailable());
        assertEquals(0, charmander.getPsychicEnergyAvailable());
        assertEquals(0, charmander.getWaterEnergyAvailable());
        assertEquals("Charmander", charmander.getCardName());
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), charmander.getAttacks());
        assertNull(charmander.getSelectedAttack());
    }

    @Test
    public void setTrainerTest() {
        charmander.setTrainer(trainer);
        assertEquals(trainer, charmander.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        charmander.setTrainer(trainer);
        charmander.isPlayed();
        assertTrue(charmander.getTrainer().getBench().contains(charmander));
        assertFalse(charmander.getTrainer().getHand().contains(charmander));
    }

    @Test
    public void equalsTest() {
        IPokemon anotherCharmander = new FirePokemon("Charmander", 4, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
        assertEquals(anotherCharmander, charmander);
        assertNotEquals(squirtle, charmander);
    }

    @Test
    public void updateHPTest() {
        charmander.updateHP(90);
        assertEquals(90, charmander.getHP());
    }

    @Test
    public void addEnergyToPokemonTest() {
        EnergyCounter charmanderCounter = charmander.getEnergyList();
        aFire.setTrainer(trainer);
        charmander.addEnergyToPokemon(aFire);
        charmanderCounter.addFireEnergy();
        assertEquals(charmanderCounter, charmander.getEnergyList());
    }


    @Test
    public void setAttackTest() {
        charmander.setAttack(0);
        assertEquals(attack30, charmander.getSelectedAttack());
        charmander.setAttack(3);
        assertEquals(attack30, charmander.getSelectedAttack());
    }

    @Test
    public void canAttackTest() {
        aFire.setTrainer(trainer);
        aWater.setTrainer(trainer);
        charmander.addEnergyToPokemon(aFire);
        charmander.addEnergyToPokemon(aWater);
        charmander.setAttack(0);
        assertTrue(charmander.canAttack());
        charmander.setAttack(1);
        assertFalse(charmander.canAttack());
    }

    @Test
    public void attackTest() {
        charmander.setAttack(0);
        charmander.attack(squirtle);
        assertEquals(100, squirtle.getHP());

        aFire.setTrainer(trainer);
        aPsychic.setTrainer(trainer);
        aWater.setTrainer(trainer);
        charmander.addEnergyToPokemon(aFire);
        charmander.addEnergyToPokemon(aPsychic);
        charmander.addEnergyToPokemon(aWater);

        charmander.setAttack(1);
        charmander.attack(squirtle);
        assertEquals(50, squirtle.getHP());

        charmander.setAttack(0);
        charmander.attack(tangela);
        assertEquals(40, tangela.getHP());
    }

    @Test
    public void attackedByFightingPokemon() {
        charmander.attackedByFightingPokemon(attack40);
        assertEquals(60, charmander.getHP());
    }

    @Test
    public void attackedByFirePokemon() {
        charmander.attackedByFirePokemon(attack40);
        assertEquals(60, charmander.getHP());
    }

    @Test
    public void attackedByGrassPokemon() {
        charmander.attackedByGrassPokemon(attack40);
        assertEquals(60, charmander.getHP());
    }

    @Test
    public void attackedByLightningPokemon() {
        charmander.attackedByLightningPokemon(attack40);
        assertEquals(60, charmander.getHP());
    }

    @Test
    public void attackedByPsychicPokemon() {
        charmander.attackedByPsychicPokemon(attack40);
        assertEquals(60, charmander.getHP());
    }

    @Test
    public void attackedByWaterPokemon() {
        charmander.attackedByWaterPokemon(attack40);
        assertEquals(20, charmander.getHP());
    }

}