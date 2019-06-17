package cc3002.t1.pokemon;

import cc3002.t1.abilities.ElectricShock;
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
import cc3002.t1.visitors.PlayCardVisitor;
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
    private EnergyCounter count10, count20, count30, count40, count50;

    @Before
    public void setUp() {

        aFire = new FireEnergy();
        aPsychic = new PsychicEnergy();
        aWater = new WaterEnergy();

        count10 = new EnergyCounter();
        count10.setFireEnergy(2);
        count10.setWaterEnergy(1);

        count20 = new EnergyCounter();
        count20.setGrassEnergy(2);
        count20.setPsychicEnergy(1);

        count30 = new EnergyCounter();
        count30.setFireEnergy(1);
        count30.setWaterEnergy(1);

        count40 = new EnergyCounter();
        count40.setFireEnergy(1);
        count40.setGrassEnergy(2);
        count40.setPsychicEnergy(1);

        count50 = new EnergyCounter();
        count50.setFireEnergy(1);
        count50.setPsychicEnergy(1);

        attack10 = new ElectricShock("Electric Shock 10", 10,
                "This Electric Shock has a base damage of 10", count10);
        attack20 = new ElectricShock("Electric Shock 20", 20,
                "This Electric Shock has a base damage of 20", count20);
        attack30 = new ElectricShock("Electric Shock 30", 30,
                "This Electric Shock has a base damage of 30", count30);
        attack40 = new ElectricShock("Electric Shock 40", 40,
                "This Electric Shock has a base damage of 40", count40);
        attack50 = new ElectricShock("Electric Shock 50", 50,
                "This Electric Shock has a base damage of 50", count50);

        pikachu = new BasicLightningPokemon("Pikachu", 25, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new BasicWaterPokemon("Squirtle", 7, 90,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new BasicGrassPokemon("Tangela", 114, 100,
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
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), pikachu.getAbilityList());
        assertNull(pikachu.getSelectedAbility());
    }

    @Test
    public void setTrainerTest() {
        pikachu.setTrainer(trainer);
        assertEquals(trainer, pikachu.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        pikachu.setTrainer(trainer);
        PlayCardVisitor v = new PlayCardVisitor();
        pikachu.isPlayed(v);
        assertTrue(pikachu.getTrainer().getBench().contains(pikachu));
        assertFalse(pikachu.getTrainer().getHand().contains(pikachu));
    }

    @Test
    public void equalsTest() {
        IPokemon anotherPikachu = new BasicLightningPokemon("Pikachu", 25, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
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
        pikachuCounter.addFireEnergy(1);
        assertEquals(pikachuCounter, pikachu.getEnergyList());
    }

    @Test
    public void setSelectedAbilityTest() {
        pikachu.setSelectedAbility(0);
        assertEquals(attack30, pikachu.getSelectedAbility());
        pikachu.setSelectedAbility(3);
        assertEquals(attack30, pikachu.getSelectedAbility());
    }

    @Test
    public void canUseAbilityTest() {
        aFire.setTrainer(trainer);
        aWater.setTrainer(trainer);
        pikachu.addEnergyToPokemon(aFire);
        pikachu.addEnergyToPokemon(aWater);
        pikachu.setSelectedAbility(0);
        assertTrue(pikachu.canUseAbility());
        pikachu.setSelectedAbility(1);
        assertFalse(pikachu.canUseAbility());
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