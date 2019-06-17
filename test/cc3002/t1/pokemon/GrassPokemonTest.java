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

public class GrassPokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack20, attack30, attack40, attack50;
    private IPokemon tangela, squirtle, pikachu;
    private ITrainer trainer;
    private List<ICard> trainerDeck, auxTrainerDeck;
    private EnergyCounter count20, count30, count40, count50;

    @Before
    public void setUp() {

        aFire = new FireEnergy();
        aPsychic = new PsychicEnergy();
        aWater = new WaterEnergy();

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

        attack20 = new ElectricShock("Electric Shock 20", 20,
                "This Electric Shock has a base damage of 20", count20);
        attack30 = new ElectricShock("Electric Shock 30", 30,
                "This Electric Shock has a base damage of 30", count30);
        attack40 = new ElectricShock("Electric Shock 40", 40,
                "This Electric Shock has a base damage of 40", count40);
        attack50 = new ElectricShock("Electric Shock 50", 50,
                "This Electric Shock has a base damage of 50", count50);

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
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), tangela.getAbilityList());
        assertNull(tangela.getSelectedAbility());
    }

    @Test
    public void setTrainerTest() {
        tangela.setTrainer(trainer);
        assertEquals(trainer, tangela.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        tangela.setTrainer(trainer);
        PlayCardVisitor v = new PlayCardVisitor();
        tangela.isPlayed(v);
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
        tangelaCounter.addFireEnergy(1);
        assertEquals(tangelaCounter, tangela.getEnergyList());
    }

    @Test
    public void setSelectedAbilityTest() {
        tangela.setSelectedAbility(0);
        assertEquals(attack30, tangela.getSelectedAbility());
        tangela.setSelectedAbility(3);
        assertEquals(attack30, tangela.getSelectedAbility());
    }

    @Test
    public void canUseAbilityTest() {
        aFire.setTrainer(trainer);
        aWater.setTrainer(trainer);
        tangela.addEnergyToPokemon(aFire);
        tangela.addEnergyToPokemon(aWater);
        tangela.setSelectedAbility(0);
        assertTrue(tangela.canUseAbility());
        tangela.setSelectedAbility(1);
        assertFalse(tangela.canUseAbility());
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