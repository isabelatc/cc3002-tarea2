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
import cc3002.t1.pokemon.basic.BasicPsychicPokemon;
import cc3002.t1.pokemon.basic.BasicWaterPokemon;
import cc3002.t1.visitors.PlayCardVisitor;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PsychicPokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon abra, squirtle, otherAbra;
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

        abra = new BasicPsychicPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new BasicWaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack50)));
        otherAbra = new BasicPsychicPokemon("Abra", 63, 90,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));

        auxTrainerDeck = new ArrayList<>(Arrays.asList(abra, squirtle, aFire, aPsychic, otherAbra, aWater));
        trainerDeck = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            for (ICard card : auxTrainerDeck) {
                trainerDeck.add(card);
            }
        }

        Collections.shuffle(trainerDeck);

        trainer = new Trainer(trainerDeck);
        trainer.addToHand(abra);
        trainer.addToHand(squirtle);
        trainer.addToHand(aFire);
        trainer.addToHand(aPsychic);
        trainer.addToHand(otherAbra);
        trainer.addToHand(aWater);

    }

    @Test
    public void constructorTest() {
        assertEquals(63, abra.getID());
        assertEquals(100, abra.getHP());
        assertEquals(new EnergyCounter(), abra.getEnergyList());
        assertEquals(0, abra.getFightingEnergyAvailable());
        assertEquals(0, abra.getFireEnergyAvailable());
        assertEquals(0, abra.getGrassEnergyAvailable());
        assertEquals(0, abra.getLightningEnergyAvailable());
        assertEquals(0, abra.getPsychicEnergyAvailable());
        assertEquals(0, abra.getWaterEnergyAvailable());
        assertEquals("Abra", abra.getCardName());
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), abra.getAbilityList());
        assertNull(abra.getSelectedAbility());
    }

    @Test
    public void setTrainerTest() {
        abra.setTrainer(trainer);
        assertEquals(trainer, abra.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        abra.setTrainer(trainer);
        PlayCardVisitor v = new PlayCardVisitor();
        abra.isPlayed(v);
        assertTrue(abra.getTrainer().getBench().contains(abra));
        assertFalse(abra.getTrainer().getHand().contains(abra));
    }

    @Test
    public void equalsTest() {
        IPokemon anotherAbra = new BasicPsychicPokemon("Abra", 63, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
        assertEquals(anotherAbra, abra);
        assertNotEquals(otherAbra, abra);
    }

    @Test
    public void updateHPTest() {
        abra.updateHP(90);
        assertEquals(90, abra.getHP());
    }

    @Test
    public void addEnergyToPokemonTest() {
        EnergyCounter abraCounter = abra.getEnergyList();
        aFire.setTrainer(trainer);
        abra.addEnergyToPokemon(aFire);
        abraCounter.addFireEnergy(1);
        assertEquals(abraCounter, abra.getEnergyList());
    }

    @Test
    public void setSelectedAbilityTest() {
        abra.setSelectedAbility(0);
        assertEquals(attack30, abra.getSelectedAbility());
        abra.setSelectedAbility(3);
        assertEquals(attack30, abra.getSelectedAbility());
    }

    @Test
    public void canUseAbilityTest() {
        aFire.setTrainer(trainer);
        aWater.setTrainer(trainer);
        abra.addEnergyToPokemon(aFire);
        abra.addEnergyToPokemon(aWater);
        abra.setSelectedAbility(0);
        assertTrue(abra.canUseAbility());
        abra.setSelectedAbility(1);
        assertFalse(abra.canUseAbility());
    }

    @Test
    public void attackedByFightingPokemon() {
        abra.attackedByFightingPokemon(attack40);
        assertEquals(90, abra.getHP());
    }

    @Test
    public void attackedByFirePokemon() {
        abra.attackedByFirePokemon(attack40);
        assertEquals(60, abra.getHP());
    }

    @Test
    public void attackedByGrassPokemon() {
        abra.attackedByGrassPokemon(attack40);
        assertEquals(60, abra.getHP());
    }

    @Test
    public void attackedByLightningPokemon() {
        abra.attackedByLightningPokemon(attack40);
        assertEquals(60, abra.getHP());
    }

    @Test
    public void attackedByPsychicPokemon() {
        abra.attackedByPsychicPokemon(attack40);
        assertEquals(20, abra.getHP());
    }

    @Test
    public void attackedByWaterPokemon() {
        abra.attackedByWaterPokemon(attack40);
        assertEquals(60, abra.getHP());
    }

}