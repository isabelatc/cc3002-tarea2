package cc3002.t1.abilities;


import cc3002.t1.general.EnergyCounter;
import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.pokemon.basic.BasicFirePokemon;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AbilitiesTest {

    private String abilityName, abilityDescription, attackName, attackDescription;
    private int attackBaseDamage;
    private IAbility ability, attack;
    private EnergyCounter thisEnergyCounter, sameEnergyCounter, differentEnergyCounter;
    private IPokemon pokemon;


    @Before
    public void setUp() {
        abilityName = "Energy Burn";
        abilityDescription = "This is an ability";

        attackName = "Electric Shock";
        attackBaseDamage = 40;
        attackDescription = "This is an attack";

        thisEnergyCounter = new EnergyCounter();
        thisEnergyCounter.setFightingEnergy(2);
        thisEnergyCounter.setFireEnergy(1);
        thisEnergyCounter.setPsychicEnergy(1);

        sameEnergyCounter = new EnergyCounter();
        sameEnergyCounter.setFightingEnergy(2);
        sameEnergyCounter.setFireEnergy(1);
        sameEnergyCounter.setPsychicEnergy(1);

        differentEnergyCounter = new EnergyCounter();
        differentEnergyCounter.setFightingEnergy(2);
        differentEnergyCounter.setFireEnergy(3);
        differentEnergyCounter.setWaterEnergy(4);

        attack = new ElectricShock(attackName, attackBaseDamage, attackDescription, thisEnergyCounter);
        ability = new EnergyBurn(abilityName, abilityDescription, thisEnergyCounter);

        pokemon = new BasicFirePokemon("Charmander", 4, 100,
                new ArrayList<>(Arrays.asList(attack, ability)));

    }

    @Test
    public void getAbilityNameTest() {
        assertEquals("Electric Shock", attack.getAbilityName());
        assertEquals("Energy Burn", ability.getAbilityName());
    }

    @Test
    public void getBaseDamageTest() {
        assertEquals(40, attack.getBaseDamage());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals("This is an attack", attack.getDescription());
        assertEquals("This is an ability", ability.getDescription());
    }

    @Test
    public void getEnergyCostsTest() {
        assertEquals(sameEnergyCounter, attack.getEnergyCosts());
        assertEquals(sameEnergyCounter, ability.getEnergyCosts());
        assertNotEquals(differentEnergyCounter, attack.getEnergyCosts());
        assertNotEquals(differentEnergyCounter, ability.getEnergyCosts());
    }

    @Test
    public void getFightingCostTest() {
        assertEquals(2, ability.getFightingCost());
        assertEquals(2, attack.getFightingCost());
    }

    @Test
    public void getFireCostTest() {
        assertEquals(1, ability.getFireCost());
        assertEquals(1, attack.getFireCost());
    }

    @Test
    public void getGrassCostTest() {
        assertEquals(0, ability.getGrassCost());
        assertEquals(0, attack.getGrassCost());
    }

    @Test
    public void getLightningCostTest() {
        assertEquals(0, ability.getLightningCost());
        assertEquals(0, attack.getLightningCost());
    }

    @Test
    public void getPsychicCostTest() {
        assertEquals(1, ability.getPsychicCost());
        assertEquals(1, attack.getPsychicCost());
    }

    @Test
    public void getWaterCostTest() {
        assertEquals(0, ability.getWaterCost());
        assertEquals(0, attack.getWaterCost());
    }

    @Test
    public void getPokemonTest() {
        assertNull(ability.getPokemon());
        assertNull(attack.getPokemon());
    }

    @Test
    public void setPokemonTest() {
        ability.setPokemon(pokemon);
        attack.setPokemon(pokemon);
        assertEquals(pokemon, ability.getPokemon());
        assertEquals(pokemon, attack.getPokemon());
    }

}
