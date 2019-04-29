package cc3002.t1;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AttackTest {

    private String attackName, attackDescription;
    private int attackBaseDamage, fightingCost, fireCost, grassCost, lightningCost, psychicCost, waterCost;
    private IAttack attack;


    @Before
    public void setUp() {
        attackName = "An attack";
        attackBaseDamage = 40;
        attackDescription = "This is an attack";
        fightingCost = 2;
        fireCost = 1;
        grassCost = 0;
        lightningCost = 0;
        psychicCost = 1;
        waterCost = 0;
        attack = new Attack(attackName, attackBaseDamage, attackDescription, fightingCost, fireCost, grassCost, lightningCost, psychicCost, waterCost);
    }

    @Test
    public void getAttackNameTest() { assertEquals("An attack", attack.getAttackName());
    }

    @Test
    public void getBaseDamageTest() {
        assertEquals(40, attack.getBaseDamage());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("This is an attack", attack.getDescription());
    }

    @Test
    public void getEnergyCostsTest() {
        assertEquals(new ArrayList<>(Arrays.asList(2, 1, 0, 0, 1, 0)), attack.getEnergyCosts());
        assertNotEquals(new ArrayList<>(Arrays.asList(1, 2, 0, 0, 1, 0)), attack.getEnergyCosts());
    }

    @Test
    public void getFightingCostTest() {
        assertEquals(2, attack.getFightingCost());
    }

    @Test
    public void getFireCostTest() {
        assertEquals(1, attack.getFireCost());
    }

    @Test
    public void getGrassCostTest() {
        assertEquals(0, attack.getGrassCost());
    }

    @Test
    public void getLightningCostTest() {
        assertEquals(0, attack.getLightningCost());
    }

    @Test
    public void getPsychicCostTest() {
        assertEquals(1, attack.getPsychicCost());
    }

    @Test
    public void getWaterCostTest() {
        assertEquals(0, attack.getWaterCost());
    }
}
