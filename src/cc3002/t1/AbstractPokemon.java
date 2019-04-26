package cc3002.t1;

public abstract class AbstractPokemon implements ICard, IPokemon {

    private String name;

    protected AbstractPokemon(String name) {
        this.name = name;
    }
}
