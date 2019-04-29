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

public class LightningPokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon pikachu, squirtle, tangela;


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

        pikachu = new LightningPokemon("Pikachu", 25, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new WaterPokemon("Squirtle", 7, 90,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack30, attack40, attack50)));
    }

    @Test
    public void constructorTest() {
        assertEquals(25, pikachu.getID());
        assertEquals(100, pikachu.getHP());
        assertEquals(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0)), pikachu.getEnergies());
        assertEquals(0, pikachu.getFightingEnergies());
        assertEquals(0, pikachu.getFireEnergies());
        assertEquals(0, pikachu.getGrassEnergies());
        assertEquals(0, pikachu.getLightningEnergies());
        assertEquals(0, pikachu.getPsychicEnergies());
        assertEquals(0, pikachu.getWaterEnergies());
        assertEquals("Pikachu", pikachu.getCardName());
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), pikachu.getAttacks());
        assertNull(pikachu.getSelectedAttack());
    }

    @Test
    public void equalsTest() {
        IPokemon anotherPikachu = new LightningPokemon("Pikachu", 25, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
        assertEquals(anotherPikachu, pikachu);
        assertNotEquals(squirtle, pikachu);
    }

    @Test
    public void addFightingEnergyTest() {
        pikachu.addFightingEnergy();
        assertEquals(1, pikachu.getFightingEnergies());
    }

    @Test
    public void addFireEnergyTest() {
        pikachu.addFireEnergy();
        assertEquals(1, pikachu.getFireEnergies());
    }

    @Test
    public void addGrassEnergyTest() {
        pikachu.addGrassEnergy();
        assertEquals(1, pikachu.getGrassEnergies());
    }

    @Test
    public void addLightningEnergyTest() {
        pikachu.addLightningEnergy();
        assertEquals(1, pikachu.getLightningEnergies());
    }

    @Test
    public void addPsychicEnergyTest() {
        pikachu.addPsychicEnergy();
        assertEquals(1, pikachu.getPsychicEnergies());
    }

    @Test
    public void addWaterEnergyTest() {
        pikachu.addWaterEnergy();
        assertEquals(1, pikachu.getWaterEnergies());
    }

    @Test
    public void selectAttackTest() {
        pikachu.selectAttack(0);
        assertEquals(attack30, pikachu.getSelectedAttack());
        pikachu.selectAttack(3);
        assertEquals(attack30, pikachu.getSelectedAttack());
    }

    @Test
    public void attackTest() {
        pikachu.selectAttack(0);
        pikachu.attack(squirtle);
        assertEquals(90, squirtle.getHP());

        pikachu.addEnergyToPokemon(aFire);
        pikachu.addEnergyToPokemon(aPsychic);
        pikachu.addEnergyToPokemon(aWater);

        pikachu.selectAttack(1);
        pikachu.attack(squirtle);
        assertEquals(0, squirtle.getHP());

        pikachu.selectAttack(0);
        pikachu.attack(tangela);
        assertEquals(70, tangela.getHP());
    }

    @Test
    public void attackedByFightingPokemon() {
        pikachu.attackedByFightingPokemon(attack40);
        assertEquals(20, pikachu.getHP());
    }

    @Test
    public void attackedByFirePokemon() {
        pikachu.attackedByFirePokemon(attack40);
        assertEquals(60, pikachu.getHP());
    }

    @Test
    public void attackedByGrassPokemon() {
        pikachu.attackedByGrassPokemon(attack40);
        assertEquals(60, pikachu.getHP());
    }

    @Test
    public void attackedByLightningPokemon() {
        pikachu.attackedByLightningPokemon(attack40);
        assertEquals(60, pikachu.getHP());
    }

    @Test
    public void attackedByPsychicPokemon() {
        pikachu.attackedByPsychicPokemon(attack40);
        assertEquals(60, pikachu.getHP());
    }

    @Test
    public void attackedByWaterPokemon() {
        pikachu.attackedByWaterPokemon(attack40);
        assertEquals(60, pikachu.getHP());
    }

}