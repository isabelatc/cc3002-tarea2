package cc3002.t1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractPokemon implements ICard, IPokemon {

    private String name;
    private int id, hp;
    private List<IAttack> attackList;
    private int fightingEnergies, fireEnergies, grassEnergies, lightningEnergies, psychicEnergies, waterEnergies;
    private IAttack selectedAttack;

    /**
     * The constructor of AbstractPokemon. It cannot create an instance of the class itself, but it is used by all of its subclasses.
     * Initially, some of its parameters are empty, because they will be added during the game.
     *
     * @param name The name of the Pokémon.
     * @param id The identification number of the Pokémon (according to the Pokédex).
     * @param hp The initial hit points of the Pokémon.
     * @param attackList The list of attacks the Pokémon can use.
     */
    protected AbstractPokemon(String name, int id, int hp, ArrayList<IAttack> attackList) {
        this.name = name;
        this.id = id;
        this.hp = hp;
        this.attackList = attackList;
        this.fightingEnergies = 0;
        this.fireEnergies = 0;
        this.grassEnergies = 0;
        this.lightningEnergies = 0;
        this.psychicEnergies = 0;
        this.waterEnergies = 0;
        this.selectedAttack = null;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getHP() {
        return hp;
    }

    @Override
    public List<Integer> getEnergies() {
        return new ArrayList<>(Arrays.asList(fightingEnergies, fireEnergies, grassEnergies, lightningEnergies, psychicEnergies, waterEnergies));
    }

    @Override
    public int getFightingEnergies() { return fightingEnergies; }

    @Override
    public int getFireEnergies() { return fireEnergies; }

    @Override
    public int getGrassEnergies() { return grassEnergies; }

    @Override
    public int getLightningEnergies() { return lightningEnergies; }

    @Override
    public int getPsychicEnergies() { return psychicEnergies; }

    @Override
    public int getWaterEnergies() { return waterEnergies; }

    @Override
    public String getCardName() { return name; }

    @Override
    public List<IAttack> getAttacks() { return attackList; }

    @Override
    public IAttack getSelectedAttack() { return selectedAttack; }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public void addFightingEnergy() { fightingEnergies++; }

    @Override
    public void addFireEnergy() { fireEnergies++; }

    @Override
    public void addGrassEnergy() { grassEnergies++; }

    @Override
    public void addLightningEnergy() { lightningEnergies++; }

    @Override
    public void addPsychicEnergy() { psychicEnergies++; }

    @Override
    public void addWaterEnergy() { waterEnergies++; }

    @Override
    public void selectAttack(int index) { selectedAttack = attackList.get(index); }

    @Override
    public abstract void attack(IPokemon other);

    @Override
    public abstract void attackedByFightingPokemon(IAttack attack);

    @Override
    public abstract void attackedByFirePokemon(IAttack attack);

    @Override
    public abstract void attackedByGrassPokemon(IAttack attack);

    @Override
    public abstract void attackedByLightningPokemon(IAttack attack);

    @Override
    public abstract void attackedByPsychicPokemon(IAttack attack);

    @Override
    public abstract void attackedByWaterPokemon(IAttack attack);

    @Override
    public void receivesNeutralAttack(IAttack attack) {
        hp -= attack.getBaseDamage();
    }

    @Override
    public void receivesStrengthenedAttack(IAttack attack) {
        hp -= 2*(attack.getBaseDamage());
    }

    @Override
    public void receivesWeakenedAttack(IAttack attack) {
        hp -= (attack.getBaseDamage() - 30);
    }
}
