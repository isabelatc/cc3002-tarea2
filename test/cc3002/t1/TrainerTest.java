package cc3002.t1;

import cc3002.t1.energies.*;
import cc3002.t1.pokemon.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TrainerTest {

    private IEnergy aFightingEnergy, aFireEnergy, aGrassEnergy, aLightningEnergy, anotherGrassEnergy, aWaterEnergy;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon charmander, squirtle, tangela, abra, anotherSquirtle, anotherTangela, pikachu, mankey, anotherAbra;
    private ITrainer trainer, opponent;

    @Before
    public void setUp() {

        aFightingEnergy = new FightingEnergy();
        aFireEnergy = new FireEnergy();
        aGrassEnergy = new GrassEnergy();
        aLightningEnergy = new LightningEnergy();
        anotherGrassEnergy = new GrassEnergy();
        aWaterEnergy = new WaterEnergy();

        attack10 = new Attack("Attack 10", 10, "This attack has a base damage of 10",
                0, 2, 0, 0, 0, 1);
        attack20 = new Attack("Attack 20", 20, "This attack has a base damage of 20",
                0, 0, 2, 0, 1, 0);
        attack30 = new Attack("Attack 30", 30, "This attack has a base damage of 30",
                0, 1, 2, 0, 0, 0);
        attack40 = new Attack("Attack 40", 40, "This attack has a base damage of 40",
                0, 1, 2, 0, 1, 0);
        attack50 = new Attack("Attack 50", 50, "This attack has a base damage of 50",
                0, 2, 0, 0, 3, 1);

        charmander = new FirePokemon("Charmander", 4, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack10, attack30, attack40, attack50)));
        abra = new PsychicPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(attack20, attack30, attack40, attack50)));
        anotherSquirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack30)));
        anotherTangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack20, attack40, attack50)));

        pikachu = new LightningPokemon("Pikachu", 25, 100,
                new ArrayList<>(Arrays.asList(attack20, attack40)));
        mankey = new FightingPokemon("Mankey", 56, 100,
                new ArrayList<>(Arrays.asList(attack30, attack40, attack50)));
        anotherAbra = new PsychicPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack30, attack40, attack50)));

        trainer = new Trainer(new ArrayList<>(Arrays.asList(anotherSquirtle, aGrassEnergy, charmander, abra, tangela,
                aFireEnergy, squirtle, anotherGrassEnergy, anotherTangela)));

        opponent = new Trainer(new ArrayList<>(Arrays.asList(pikachu, mankey, aLightningEnergy, aFightingEnergy,
                aWaterEnergy, anotherAbra)));
    }

    @Test
    public void getHandTest() {
        assertEquals(9, trainer.getHand().size());
    }

    @Test
    public void getBenchTest() {
        assertEquals(0, trainer.getBench().size());
    }

    @Test
    public void getActivePokemonTest() {
        assertNull(trainer.getActivePokemon());
        trainer.playCard(charmander);
        trainer.setActivePokemon();
        assertEquals(charmander, trainer.getActivePokemon());
    }

    @Test
    public void getPokemonEnergyTest() {
        assertEquals(new EnergyCounter(), trainer.getPokemonEnergy(charmander));
    }

    @Test
    public void getPokemonAttacksTest() {
        assertEquals(3, trainer.getPokemonAttacks(squirtle).size());
    }

    @Test
    public void playCardTest() {
        trainer.playCard(charmander);
        trainer.playCard(squirtle);
        trainer.playCard(abra);
        trainer.playCard(aFireEnergy);
        assertEquals(3, trainer.getBench().size());
        assertEquals(6, trainer.getHand().size());
        trainer.setActivePokemon();
        trainer.playCard(aFireEnergy);
        assertEquals(2, trainer.getBench().size());
        assertEquals(5, trainer.getHand().size());
        assertEquals(1, charmander.getFireEnergyAvailable());
        assertEquals(0, charmander.getGrassEnergyAvailable());
        assertEquals(trainer, squirtle.getTrainer());
    }

    @Test
    public void removeFromHandTest() {
        trainer.removeFromHand(tangela);
        assertFalse(trainer.getHand().contains(tangela));
        assertEquals(8, trainer.getHand().size());
    }

    @Test
    public void addToBenchTest() {
        trainer.addToBench(tangela);
        assertFalse(trainer.getHand().contains(tangela));
        assertTrue(trainer.getBench().contains(tangela));
        assertEquals(8, trainer.getHand().size());
        assertEquals(1, trainer.getBench().size());
    }

    @Test
    public void changeActivePokemonTest() {
        trainer.playCard(charmander);
        trainer.playCard(abra);
        trainer.changeActivePokemon(abra);
        assertEquals(abra, trainer.getActivePokemon());
        assertTrue(trainer.getBench().contains(charmander));
        assertFalse(trainer.getBench().contains(abra));
        assertEquals(1, trainer.getBench().size());
    }

    @Test
    public void setActivePokemonTest() {
        trainer.playCard(squirtle);
        trainer.setActivePokemon();
        assertEquals(squirtle, trainer.getActivePokemon());
        assertEquals(0, trainer.getBench().size());
    }

    @Test
    public void selectAttackTest() {
        trainer.playCard(squirtle);
        trainer.setActivePokemon();
        trainer.selectAttack(1);
        assertEquals(attack20, squirtle.getSelectedAttack());
    }

    @Test
    public void useAttackTest() {
        trainer.playCard(charmander);
        trainer.playCard(anotherSquirtle);
        trainer.playCard(anotherTangela);
        trainer.setActivePokemon();

        trainer.playCard(aFireEnergy);
        trainer.playCard(aGrassEnergy);
        trainer.playCard(anotherGrassEnergy);

        opponent.playCard(pikachu);
        opponent.playCard(mankey);
        opponent.playCard(anotherAbra);
        opponent.setActivePokemon();

        trainer.selectAttack(0);
        assertTrue(charmander.canAttack());
        trainer.useAttack(opponent);

        assertEquals(pikachu, opponent.getActivePokemon());
        assertEquals(70, pikachu.getHP());
        assertEquals(charmander, trainer.getActivePokemon());
        assertEquals(100, charmander.getHP());
    }

    @Test
    public void equalsTest() {
        ITrainer otherTrainer = new Trainer(new ArrayList<>(Arrays.asList(anotherSquirtle, aGrassEnergy, charmander, abra, tangela,
                aFireEnergy, squirtle, anotherGrassEnergy, anotherTangela)));
        assertEquals(otherTrainer, trainer);
    }
}