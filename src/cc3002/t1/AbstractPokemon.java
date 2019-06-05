package cc3002.t1;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPokemon implements ICard, IPokemon {

    private String name;
    private int id, hp;
    private List<IAttack> attackList;
    private EnergyCounter energyList;
    private IAttack selectedAttack;
    private ITrainer trainer;

    /**
     * The constructor of AbstractPokemon. It cannot create an instance of the class itself, but it is used by all of its subclasses.
     * Initially, some of its parameters are empty, because they will be added during the game.
     * A restriction for the constructor is that the Pokémon cannot have more than 4 attacks.
     *
     * @param name The name of the Pokémon.
     * @param id The identification number of the Pokémon (according to the Pokédex).
     * @param hp The initial hit points of the Pokémon.
     * @param anAttackList The list of attacks the Pokémon can use. If it contains more than 4 attacks, only the first 4 will be stored.
     */
    protected AbstractPokemon(String name, int id, int hp, ArrayList<IAttack> anAttackList) {
        this.name = name;
        this.id = id;
        this.hp = hp;
        this.attackList = new ArrayList<>();
        for (IAttack attack : anAttackList) {
            if (attackList.size() <= 4) attackList.add(attack);
        }
        this.energyList = new EnergyCounter();
        this.selectedAttack = null;
        this.trainer = null;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getHP() {
        if (hp < 0) {
            return 0;
        }
        return hp;
    }

    @Override
    public EnergyCounter getEnergyList() { return energyList; }

    @Override
    public int getFightingEnergyAvailable() { return energyList.getFightingEnergy(); }

    @Override
    public int getFireEnergyAvailable() { return energyList.getFireEnergy(); }

    @Override
    public int getGrassEnergyAvailable() { return energyList.getGrassEnergy(); }

    @Override
    public int getLightningEnergyAvailable() { return energyList.getLightningEnergy(); }

    @Override
    public int getPsychicEnergyAvailable() { return energyList.getPsychicEnergy(); }

    @Override
    public int getWaterEnergyAvailable() { return energyList.getWaterEnergy(); }

    @Override
    public List<IAttack> getAttacks() { return attackList; }

    @Override
    public IAttack getSelectedAttack() { return selectedAttack; }

    @Override
    public String getCardName() { return name; }

    @Override
    public ITrainer getTrainer() { return trainer; }

    @Override
    public void setTrainer(ITrainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public void isPlayed() {
        trainer.addToBench(this);
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public void updateHP(int newHP) { this.hp = newHP; }

    @Override
    public void addEnergyToPokemon(IEnergy energy) {
        energy.isAdded(this);
    }

    @Override
    public void setAttack(int index) {
        if (index < attackList.size()) selectedAttack = attackList.get(index);
    }

    @Override
    public boolean canAttack() {
        EnergyCounter costs = selectedAttack.getEnergyCosts();
        return (getHP() > 0) && (energyList.greaterOrEqual(costs));
    }

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
    public void suffersEffectiveDamage(int damage) {
        int postHP = this.getHP() - damage;
        if (postHP < 0) {
            this.updateHP(0);
            this.getTrainer().setActivePokemon();
        }
        else {
            this.updateHP(postHP);
        }
    }

    @Override
    public void receivesNeutralAttack(IAttack attack) {
        int damage = attack.getBaseDamage();
        suffersEffectiveDamage(damage);
    }

    @Override
    public void receivesStrengthenedAttack(IAttack attack) {
        int damage = 2*(attack.getBaseDamage());
        suffersEffectiveDamage(damage);
    }

    @Override
    public void receivesWeakenedAttack(IAttack attack) {
        int damage = attack.getBaseDamage() - 30;
        suffersEffectiveDamage(damage);
    }

}
