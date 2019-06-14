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
import cc3002.t1.pokemon.grassPokemon.GrassPokemon;
import cc3002.t1.pokemon.lightningPokemon.LightningPokemon;
import cc3002.t1.pokemon.waterPokemon.WaterPokemon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class LightningPokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon pikachu, squirtle, tangela;
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

        pikachu = new LightningPokemon("Pikachu", 25, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new WaterPokemon("Squirtle", 7, 90,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack30, attack40, attack50)));

        auxTrainerDeck = new ArrayList<>(Arrays.asList(pikachu, squirtle, aFire, aPsychic, tangela, aWater));
        trainerDeck = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (ICard card : auxTrainerDeck) {
                trainerDeck.add(card);
            }
        }

        Collections.shuffle(trainerDeck);

        trainer = new Trainer(trainerDeck);
        trainer.addToHand(pikachu);
        trainer.addToHand(squirtle);
        trainer.addToHand(aFire);
        trainer.addToHand(aPsychic);
        trainer.addToHand(tangela);
        trainer.addToHand(aWater);

    }

    @Test
    public void constructorTest() {
        assertEquals(25, pikachu.getID());
        assertEquals(100, pikachu.getHP());
        assertEquals(new EnergyCounter(), pikachu.getEnergyList());
        assertEquals(0, pikachu.getFightingEnergyAvailable());
        assertEquals(0, pikachu.getFireEnergyAvailable());
        assertEquals(0, pikachu.getGrassEnergyAvailable());
        assertEquals(0, pikachu.getLightningEnergyAvailable());
        assertEquals(0, pikachu.getPsychicEnergyAvailable());
        assertEquals(0, pikachu.getWaterEnergyAvailable());
        assertEquals("Pikachu", pikachu.getCardName());
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), pikachu.getAttacks());
        assertNull(pikachu.getSelectedAttack());
    }

    @Test
    public void setTrainerTest() {
        pikachu.setTrainer(trainer);
        assertEquals(trainer, pikachu.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        pikachu.setTrainer(trainer);
        pikachu.isPlayed();
        assertTrue(pikachu.getTrainer().getBench().contains(pikachu));
        assertFalse(pikachu.getTrainer().getHand().contains(pikachu));
    }

    @Test
    public void equalsTest() {
        IPokemon anotherPikachu = new LightningPokemon("Pikachu", 25, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
        assertEquals(anotherPikachu, pikachu);
        assertNotEquals(squirtle, pikachu);
    }

    @Test
    public void updateHPTest() {
        pikachu.updateHP(90);
        assertEquals(90, pikachu.getHP());
    }

    @Test
    public void addEnergyToPokemonTest() {
        EnergyCounter pikachuCounter = pikachu.getEnergyList();
        aFire.setTrainer(trainer);
        pikachu.addEnergyToPokemon(aFire);
        pikachuCounter.addFireEnergy();
        assertEquals(pikachuCounter, pikachu.getEnergyList());
    }

    @Test
    public void setAttackTest() {
        pikachu.setAttack(0);
        assertEquals(attack30, pikachu.getSelectedAttack());
        pikachu.setAttack(3);
        assertEquals(attack30, pikachu.getSelectedAttack());
    }

    @Test
    public void canAttackTest() {
        aFire.setTrainer(trainer);
        aWater.setTrainer(trainer);
        pikachu.addEnergyToPokemon(aFire);
        pikachu.addEnergyToPokemon(aWater);
        pikachu.setAttack(0);
        assertTrue(pikachu.canAttack());
        pikachu.setAttack(1);
        assertFalse(pikachu.canAttack());
    }

    @Test
    public void attackTest() {
        pikachu.setAttack(0);
        pikachu.attack(squirtle);
        assertEquals(90, squirtle.getHP());

        aFire.setTrainer(trainer);
        aPsychic.setTrainer(trainer);
        aWater.setTrainer(trainer);
        pikachu.addEnergyToPokemon(aFire);
        pikachu.addEnergyToPokemon(aPsychic);
        pikachu.addEnergyToPokemon(aWater);

        squirtle.setTrainer(trainer);
        pikachu.setAttack(1);
        pikachu.attack(squirtle);
        assertEquals(0, squirtle.getHP());

        pikachu.setAttack(0);
        pikachu.attack(tangela);
        assertEquals(70, tangela.getHP());
    }

    @Test
    public void attackedByFightingPokemon() {
        pikachu.attackedByFightingPokemon(attack40);
        assertEquals(20, pikachu.getHP());
    }

    @Test
    public void attackedByFirePokemon() {
        pikachu.attackedByFirePokemon(attack40);
        assertEquals(60, pikachu.getHP());
    }

    @Test
    public void attackedByGrassPokemon() {
        pikachu.attackedByGrassPokemon(attack40);
        assertEquals(60, pikachu.getHP());
    }

    @Test
    public void attackedByLightningPokemon() {
        pikachu.attackedByLightningPokemon(attack40);
        assertEquals(60, pikachu.getHP());
    }

    @Test
    public void attackedByPsychicPokemon() {
        pikachu.attackedByPsychicPokemon(attack40);
        assertEquals(60, pikachu.getHP());
    }

    @Test
    public void attackedByWaterPokemon() {
        pikachu.attackedByWaterPokemon(attack40);
        assertEquals(60, pikachu.getHP());
    }

}