package cc3002.t1.pokemon;

import cc3002.t1.*;
import cc3002.t1.energies.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FightingPokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack20, attack30, attack40, attack50;
    private IPokemon mankey, squirtle, pikachu;


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

        mankey = new FightingPokemon("Mankey", 56, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack20, attack50)));
        pikachu = new LightningPokemon("Pikachu", 25, 50,
                new ArrayList<>(Arrays.asList(attack20, attack30, attack40, attack50)));

    }

    @Test
    public void constructorTest() {
        assertEquals(56, mankey.getID());
        assertEquals(100, mankey.getHP());
        assertEquals(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0)), mankey.getEnergies());
        assertEquals(0, mankey.getFightingEnergies());
        assertEquals(0, mankey.getFireEnergies());
        assertEquals(0, mankey.getGrassEnergies());
        assertEquals(0, mankey.getLightningEnergies());
        assertEquals(0, mankey.getPsychicEnergies());
        assertEquals(0, mankey.getWaterEnergies());
        assertEquals("Mankey", mankey.getCardName());
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), mankey.getAttacks());
        assertNull(mankey.getSelectedAttack());
    }

    @Test
    public void equalsTest() {
        IPokemon anotherMankey = new FightingPokemon("Mankey", 56, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
        assertEquals(anotherMankey, mankey);
        assertNotEquals(squirtle, mankey);
    }

    @Test
    public void addFightingEnergyTest() {
        mankey.addFightingEnergy();
        assertEquals(1, mankey.getFightingEnergies());
    }

    @Test
    public void addFireEnergyTest() {
        mankey.addFireEnergy();
        assertEquals(1, mankey.getFireEnergies());
    }

    @Test
    public void addGrassEnergyTest() {
        mankey.addGrassEnergy();
        assertEquals(1, mankey.getGrassEnergies());
    }

    @Test
    public void addLightningEnergyTest() {
        mankey.addLightningEnergy();
        assertEquals(1, mankey.getLightningEnergies());
    }

    @Test
    public void addPsychicEnergyTest() {
        mankey.addPsychicEnergy();
        assertEquals(1, mankey.getPsychicEnergies());
    }

    @Test
    public void addWaterEnergyTest() {
        mankey.addWaterEnergy();
        assertEquals(1, mankey.getWaterEnergies());
    }

    @Test
    public void selectAttackTest() {
        mankey.selectAttack(0);
        assertEquals(attack30, mankey.getSelectedAttack());
        mankey.selectAttack(3);
        assertEquals(attack30, mankey.getSelectedAttack());
    }

    @Test
    public void attackTest() {
        mankey.selectAttack(0);
        mankey.attack(squirtle);
        assertEquals(100, squirtle.getHP());

        mankey.addEnergyToPokemon(aFire);
        mankey.addEnergyToPokemon(aPsychic);
        mankey.addEnergyToPokemon(aWater);

        mankey.selectAttack(1);
        mankey.attack(squirtle);
        assertEquals(80, squirtle.getHP());

        mankey.selectAttack(0);
        mankey.attack(pikachu);
        assertEquals(0, pikachu.getHP());
    }

    @Test
    public void attackedByFightingPokemon() {
        mankey.attackedByFightingPokemon(attack40);
        assertEquals(60, mankey.getHP());
    }

    @Test
    public void attackedByFirePokemon() {
        mankey.attackedByFirePokemon(attack40);
        assertEquals(60, mankey.getHP());
    }

    @Test
    public void attackedByGrassPokemon() {
        mankey.attackedByGrassPokemon(attack40);
        assertEquals(20, mankey.getHP());
    }

    @Test
    public void attackedByLightningPokemon() {
        mankey.attackedByLightningPokemon(attack40);
        assertEquals(60, mankey.getHP());
    }

    @Test
    public void attackedByPsychicPokemon() {
        mankey.attackedByPsychicPokemon(attack40);
        assertEquals(20, mankey.getHP());
    }

    @Test
    public void attackedByWaterPokemon() {
        mankey.attackedByWaterPokemon(attack40);
        assertEquals(60, mankey.getHP());
    }

}