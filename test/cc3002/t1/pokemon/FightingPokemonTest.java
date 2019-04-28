package cc3002.t1.pokemon;

import cc3002.t1.*;
import cc3002.t1.energies.*;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;

public class FightingPokemonTest {

    private IEnergy aFighting, aFire, aGrass, aLightning, aPsychic, aWater, anotherFighting, anotherWater;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon charmander, squirtle, tangela, abra, anotherSquirtle, anotherTangela, anotherAbra;
    private ITrainer trainer;


    @Before
    public void setUp() {

        aFighting = new FightingEnergy();
        aFire = new FireEnergy();
        aGrass = new GrassEnergy();
        aLightning = new LightningEnergy();
        aPsychic = new PsychicEnergy();
        aWater = new WaterEnergy();
        anotherFighting = new FightingEnergy();
        anotherWater = new WaterEnergy();

        attack10 = new Attack("Attack 10", 10, "This attack has a base damage of 10",
                0, 2, 0, 0, 0, 1);
        attack20 = new Attack("Attack 20", 20, "This attack has a base damage of 20",
                0, 0, 2, 0, 1, 0);
        attack30 = new Attack("Attack 30", 30, "This attack has a base damage of 30",
                0, 1, 2, 0, 0, 0);
        attack40 = new Attack("Attack 40", 40, "This attack has a base damage of 40",
                0, 1, 2, 0, 1, 0);
        attack50 = new Attack("Attack 50", 50, "This attack has a base damage of 50",
                0, 2, 0, 0, 3, 0);

        charmander = new FirePokemon("Charmander", 4, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack10, attack30, attack40, attack50)));
        abra = new GrassPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(attack20, attack30, attack40, attack50)));
        anotherSquirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack30)));
        anotherTangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack20, attack40, attack50)));
        anotherAbra = new GrassPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack30, attack40, attack50)));

        trainer = new Trainer(new ArrayList<>(Arrays.asList(anotherSquirtle, aGrass, charmander, abra, tangela, aFire, squirtle, aPsychic, anotherAbra, anotherTangela)));
    }


}