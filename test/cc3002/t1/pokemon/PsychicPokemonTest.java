package cc3002.t1.pokemon;

import cc3002.t1.Attack;
import cc3002.t1.IAttack;
import cc3002.t1.IEnergy;
import cc3002.t1.IPokemon;
import cc3002.t1.energies.FireEnergy;
import cc3002.t1.energies.PsychicEnergy;
import cc3002.t1.energies.WaterEnergy;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PsychicPokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon abra, squirtle, otherAbra;


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

        abra = new PsychicPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack50)));
        otherAbra = new PsychicPokemon("Abra", 63, 90,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
    }

    @Test
    public void constructorTest() {
        assertEquals(63, abra.getID());
        assertEquals(100, abra.getHP());
        assertEquals(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0)), abra.getEnergies());
        assertEquals(0, abra.getFightingEnergies());
        assertEquals(0, abra.getFireEnergies());
        assertEquals(0, abra.getGrassEnergies());
        assertEquals(0, abra.getLightningEnergies());
        assertEquals(0, abra.getPsychicEnergies());
        assertEquals(0, abra.getWaterEnergies());
        assertEquals("Abra", abra.getCardName());
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), abra.getAttacks());
        assertNull(abra.getSelectedAttack());
    }

    @Test
    public void equalsTest() {
        IPokemon anotherAbra = new PsychicPokemon("Abra", 63, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
        assertEquals(anotherAbra, abra);
        assertNotEquals(otherAbra, abra);
    }

    @Test
    public void addFightingEnergyTest() {
        abra.addFightingEnergy();
        assertEquals(1, abra.getFightingEnergies());
    }

    @Test
    public void addFireEnergyTest() {
        abra.addFireEnergy();
        assertEquals(1, abra.getFireEnergies());
    }

    @Test
    public void addGrassEnergyTest() {
        abra.addGrassEnergy();
        assertEquals(1, abra.getGrassEnergies());
    }

    @Test
    public void addLightningEnergyTest() {
        abra.addLightningEnergy();
        assertEquals(1, abra.getLightningEnergies());
    }

    @Test
    public void addPsychicEnergyTest() {
        abra.addPsychicEnergy();
        assertEquals(1, abra.getPsychicEnergies());
    }

    @Test
    public void addWaterEnergyTest() {
        abra.addWaterEnergy();
        assertEquals(1, abra.getWaterEnergies());
    }

    @Test
    public void selectAttackTest() {
        abra.selectAttack(0);
        assertEquals(attack30, abra.getSelectedAttack());
        abra.selectAttack(3);
        assertEquals(attack30, abra.getSelectedAttack());
    }

    @Test
    public void attackTest() {
        abra.selectAttack(0);
        abra.attack(otherAbra);
        assertEquals(90, otherAbra.getHP());

        abra.addEnergyToPokemon(aFire);
        abra.addEnergyToPokemon(aPsychic);
        abra.addEnergyToPokemon(aWater);

        abra.selectAttack(1);
        abra.attack(otherAbra);
        assertEquals(0, otherAbra.getHP());

        abra.selectAttack(0);
        abra.attack(squirtle);
        assertEquals(70, squirtle.getHP());
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