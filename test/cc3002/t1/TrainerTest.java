package cc3002.t1;

import cc3002.t1.abilities.Attack;
import cc3002.t1.abilities.IAttack;
import cc3002.t1.energies.*;
import cc3002.t1.general.EnergyCounter;
import cc3002.t1.general.ICard;
import cc3002.t1.general.ITrainer;
import cc3002.t1.general.Trainer;
import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.pokemon.basic.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TrainerTest {

    private IEnergy aFightingEnergy, aFireEnergy, aGrassEnergy, aLightningEnergy, anotherGrassEnergy, aWaterEnergy, aPsychicEnergy;
    private IAttack attack10, attack20, attack30, attack40, attack50;
    private IPokemon charmander, squirtle, tangela, abra, anotherSquirtle, anotherTangela, pikachu, mankey, anotherAbra;
    private ITrainer trainer, opponent;
    private List<ICard> trainerDeck, opponentDeck, auxTrainerDeck, auxOpponentDeck;

    @Before
    public void setUp() {

        aFightingEnergy = new FightingEnergy();
        aFireEnergy = new FireEnergy();
        aGrassEnergy = new GrassEnergy();
        anotherGrassEnergy = new GrassEnergy();
        aLightningEnergy = new LightningEnergy();
        aPsychicEnergy = new PsychicEnergy();
        aWaterEnergy = new WaterEnergy();

        attack10 = new Attack("Attack 10", 10, "This attack has a base damage of 10",
                0, 2, 0, 0, 0, 1);
        attack20 = new Attack("Attack 20", 20, "This attack has a base damage of 20",
                0, 0, 2, 0, 1, 0);
        attack30 = new Attack("Attack 30", 30, "This attack has a base damage of 30",
                0, 1, 2, 0, 0, 0);
        attack40 = new Attack("Attack 40", 40, "This attack has a base damage of 40",
                0, 1, 2, 0, 1, 0);
        attack50 = new Attack("Attack 50", 50, "This attack has a base damage of 50",
                0, 2, 0, 0, 3, 1);

        charmander = new BasicFirePokemon("Charmander", 4, 100,
                new ArrayList<>(Arrays.asList(attack30, attack50)));
        squirtle = new BasicWaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack50)));
        tangela = new BasicGrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack10, attack30, attack40, attack50)));
        abra = new BasicPsychicPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(attack20, attack30, attack40, attack50)));
        anotherSquirtle = new BasicWaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(attack10, attack30)));
        anotherTangela = new BasicGrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(attack20, attack40, attack50)));

        pikachu = new BasicLightningPokemon("Pikachu", 25, 100,
                new ArrayList<>(Arrays.asList(attack20, attack40)));
        mankey = new BasicFightingPokemon("Mankey", 56, 100,
                new ArrayList<>(Arrays.asList(attack30, attack40, attack50)));
        anotherAbra = new BasicPsychicPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(attack10, attack20, attack30, attack40, attack50)));

        auxTrainerDeck = new ArrayList<>(Arrays.asList(charmander, squirtle, tangela, abra, anotherSquirtle,
                anotherTangela, aFireEnergy, aGrassEnergy, anotherGrassEnergy, aPsychicEnergy));

        auxOpponentDeck = new ArrayList<>(Arrays.asList(pikachu, mankey, anotherAbra, aFightingEnergy, aLightningEnergy, aWaterEnergy));

        trainerDeck = new ArrayList<>();
        opponentDeck = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            for (ICard card : auxTrainerDeck) {
                trainerDeck.add(card);
            }
        }

        for (int i = 0; i < 10; i++) {
            for (ICard card : auxOpponentDeck) {
                opponentDeck.add(card);
            }
        }

        Collections.shuffle(trainerDeck);
        Collections.shuffle(opponentDeck);

        trainer = new Trainer(trainerDeck);
        opponent = new Trainer(opponentDeck);

    }

    @Test
    public void getHandTest() {
        assertEquals(0, trainer.getHand().size());
    }

    @Test
    public void getBenchTest() {
        assertEquals(0, trainer.getBench().size());
    }

    @Test
    public void getDiscardPileTest() {
        assertEquals(0, trainer.getDiscardPile().size());
    }

    @Test
    public void getDeckTest() {
        assertEquals(60, trainer.getDeck().size());
    }

    @Test
    public void getPrizesTest() {
        assertEquals(0, trainer.getPrizes().size());
    }

    @Test
    public void getActivePokemonTest() {
        assertNull(trainer.getActivePokemon());
        trainer.addToHand(charmander);
        trainer.playCard(charmander);
        trainer.setActivePokemon();
        assertEquals(charmander, trainer.getActivePokemon());
    }

    @Test
    public void getPokemonEnergyTest() {
        assertEquals(new EnergyCounter(), trainer.getPokemonEnergy(charmander));
    }

    @Test
    public void getPokemonAttacksTest() {
        assertEquals(3, trainer.getPokemonAttacks(squirtle).size());
    }

    @Test
    public void getSelectedPokemonTest() {
        assertNull(trainer.getSelectedPokemon());
    }

    @Test
    public void drawFromDeckTest() {
        trainer.drawFromDeck();
        assertEquals(59, trainer.getDeck().size());
        assertEquals(1, trainer.getHand().size());
    }

    @Test
    public void playCardTest() {
        trainer.addToHand(charmander);
        trainer.addToHand(squirtle);
        trainer.addToHand(abra);
        trainer.addToHand(aFireEnergy);

        trainer.playCard(charmander);
        trainer.playCard(squirtle);
        trainer.playCard(abra);
        trainer.playCard(aFireEnergy);

        assertEquals(3, trainer.getBench().size());
        assertEquals(1, trainer.getHand().size());
        trainer.setActivePokemon();
        trainer.playCard(aFireEnergy);
        assertEquals(2, trainer.getBench().size());
        assertEquals(0, trainer.getHand().size());
        assertEquals(1, charmander.getFireEnergyAvailable());
        assertEquals(0, charmander.getGrassEnergyAvailable());
        assertEquals(trainer, charmander.getTrainer());
        assertEquals(trainer, squirtle.getTrainer());
    }

    @Test
    public void addToHandTest() {
        trainer.addToHand(charmander);
        assertEquals(1, trainer.getHand().size());
    }

    @Test
    public void removeFromHandTest() {
        trainer.addToHand(tangela);
        trainer.removeFromHand(tangela);
        assertFalse(trainer.getHand().contains(tangela));
        assertEquals(0, trainer.getHand().size());
    }

    @Test
    public void addToBenchTest() {
        trainer.addToHand(tangela);
        trainer.addToBench(tangela);
        assertFalse(trainer.getHand().contains(tangela));
        assertTrue(trainer.getBench().contains(tangela));
        assertEquals(0, trainer.getHand().size());
        assertEquals(1, trainer.getBench().size());
    }

    @Test
    public void addToDiscardPileTest() {
        trainer.addToDiscardPile(charmander);
        assertEquals(1, trainer.getDiscardPile().size());
    }

    @Test
    public void changeActivePokemonTest() {
        trainer.addToHand(charmander);
        trainer.addToHand(abra);

        trainer.playCard(charmander);
        trainer.playCard(abra);
        trainer.changeActivePokemon(abra);

        assertEquals(abra, trainer.getActivePokemon());
        assertTrue(trainer.getBench().contains(charmander));
        assertFalse(trainer.getBench().contains(abra));
        assertEquals(1, trainer.getBench().size());
    }

    @Test
    public void setActivePokemonTest() {
        trainer.addToHand(squirtle);
        trainer.playCard(squirtle);
        trainer.setActivePokemon();
        assertEquals(squirtle, trainer.getActivePokemon());
        assertEquals(0, trainer.getBench().size());
    }

    @Test
    public void selectAttackTest() {
        trainer.addToHand(squirtle);
        trainer.playCard(squirtle);
        trainer.setActivePokemon();
        trainer.selectAttack(1);
        assertEquals(attack20, squirtle.getSelectedAttack());
    }

    @Test
    public void useAttackTest() {
        trainer.addToHand(charmander);
        trainer.addToHand(anotherSquirtle);
        trainer.addToHand(anotherTangela);
        trainer.playCard(charmander);
        trainer.playCard(anotherSquirtle);
        trainer.playCard(anotherTangela);
        trainer.setActivePokemon();

        trainer.addToHand(aFireEnergy);
        trainer.addToHand(aGrassEnergy);
        trainer.addToHand(anotherGrassEnergy);
        trainer.playCard(aFireEnergy);
        trainer.playCard(aGrassEnergy);
        trainer.playCard(anotherGrassEnergy);

        opponent.addToHand(pikachu);
        opponent.addToHand(mankey);
        opponent.addToHand(anotherAbra);
        opponent.playCard(pikachu);
        opponent.playCard(mankey);
        opponent.playCard(anotherAbra);
        opponent.setActivePokemon();

        trainer.selectAttack(0);
        assertTrue(charmander.canAttack());
        trainer.useAttack(opponent);

        assertEquals(pikachu, opponent.getActivePokemon());
        assertEquals(70, pikachu.getHP());
        assertEquals(charmander, trainer.getActivePokemon());
        assertEquals(100, charmander.getHP());
    }

    @Test
    public void setSelectedPokemonTest() {
        trainer.addToHand(charmander);
        trainer.addToHand(tangela);
        trainer.playCard(charmander);
        trainer.playCard(tangela);
        trainer.setActivePokemon();
        assertEquals(charmander, trainer.getSelectedPokemon());
        trainer.setSelectedPokemon(tangela);
        assertEquals(tangela, trainer.setSelectedPokemon());
    }

    @Test
    public void equalsTest() {
        List<ICard> newDeck = trainer.getDeck();
        assertEquals(new Trainer(newDeck), trainer);
    }
}