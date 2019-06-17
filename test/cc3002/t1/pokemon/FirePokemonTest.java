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

public class FirePokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon charmander, squirtle, tangela;
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

        charmander = new BasicFirePokemon("Charmander", 4, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new BasicWaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new BasicGrassPokemon("Tangela", 114, 100,
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
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), charmander.getAbilityList());
        assertNull(charmander.getSelectedAbility());
    }

    @Test
    public void setTrainerTest() {
        charmander.setTrainer(trainer);
        assertEquals(trainer, charmander.getTrainer());
    }

    @Test
    public void isPlayedTest() {
        charmander.setTrainer(trainer);
        PlayCardVisitor v = new PlayCardVisitor();
        charmander.isPlayed(v);
        assertTrue(charmander.getTrainer().getBench().contains(charmander));
        assertFalse(charmander.getTrainer().getHand().contains(charmander));
    }

    @Test
    public void equalsTest() {
        IPokemon anotherCharmander = new BasicFirePokemon("Charmander", 4, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
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
        charmanderCounter.addFireEnergy(1);
        assertEquals(charmanderCounter, charmander.getEnergyList());
    }


    @Test
    public void setSelectedAbilityTest() {
        charmander.setSelectedAbility(0);
        assertEquals(attack30, charmander.getSelectedAbility());
        charmander.setSelectedAbility(3);
        assertEquals(attack30, charmander.getSelectedAbility());
    }

    @Test
    public void canUseAbilityTest() {
        aFire.setTrainer(trainer);
        aWater.setTrainer(trainer);
        charmander.addEnergyToPokemon(aFire);
        charmander.addEnergyToPokemon(aWater);
        charmander.setSelectedAbility(0);
        assertTrue(charmander.canUseAbility());
        charmander.setSelectedAbility(1);
        assertFalse(charmander.canUseAbility());
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