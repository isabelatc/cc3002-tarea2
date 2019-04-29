package cc3002.t1.pokemon;

import cc3002.t1.*;
import cc3002.t1.energies.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FirePokemonTest {

    private IEnergy aFire, aPsychic, aWater;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon charmander, squirtle, tangela;


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

        charmander = new FirePokemon("Charmander", 4, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack30, attack40, attack50)));
    }

    @Test
    public void constructorTest() {
        assertEquals(4, charmander.getID());
        assertEquals(100, charmander.getHP());
        assertEquals(new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0)), charmander.getEnergies());
        assertEquals(0, charmander.getFightingEnergies());
        assertEquals(0, charmander.getFireEnergies());
        assertEquals(0, charmander.getGrassEnergies());
        assertEquals(0, charmander.getLightningEnergies());
        assertEquals(0, charmander.getPsychicEnergies());
        assertEquals(0, charmander.getWaterEnergies());
        assertEquals("Charmander", charmander.getCardName());
        assertEquals(new ArrayList<>(Arrays.asList(attack30, attack50)), charmander.getAttacks());
        assertNull(charmander.getSelectedAttack());
    }

    @Test
    public void equalsTest() {
        IPokemon anotherCharmander = new FirePokemon("Charmander", 4, 100, new ArrayList<>(Arrays.asList(attack30, attack50)));
        assertEquals(anotherCharmander, charmander);
        assertNotEquals(squirtle, charmander);
    }

    @Test
    public void addFightingEnergyTest() {
        charmander.addFightingEnergy();
        assertEquals(1, charmander.getFightingEnergies());
    }

    @Test
    public void addFireEnergyTest() {
        charmander.addFireEnergy();
        assertEquals(1, charmander.getFireEnergies());
    }

    @Test
    public void addGrassEnergyTest() {
        charmander.addGrassEnergy();
        assertEquals(1, charmander.getGrassEnergies());
    }

    @Test
    public void addLightningEnergyTest() {
        charmander.addLightningEnergy();
        assertEquals(1, charmander.getLightningEnergies());
    }

    @Test
    public void addPsychicEnergyTest() {
        charmander.addPsychicEnergy();
        assertEquals(1, charmander.getPsychicEnergies());
    }

    @Test
    public void addWaterEnergyTest() {
        charmander.addWaterEnergy();
        assertEquals(1, charmander.getWaterEnergies());
    }

    @Test
    public void selectAttackTest() {
        charmander.selectAttack(0);
        assertEquals(attack30, charmander.getSelectedAttack());
        charmander.selectAttack(3);
        assertEquals(attack30, charmander.getSelectedAttack());
    }

    @Test
    public void attackTest() {
        charmander.selectAttack(0);
        charmander.attack(squirtle);
        assertEquals(100, squirtle.getHP());

        charmander.addEnergyToPokemon(aFire);
        charmander.addEnergyToPokemon(aPsychic);
        charmander.addEnergyToPokemon(aWater);

        charmander.selectAttack(1);
        charmander.attack(squirtle);
        assertEquals(50, squirtle.getHP());

        charmander.selectAttack(0);
        charmander.attack(tangela);
        assertEquals(40, tangela.getHP());
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