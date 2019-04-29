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

public class GrassPokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack20, attack30, attack40, attack50;
    private IPokemon tangela, squirtle, pikachu;


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

        tangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new WaterPokemon("Squirtle", 7, 90,
                new ArrayList<>(Arrays.asList(attack20, attack50)));
        pikachu = new LightningPokemon("Pikachu", 25, 100,
                new ArrayList<>(Arrays.asList(attack20, attack30, attack40, attack50)));

    }

    @Test
    public void constructorTest() {
        assertEquals(114, tangela.getID());
        assertEquals(100, tangela.getHP());
        assertEquals(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0)), tangela.getEnergies());
        assertEquals(0, tangela.getFightingEnergies());
        assertEquals(0, tangela.getFireEnergies());
        assertEquals(0, tangela.getGrassEnergies());
        assertEquals(0, tangela.getLightningEnergies());
        assertEquals(0, tangela.getPsychicEnergies());
        assertEquals(0, tangela.getWaterEnergies());
        assertEquals("Tangela", tangela.getCardName());
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), tangela.getAttacks());
        assertNull(tangela.getSelectedAttack());
    }

    @Test
    public void equalsTest() {
        IPokemon anotherTangela = new GrassPokemon("Tangela", 114, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
        assertEquals(anotherTangela, tangela);
        assertNotEquals(squirtle, tangela);
    }

    @Test
    public void addFightingEnergyTest() {
        tangela.addFightingEnergy();
        assertEquals(1, tangela.getFightingEnergies());
    }

    @Test
    public void addFireEnergyTest() {
        tangela.addFireEnergy();
        assertEquals(1, tangela.getFireEnergies());
    }

    @Test
    public void addGrassEnergyTest() {
        tangela.addGrassEnergy();
        assertEquals(1, tangela.getGrassEnergies());
    }

    @Test
    public void addLightningEnergyTest() {
        tangela.addLightningEnergy();
        assertEquals(1, tangela.getLightningEnergies());
    }

    @Test
    public void addPsychicEnergyTest() {
        tangela.addPsychicEnergy();
        assertEquals(1, tangela.getPsychicEnergies());
    }

    @Test
    public void addWaterEnergyTest() {
        tangela.addWaterEnergy();
        assertEquals(1, tangela.getWaterEnergies());
    }

    @Test
    public void selectAttackTest() {
        tangela.selectAttack(0);
        assertEquals(attack30, tangela.getSelectedAttack());
        tangela.selectAttack(3);
        assertEquals(attack30, tangela.getSelectedAttack());
    }

    @Test
    public void attackTest() {
        tangela.selectAttack(0);
        tangela.attack(squirtle);
        assertEquals(90, squirtle.getHP());

        tangela.addEnergyToPokemon(aFire);
        tangela.addEnergyToPokemon(aPsychic);
        tangela.addEnergyToPokemon(aWater);

        tangela.selectAttack(1);
        tangela.attack(squirtle);
        assertEquals(0, squirtle.getHP());

        tangela.selectAttack(0);
        tangela.attack(pikachu);
        assertEquals(70, pikachu.getHP());
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