package cc3002.t1.visitors;

import cc3002.t1.general.ICard;
import cc3002.t1.energies.IEnergy;
import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.general.ITrainer;
import cc3002.t1.pokemon.basic.IBasicPokemon;
import cc3002.t1.pokemon.stage1.IStage1Pokemon;
import cc3002.t1.pokemon.stage2.IStage2Pokemon;
import cc3002.t1.trainercards.LuckyStadium;
import cc3002.t1.trainercards.ProfessorJuniper;
import cc3002.t1.trainercards.SuperScoopUp;

public class PlayCardVisitor extends Visitor {

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
        PlayableVisitor v = new PlayableVisitor();
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

    public void visitSuperScoopUp(SuperScoopUp objectCard) {
        boolean heads = objectCard.getTrainer().flipACoin();
        if (heads) {
            ITrainer trainer = objectCard.getTrainer();
            IPokemon pokemon = trainer.getSelectedPokemon();
            if (trainer.isOnField(pokemon)) {
                trainer.getHand().add(pokemon);
                trainer.removeFromField(pokemon);
                for (ICard card : pokemon.getAssociatedCards()) {
                    trainer.getHand().add(card);
                    pokemon.getAssociatedCards().remove(card);
                }
            }
        }
    }

    public void visitLuckyStadium(LuckyStadium fieldCard) {
        boolean heads = fieldCard.getTrainer().flipACoin();
        if (heads) fieldCard.getTrainer().drawFromDeck();
    }

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
