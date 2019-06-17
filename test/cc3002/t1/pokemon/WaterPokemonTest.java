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

public class WaterPokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon squirtle, charmander, tangela;
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

        squirtle = new BasicWaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        charmander = new BasicFirePokemon("Charmander", 4, 90,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new BasicGrassPokemon("Tangela", 114, 100,
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
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), squirtle.getAbilityList());
        assertNull(squirtle.getSelectedAbility());
    }

    @Test
    public void setTrainerTest() {
        squirtle.setTrainer(trainer);
        assertEquals(trainer, squirtle.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        squirtle.setTrainer(trainer);
        PlayCardVisitor v = new PlayCardVisitor();
        squirtle.isPlayed(v);
        assertTrue(squirtle.getTrainer().getBench().contains(squirtle));
        assertFalse(squirtle.getTrainer().getHand().contains(squirtle));
    }

    @Test
    public void equalsTest() {
        IPokemon anotherSquirtle = new BasicWaterPokemon("Squirtle", 7, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
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
        squirtleCounter.addFireEnergy(1);
        assertEquals(squirtleCounter, squirtle.getEnergyList());
    }

    @Test
    public void setSelectedAbilityTest() {
        squirtle.setSelectedAbility(0);
        assertEquals(attack30, squirtle.getSelectedAbility());
        squirtle.setSelectedAbility(3);
        assertEquals(attack30, squirtle.getSelectedAbility());
    }

    @Test
    public void canUseAbilityTest() {
        aFire.setTrainer(trainer);
        aWater.setTrainer(trainer);
        squirtle.addEnergyToPokemon(aFire);
        squirtle.addEnergyToPokemon(aWater);
        squirtle.setSelectedAbility(0);
        assertTrue(squirtle.canUseAbility());
        squirtle.setSelectedAbility(1);
        assertFalse(squirtle.canUseAbility());
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