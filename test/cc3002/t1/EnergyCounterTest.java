package cc3002.t1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnergyCounterTest {

    private EnergyCounter counter1, counter2, counter3;

    @Before
    public void SetUp() {

        counter1 = new EnergyCounter();
        counter1.setFightingEnergy(3);
        counter1.setWaterEnergy(2);

        counter2 = new EnergyCounter();
        counter2.setFightingEnergy(3);
        counter2.setWaterEnergy(2);

        counter3 = new EnergyCounter();
        counter3.setFightingEnergy(1);
        counter3.setFireEnergy(2);
        counter3.setGrassEnergy(6);
        counter3.setLightningEnergy(2);
        counter3.setPsychicEnergy(4);
        counter3.setWaterEnergy(2);
    }

    @Test
    public void setFightingEnergyTest() {
        counter1.setFightingEnergy(5);
        assertEquals(5, counter1.getFightingEnergy());
    }

    @Test
    public void setFireEnergyTest() {
        counter1.setFireEnergy(5);
        assertEquals(5, counter1.getFireEnergy());
    }

    @Test
    public void setGrassEnergyTest() {
        counter1.setGrassEnergy(5);
        assertEquals(5, counter1.getGrassEnergy());
    }

    @Test
    public void setLightningEnergyTest() {
        counter1.setLightningEnergy(5);
        assertEquals(5, counter1.getLightningEnergy());
    }

    @Test
    public void setPsychicEnergyTest() {
        counter1.setPsychicEnergy(5);
        assertEquals(5, counter1.getPsychicEnergy());
    }

    @Test
    public void setWaterEnergyTest() {
        counter1.setWaterEnergy(5);
        assertEquals(5, counter1.getWaterEnergy());
    }

    @Test
    public void getFightingEnergyTest() {
        assertEquals(1, counter3.getFightingEnergy());
    }

    @Test
    public void getFireEnergyTest() {
        assertEquals(2, counter3.getFireEnergy());
    }

    @Test
    public void getGrassEnergyTest() {
        assertEquals(6, counter3.getGrassEnergy());
    }

    @Test
    public void getLightningEnergyTest() {
        assertEquals(2, counter3.getLightningEnergy());
    }

    @Test
    public void getPsychicEnergyTest() {
        assertEquals(4, counter3.getPsychicEnergy());
    }

    @Test
    public void getWaterEnergyTest() {
        assertEquals(2, counter3.getWaterEnergy());
    }

    @Test
    public void addFightingEnergyTest() {
        counter1.addFightingEnergy();
        assertEquals(4, counter1.getFightingEnergy());
    }

    @Test
    public void addFireEnergyTest() {
        counter1.addFireEnergy();
        assertEquals(1, counter1.getFireEnergy());
    }

    @Test
    public void addGrassEnergyTest() {
        counter1.addGrassEnergy();
        assertEquals(1, counter1.getGrassEnergy());
    }

    @Test
    public void addLightningEnergyTest() {
        counter1.addLightningEnergy();
        assertEquals(1, counter1.getLightningEnergy());
    }

    @Test
    public void addPsychicEnergyTest() {
        counter1.addPsychicEnergy();
        assertEquals(1, counter1.getPsychicEnergy());
    }

    @Test
    public void addWaterEnergyTest() {
        counter1.addWaterEnergy();
        assertEquals(3, counter1.getWaterEnergy());
    }

    @Test
    public void greaterOrEqualTest() {
        assertTrue(counter1.greaterOrEqual(counter2));
        counter1.addFightingEnergy();
        assertTrue(counter1.greaterOrEqual(counter2));
        assertFalse(counter1.greaterOrEqual(counter3));
    }

    @Test
    public void equalsTest() {
        assertEquals(counter1, counter2);
        assertNotEquals(counter1, counter3);
    }

}

