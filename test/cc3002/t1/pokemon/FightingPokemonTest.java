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
import cc3002.t1.pokemon.basic.BasicFightingPokemon;
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

public class FightingPokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack20, attack30, attack40, attack50;
    private IPokemon mankey, squirtle, pikachu;
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

        mankey = new BasicFightingPokemon("Mankey", 56, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new BasicWaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack20, attack50)));
        pikachu = new BasicLightningPokemon("Pikachu", 25, 50,
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
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), mankey.getAbilityList());
        assertNull(mankey.getSelectedAbility());
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
        PlayCardVisitor v = new PlayCardVisitor();
        mankey.isPlayed(v);
        assertTrue(mankey.getTrainer().getBench().contains(mankey));
        assertFalse(mankey.getTrainer().getHand().contains(mankey));
    }

    @Test
    public void equalsTest() {
        IPokemon anotherMankey = new BasicFightingPokemon("Mankey", 56, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
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
        mankeyCounter.addFireEnergy(1);
        assertEquals(mankeyCounter, mankey.getEnergyList());
    }

    @Test
    public void setSelectedAbilityTest() {
        mankey.setSelectedAbility(0);
        assertEquals(attack30, mankey.getSelectedAbility());
        mankey.setSelectedAbility(3);
        assertEquals(attack30, mankey.getSelectedAbility());
    }

    @Test
    public void canUseAbilityTest() {
        aFire.setTrainer(trainer);
        aWater.setTrainer(trainer);
        mankey.addEnergyToPokemon(aFire);
        mankey.addEnergyToPokemon(aWater);
        mankey.setSelectedAbility(0);
        assertTrue(mankey.canUseAbility());
        mankey.setSelectedAbility(1);
        assertFalse(mankey.canUseAbility());
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