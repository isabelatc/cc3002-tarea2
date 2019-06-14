package cc3002.t1.visitors;

import cc3002.t1.pokemon.IPokemon;
import cc3002.t1.pokemon.basic.IBasicPokemon;
import cc3002.t1.pokemon.stage1.IStage1Pokemon;
import cc3002.t1.pokemon.stage2.IStage2Pokemon;

public abstract class Visitor {

    public abstract void visitBasicPokemon(IBasicPokemon pokemon);

    public abstract void visitNonBasicPokemon(IPokemon pokemon);

    public abstract void visitStage1Pokemon(IStage1Pokemon pokemon);

    public abstract void visitStage2Pokemon(IStage2Pokemon pokemon);

}
