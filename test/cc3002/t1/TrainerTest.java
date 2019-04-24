package cc3002.t1;

import cc3002.t1.energies.FireEnergy;
import cc3002.t1.energies.GrassEnergy;
import cc3002.t1.energies.PsychicEnergy;
import cc3002.t1.pokemon.FirePokemon;
import cc3002.t1.pokemon.GrassPokemon;
import cc3002.t1.pokemon.WaterPokemon;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;

public class TrainerTest {

    private ICard aGrassEnergy, aFireEnergy, aPsychicEnergy, charmander, squirtle, tangela, abra, anotherAbra;
    private ITrainer trainer;
    private IAttack attack10, attack20, attack30, attack40, attack50;

    @Before
    public void setUp() throws Exception {

        aGrassEnergy = new GrassEnergy();
        aFireEnergy = new FireEnergy();
        aPsychicEnergy = new PsychicEnergy();

        attack10 = new Attack("Attack 10", 10, "This attack has a base damage of 10",
                0, 2, 0, 0, 0, 1);
        attack20 = new Attack("Attack 20", 20, "This attack has a base damage of 20",
                0, 0, 2, 0, 1, 0);
        attack30 = new Attack("Attack 30", 30, "This attack has a base damage of 30",
                0, 1, 2, 0, 0, 0,);
        attack40 = new Attack("Attack 40", 40, "This attack has a base damage of 40",
                0, 1, 2, 0, 1, 0,);
        attack50 = new Attack("Attack 50", 50, "This attack has a base damage of 50",
                0, 2, 0, 0, 3, 0);

        charmander = new FirePokemon("Charmander", 4, 100,
                new ArrayList<IAttack>Arrays.asList(attack30, attack50), 0, 0, 0, 0, 0, 0);
        squirtle = new WaterPokemon("Squirtle", 7, 100,
                new ArrayList<IAttack>Arrays.asList(attack10, attack20, attack50), 0, 0, 0, 0, 0, 0);
        tangela = new GrassPokemon("Tangela", 114, 100,
                new ArrayList<IAttack>Arrays.asList(attack10, attack30, attack40, attack50), 0, 0, 0, 0, 0, 0);
        abra = new GrassPokemon("Abra", 63, 100,
                new ArrayList<IAttack>Arrays.asList(attack20, attack30, attack40, attack50), 0, 0, 0, 0, 0, 0);
        anotherAbra = new GrassPokemon("Abra", 63, 100,
                new ArrayList<IAttack>Arrays.asList(attack10, attack20, attack30, attack40, attack50), 0, 0, 0, 0, 0, 0);

        trainer = new Trainer(charmander, new ArrayList<Pokemon>Arrays.asList(squirtle, abra),
                new ArrayList<ICard>Arrays.asList(aGrassEnergy, tangela, aFireEnergy, aPsychicEnergy, anotherAbra));
    }
}