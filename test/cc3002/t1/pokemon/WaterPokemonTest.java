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

public class WaterPokemonTest {
    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon squirtle, charmander, tangela;


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

        squirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        charmander = new FirePokemon("Charmander", 4, 90,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack30, attack40, attack50)));
    }

    @Test
    public void constructorTest() {
        assertEquals(7, squirtle.getID());
        assertEquals(100, squirtle.getHP());
        assertEquals(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0)), squirtle.getEnergies());
        assertEquals(0, squirtle.getFightingEnergies());
        assertEquals(0, squirtle.getFireEnergies());
        assertEquals(0, squirtle.getGrassEnergies());
        assertEquals(0, squirtle.getLightningEnergies());
        assertEquals(0, squirtle.getPsychicEnergies());
        assertEquals(0, squirtle.getWaterEnergies());
        assertEquals("Squirtle", squirtle.getCardName());
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), squirtle.getAttacks());
        assertNull(squirtle.getSelectedAttack());
    }

    @Test
    public void equalsTest() {
        IPokemon anotherSquirtle = new WaterPokemon("Squirtle", 7, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
        assertEquals(anotherSquirtle, squirtle);
        assertNotEquals(charmander, squirtle);
    }

    @Test
    public void addFightingEnergyTest() {
        squirtle.addFightingEnergy();
        assertEquals(1, squirtle.getFightingEnergies());
    }

    @Test
    public void addFireEnergyTest() {
        squirtle.addFireEnergy();
        assertEquals(1, squirtle.getFireEnergies());
    }

    @Test
    public void addGrassEnergyTest() {
        squirtle.addGrassEnergy();
        assertEquals(1, squirtle.getGrassEnergies());
    }

    @Test
    public void addLightningEnergyTest() {
        squirtle.addLightningEnergy();
        assertEquals(1, squirtle.getLightningEnergies());
    }

    @Test
    public void addPsychicEnergyTest() {
        squirtle.addPsychicEnergy();
        assertEquals(1, squirtle.getPsychicEnergies());
    }

    @Test
    public void addWaterEnergyTest() {
        squirtle.addWaterEnergy();
        assertEquals(1, squirtle.getWaterEnergies());
    }

    @Test
    public void selectAttackTest() {
        squirtle.selectAttack(0);
        assertEquals(attack30, squirtle.getSelectedAttack());
        squirtle.selectAttack(3);
        assertEquals(attack30, squirtle.getSelectedAttack());
    }

    @Test
    public void attackTest() {
        squirtle.selectAttack(0);
        squirtle.attack(charmander);
        assertEquals(90, charmander.getHP());

        squirtle.addEnergyToPokemon(aFire);
        squirtle.addEnergyToPokemon(aPsychic);
        squirtle.addEnergyToPokemon(aWater);

        squirtle.selectAttack(1);
        squirtle.attack(charmander);
        assertEquals(0, charmander.getHP());

        squirtle.selectAttack(1);
        squirtle.attack(tangela);
        assertEquals(80, tangela.getHP());
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