package cc3002.t1.pokemon;

import cc3002.t1.abilities.Attack;
import cc3002.t1.abilities.IAttack;
import cc3002.t1.energies.FireEnergy;
import cc3002.t1.energies.IEnergy;
import cc3002.t1.energies.PsychicEnergy;
import cc3002.t1.energies.WaterEnergy;

import cc3002.t1.general.EnergyCounter;
import cc3002.t1.general.ICard;
import cc3002.t1.general.ITrainer;
import cc3002.t1.general.Trainer;
import cc3002.t1.pokemon.basic.BasicGrassPokemon;
import cc3002.t1.pokemon.basic.BasicLightningPokemon;
import cc3002.t1.pokemon.basic.BasicWaterPokemon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class GrassPokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack20, attack30, attack40, attack50;
    private IPokemon tangela, squirtle, pikachu;
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

        tangela = new BasicGrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new BasicWaterPokemon("Squirtle", 7, 90,
                new ArrayList<>(Arrays.asList(attack20, attack50)));
        pikachu = new BasicLightningPokemon("Pikachu", 25, 100,
                new ArrayList<>(Arrays.asList(attack20, attack30, attack40, attack50)));

        auxTrainerDeck = new ArrayList<>(Arrays.asList(tangela, squirtle, aFire, aPsychic, pikachu, aWater));
        trainerDeck = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (ICard card : auxTrainerDeck) {
                trainerDeck.add(card);
            }
        }

        Collections.shuffle(trainerDeck);

        trainer = new Trainer(trainerDeck);
        trainer.addToHand(tangela);
        trainer.addToHand(squirtle);
        trainer.addToHand(aFire);
        trainer.addToHand(aPsychic);
        trainer.addToHand(pikachu);
        trainer.addToHand(aWater);

    }

    @Test
    public void constructorTest() {
        assertEquals(114, tangela.getID());
        assertEquals(100, tangela.getHP());
        assertEquals(new EnergyCounter(), tangela.getEnergyList());
        assertEquals(0, tangela.getFightingEnergyAvailable());
        assertEquals(0, tangela.getFireEnergyAvailable());
        assertEquals(0, tangela.getGrassEnergyAvailable());
        assertEquals(0, tangela.getLightningEnergyAvailable());
        assertEquals(0, tangela.getPsychicEnergyAvailable());
        assertEquals(0, tangela.getWaterEnergyAvailable());
        assertEquals("Tangela", tangela.getCardName());
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), tangela.getAttacks());
        assertNull(tangela.getSelectedAttack());
    }

    @Test
    public void setTrainerTest() {
        tangela.setTrainer(trainer);
        assertEquals(trainer, tangela.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        tangela.setTrainer(trainer);
        tangela.isPlayed();
        assertTrue(tangela.getTrainer().getBench().contains(tangela));
        assertFalse(tangela.getTrainer().getHand().contains(tangela));
    }

    @Test
    public void equalsTest() {
        IPokemon anotherTangela = new BasicGrassPokemon("Tangela", 114, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
        assertEquals(anotherTangela, tangela);
        assertNotEquals(squirtle, tangela);
    }

    @Test
    public void updateHPTest() {
        tangela.updateHP(90);
        assertEquals(90, tangela.getHP());
    }

    @Test
    public void addEnergyToPokemonTest() {
        EnergyCounter tangelaCounter = tangela.getEnergyList();
        aFire.setTrainer(trainer);
        tangela.addEnergyToPokemon(aFire);
        tangelaCounter.addFireEnergy();
        assertEquals(tangelaCounter, tangela.getEnergyList());
    }

    @Test
    public void setAttackTest() {
        tangela.setAttack(0);
        assertEquals(attack30, tangela.getSelectedAttack());
        tangela.setAttack(3);
        assertEquals(attack30, tangela.getSelectedAttack());
    }

    @Test
    public void canAttackTest() {
        aFire.setTrainer(trainer);
        aWater.setTrainer(trainer);
        tangela.addEnergyToPokemon(aFire);
        tangela.addEnergyToPokemon(aWater);
        tangela.setAttack(0);
        assertTrue(tangela.canAttack());
        tangela.setAttack(1);
        assertFalse(tangela.canAttack());
    }

    @Test
    public void attackTest() {
        tangela.setAttack(0);
        tangela.attack(squirtle);
        assertEquals(90, squirtle.getHP());

        aFire.setTrainer(trainer);
        aPsychic.setTrainer(trainer);
        aWater.setTrainer(trainer);
        tangela.addEnergyToPokemon(aFire);
        tangela.addEnergyToPokemon(aPsychic);
        tangela.addEnergyToPokemon(aWater);

        squirtle.setTrainer(trainer);
        tangela.setAttack(1);
        tangela.attack(squirtle);
        assertEquals(0, squirtle.getHP());

        tangela.setAttack(0);
        tangela.attack(pikachu);
        assertEquals(70, pikachu.getHP());
    }

    @Test
    public void attackedByFightingPokemon() {
        tangela.attackedByFightingPokemon(attack40);
        assertEquals(60, tangela.getHP());
    }

    @Test
    public void attackedByFirePokemon() {
        tangela.attackedByFirePokemon(attack40);
        assertEquals(20, tangela.getHP());
    }

    @Test
    public void attackedByGrassPokemon() {
        tangela.attackedByGrassPokemon(attack40);
        assertEquals(60, tangela.getHP());
    }

    @Test
    public void attackedByLightningPokemon() {
        tangela.attackedByLightningPokemon(attack40);
        assertEquals(60, tangela.getHP());
    }

    @Test
    public void attackedByPsychicPokemon() {
        tangela.attackedByPsychicPokemon(attack40);
        assertEquals(60, tangela.getHP());
    }

    @Test
    public void attackedByWaterPokemon() {
        tangela.attackedByWaterPokemon(attack40);
        assertEquals(90, tangela.getHP());
    }

}