package cc3002.t1.general;

import cc3002.t1.abilities.*;
import cc3002.t1.energies.*;
import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.pokemon.basic.*;
import cc3002.t1.pokemon.stage1.Stage1FirePokemon;
import cc3002.t1.pokemon.stage2.Stage2FirePokemon;
import cc3002.t1.trainercards.ITrainerCard;
import cc3002.t1.trainercards.field.LuckyStadium;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class TrainerTest {

    private IEnergy aFightingEnergy, aFireEnergy, anotherFireEnergy, aGrassEnergy, aLightningEnergy, anotherGrassEnergy, aWaterEnergy, aPsychicEnergy, anotherWaterEnergy;
    private IAbility energyBurn1, energyBurn2, energyBurn3, electricShock10, electricShock20, electricShock30;
    private IPokemon charmander, squirtle, tangela, abra, anotherSquirtle, anotherTangela, pikachu, mankey, anotherAbra, charmeleon, charizard;
    private ITrainer trainer, opponent;
    private List<ICard> trainerDeck, opponentDeck, auxTrainerDeck, auxOpponentDeck;
    private ITrainerCard luckyStadium;
    private EnergyCounter count10, count20, count30;

    @Before
    public void setUp() {

        aFightingEnergy = new FightingEnergy();
        aFireEnergy = new FireEnergy();
        anotherFireEnergy = new FireEnergy();
        aGrassEnergy = new GrassEnergy();
        anotherGrassEnergy = new GrassEnergy();
        aLightningEnergy = new LightningEnergy();
        aPsychicEnergy = new PsychicEnergy();
        aWaterEnergy = new WaterEnergy();
        anotherWaterEnergy = new WaterEnergy();

        luckyStadium = new LuckyStadium("A Lucky Stadium Card", "This is a Lucky Stadium Card");

        count10 = new EnergyCounter();
        count10.setFireEnergy(2);
        count10.setWaterEnergy(1);

        count20 = new EnergyCounter();
        count20.setGrassEnergy(2);
        count20.setPsychicEnergy(1);

        count30 = new EnergyCounter();
        count30.setFireEnergy(1);
        count30.setGrassEnergy(2);

        electricShock10 = new ElectricShock("Electric Shock 10", 10, "This attack has a base damage of 10", count10);
        electricShock20 = new ElectricShock("Electric Shock 20", 20, "This attack has a base damage of 20", count20);
        electricShock30 = new ElectricShock("Electric Shock 30", 30, "This attack has a base damage of 30", count30);

        energyBurn1 = new EnergyBurn("Energy Burn 1", "This is an ability", count10);
        energyBurn2 = new EnergyBurn("Energy Burn 2", "This is an ability", count20);
        energyBurn3 = new EnergyBurn("Energy Burn 3", "This is an ability", count30);

        charmander = new BasicFirePokemon("Charmander", 4, 100,
                new ArrayList<>(Arrays.asList(electricShock30, energyBurn2)));
        charmeleon = new Stage1FirePokemon("Charmeleon", 5, 100,
                new ArrayList<>(Arrays.asList(electricShock20, electricShock30, energyBurn3, electricShock10)), 4);
        charizard = new Stage2FirePokemon("Charizard", 6, 100,
                new ArrayList<>(Arrays.asList(electricShock30, electricShock10, energyBurn1, energyBurn2)), 5);
        squirtle = new BasicWaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(electricShock10, electricShock20, energyBurn3)));
        tangela = new BasicGrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(electricShock10, electricShock30, energyBurn1, energyBurn3)));
        abra = new BasicPsychicPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(electricShock20, electricShock30, energyBurn3, energyBurn2)));
        anotherSquirtle = new BasicWaterPokemon("Squirtle", 7, 100,
                new ArrayList<>(Arrays.asList(electricShock10, electricShock30)));
        anotherTangela = new BasicGrassPokemon("Tangela", 114, 100,
                new ArrayList<>(Arrays.asList(electricShock20, energyBurn3, energyBurn1)));

        pikachu = new BasicLightningPokemon("Pikachu", 25, 100,
                new ArrayList<>(Arrays.asList(electricShock20, energyBurn1)));
        mankey = new BasicFightingPokemon("Mankey", 56, 100,
                new ArrayList<>(Arrays.asList(electricShock30, energyBurn2, energyBurn3)));
        anotherAbra = new BasicPsychicPokemon("Abra", 63, 100,
                new ArrayList<>(Arrays.asList(electricShock10, electricShock20, electricShock30, energyBurn1, energyBurn2)));

        auxTrainerDeck = new ArrayList<>(Arrays.asList(charmander, squirtle, anotherFireEnergy, tangela, abra, anotherSquirtle, anotherWaterEnergy,
                anotherTangela, aFireEnergy, charizard, aGrassEnergy, anotherGrassEnergy, aPsychicEnergy, aLightningEnergy, charmeleon));

        auxOpponentDeck = new ArrayList<>(Arrays.asList(pikachu, mankey, anotherAbra, aFightingEnergy, aWaterEnergy));

        trainerDeck = new ArrayList<>();
        opponentDeck = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (ICard card : auxTrainerDeck) {
                trainerDeck.add(card);
            }
        }

        for (int i = 0; i < 12; i++) {
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
    public void getPokemonAbilitiesTest() {
        assertEquals(3, trainer.getPokemonAbilities(squirtle).size());
    }

    @Test
    public void getSelectedPokemonTest() {
        assertNull(trainer.getSelectedPokemon());
    }

    @Test
    public void getActiveFieldCardTest() {
        assertNull(trainer.getActiveFieldCard());
    }

    @Test
    public void getActionSuccessfulTest() {
        assertFalse(trainer.getActionSuccessful());
    }

    @Test
    public void getAdversaryTest() {
        assertNull(trainer.getAdversary());
    }

    @Test
    public void setAdversaryTest() {
        trainer.setAdversary(opponent);
        assertEquals(opponent, trainer.getAdversary());
    }

    @Test
    public void setActionSuccessful() {
        trainer.setActionSuccessful(true);
        assertTrue(trainer.getActionSuccessful());
    }

    @Test
    public void setActiveFieldCard() {
        trainer.setActiveFieldCard(luckyStadium);
        assertEquals(luckyStadium, trainer.getActiveFieldCard());
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
        trainer.addToHand(charizard);
        trainer.addToHand(aFireEnergy);
        trainer.addToHand(charmeleon);

        trainer.playCard(charizard);
        trainer.playCard(charmeleon);

        assertEquals(0, trainer.getBench().size());
        assertEquals(5, trainer.getHand().size());

        trainer.playCard(charmander);
        trainer.playCard(squirtle);
        trainer.playCard(charmeleon);
        trainer.playCard(charizard);

        assertEquals(2, trainer.getBench().size());
        assertEquals(1, trainer.getHand().size());
        trainer.setActivePokemon();
        trainer.setSelectedPokemon(trainer.getActivePokemon());
        trainer.playCard(aFireEnergy);
        assertEquals(1, trainer.getBench().size());
        assertEquals(0, trainer.getHand().size());
        assertEquals(1, charizard.getFireEnergyAvailable());
        assertEquals(0, charizard.getGrassEnergyAvailable());
        assertEquals(trainer, charizard.getTrainer());
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
        trainer.addToHand(charmander);
        trainer.removeFromHand(tangela);
        assertFalse(trainer.getHand().contains(tangela));
        assertEquals(1, trainer.getHand().size());
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
    public void selectAbilityTest() {
        trainer.addToHand(squirtle);
        trainer.playCard(squirtle);
        trainer.setActivePokemon();
        trainer.selectAbility(1);
        assertEquals(electricShock20, squirtle.getSelectedAbility());
    }

    @Test
    public void useAbilityTest() {
        trainer.setAdversary(opponent);
        trainer.addToHand(charmander);
        trainer.addToHand(anotherSquirtle);
        trainer.addToHand(anotherTangela);
        trainer.playCard(charmander);
        trainer.playCard(anotherSquirtle);
        trainer.playCard(anotherTangela);
        trainer.setActivePokemon();
        trainer.setSelectedPokemon(trainer.getActivePokemon());
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

        trainer.selectAbility(0);
        assertTrue(charmander.canUseAbility());
        trainer.useAbility();

        assertEquals(pikachu, opponent.getActivePokemon());
        assertEquals(70, pikachu.getHP());
        assertEquals(charmander, trainer.getActivePokemon());
    }

    @Test
    public void setSelectedPokemonTest() {
        trainer.addToHand(charmander);
        trainer.addToHand(tangela);
        trainer.playCard(charmander);
        trainer.playCard(tangela);
        trainer.setSelectedPokemon(charmander);
        assertEquals(charmander, trainer.getSelectedPokemon());
        trainer.setSelectedPokemon(tangela);
        assertEquals(tangela, trainer.getSelectedPokemon());
    }

    @Test
    public void equalsTest() {
        List<ICard> newDeck = trainer.getDeck();
        assertEquals(new Trainer(newDeck), trainer);
    }

    @Test
    public void isOnFieldTest() {
        trainer.addToHand(charmander);
        trainer.addToHand(tangela);
        trainer.playCard(tangela);
        trainer.playCard(charmander);
        trainer.setActivePokemon();

        assertTrue(trainer.isOnField(tangela));
        assertTrue(trainer.isOnField(charmander));
    }

    @Test
    public void flipACoinTest() {
        Random rand = new Random(10);
        assertTrue(trainer.flipACoin(rand));
    }

    @Test
    public void removeFromFieldTest() {
        trainer.addToHand(squirtle);
        trainer.addToHand(charmander);
        trainer.addToHand(tangela);
        trainer.playCard(tangela);
        trainer.playCard(charmander);
        trainer.playCard(squirtle);
        trainer.setActivePokemon();

        assertEquals(tangela, trainer.getActivePokemon());
        trainer.removeFromField(tangela);
        assertEquals(charmander, trainer.getActivePokemon());
        trainer.removeFromField(squirtle);
        assertFalse(trainer.getBench().contains(squirtle));
    }

}