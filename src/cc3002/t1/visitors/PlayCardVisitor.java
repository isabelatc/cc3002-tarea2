package cc3002.t1.visitors;

import cc3002.t1.general.ICard;
import cc3002.t1.energies.IEnergy;
import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.general.ITrainer;
import cc3002.t1.pokemon.basic.IBasicPokemon;
import cc3002.t1.pokemon.stage1.IStage1Pokemon;
import cc3002.t1.pokemon.stage2.IStage2Pokemon;
import cc3002.t1.trainercards.field.LuckyStadium;
import cc3002.t1.trainercards.support.ProfessorJuniper;
import cc3002.t1.trainercards.object.SuperScoopUp;

/**
 * Class of the visitor that implements what's needed for a card to be played.
 *
 * @author Isabela Tellechea Coluccio
 */
public class PlayCardVisitor extends AbstractCardsVisitor {

    /**
     * Execute the actions that happen when an energy card is played.
     *
     * @param energy The energy card being played.
     */
    public void visitEnergy(IEnergy energy) {
        if (energy.getTrainer().getSelectedPokemon() != null) {
            energy.getTrainer().getSelectedPokemon().addEnergyToPokemon(energy);
        }
    }

    @Override
    public void visitBasicPokemon(IBasicPokemon pokemon) {
        pokemon.getTrainer().addToBench(pokemon);
    }

    @Override
    public void visitNonBasicPokemon(IPokemon pokemon) {
        PlayableCardVisitor v = new PlayableCardVisitor();
        if (pokemon.canBePlayed(v)) {
            pokemon.replacePokemon(pokemon.getTrainer().getSelectedPokemon());
        }
    }

    @Override
    public void visitStage1Pokemon(IStage1Pokemon pokemon) {
        this.visitNonBasicPokemon(pokemon);
    }

    @Override
    public void visitStage2Pokemon(IStage2Pokemon pokemon) {
        this.visitNonBasicPokemon(pokemon);
    }

    /**
     * Execute the actions that happen when a Super Scoop Up card is played.
     *
     * @param objectCard The card being played.
     */
    public void visitSuperScoopUp(SuperScoopUp objectCard) {
        boolean heads = objectCard.getTrainer().flipACoin();
        if (heads) {
            ITrainer trainer = objectCard.getTrainer();
            IPokemon pokemon = trainer.getSelectedPokemon();
            if (trainer.isOnField(pokemon)) {
                trainer.addToHand(pokemon);
                trainer.removeFromField(pokemon);
                for (ICard card : pokemon.getAssociatedCards()) {
                    trainer.addToHand(card);
                    pokemon.getAssociatedCards().remove(card);
                }
            }
            trainer.addToDiscardPile(objectCard);
        }
    }

    /**
     * Execute the actions that happen when a Lucky Stadium card is played.
     *
     * @param fieldCard The card being played.
     */
    public void visitLuckyStadium(LuckyStadium fieldCard) {
        boolean heads = fieldCard.getTrainer().flipACoin();
        if (heads) fieldCard.getTrainer().drawFromDeck();
    }

    /**
     * Execute the actions that happen when a Professor Juniper card is played.
     *
     * @param supportCard The card being played.
     */
    public void visitProfessorJuniper(ProfessorJuniper supportCard) {
        ITrainer trainer = supportCard.getTrainer();
        while (trainer.getHand().size() > 0) {
            ICard card = trainer.getHand().get(0);
            trainer.addToDiscardPile(card);
            trainer.removeFromHand(card);
        }
        for (int i = 0; i < 7; i++) trainer.drawFromDeck();
    }

}
