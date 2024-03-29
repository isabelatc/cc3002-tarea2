package cc3002.t1.pokemon;

import cc3002.t1.abilities.IAbility;
import cc3002.t1.abilities.IAttack;
import cc3002.t1.energies.IEnergy;
import cc3002.t1.general.AbstractCard;
import cc3002.t1.general.EnergyCounter;
import cc3002.t1.general.EnergyType;
import cc3002.t1.general.ICard;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for a generic pokémon.
 */
public abstract class AbstractPokemon extends AbstractCard implements ICard, IPokemon {

    private int id, hp;
    private List<IAbility> abilityList;
    private EnergyCounter energyList;
    private IAbility selectedAbility;
    private List<ICard> associatedCards;
    private IPokemon opponent;

    /**
     * The constructor of a generic Pokémon. It cannot create an instance of the class itself, but it is used by all of its subclasses.
     * Initially, some of its parameters are empty, because they will be added during the game.
     * A restriction for the constructor is that the Pokémon cannot have more than 4 abilities.
     *
     * @param name The name of the Pokémon.
     * @param id The identification number of the Pokémon (according to the Pokédex).
     * @param hp The initial hit points of the Pokémon.
     * @param anAbilityList The list of abilities the Pokémon can use. If it contains more than 4 abilities, only the first 4 will be stored.
     */
    protected AbstractPokemon(String name, int id, int hp, ArrayList<IAbility> anAbilityList) {
        super(name);
        this.id = id;
        this.hp = hp;
        this.abilityList = new ArrayList<>();
        for (IAbility ability : anAbilityList) {
            if (abilityList.size() <= 4) abilityList.add(ability);
        }
        this.energyList = new EnergyCounter();
        this.selectedAbility = null;
        this.associatedCards = new ArrayList<>();
        this.opponent = null;
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
    public List<IAbility> getAbilityList() { return abilityList; }

    @Override
    public IAbility getSelectedAbility() { return selectedAbility; }

    @Override
    public List<ICard> getAssociatedCards() {
        return this.associatedCards;
    }

    @Override
    public IPokemon getOpponent() {
        return this.opponent;
    }

    @Override
    public void setOpponent(IPokemon opponent) {
        this.opponent = opponent;
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
    public void setSelectedAbility(int index) {
        if (index < abilityList.size()) selectedAbility = abilityList.get(index);
    }

    @Override
    public boolean canUseAbility() {
        EnergyCounter costs = selectedAbility.getEnergyCosts();
        return (getHP() > 0) && (energyList.greaterOrEqual(costs));
    }

    @Override
    public abstract void usesAbility();

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
            this.getTrainer().addToDiscardPile(this);
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

    @Override
    public void transferEnergy(IPokemon other) {
        for (EnergyType energy : EnergyType.values()) {
            int thisEnergyCount = this.getEnergyList().getEnergies().get(energy);
            int otherEnergyCount = other.getEnergyList().getEnergies().get(energy);
            other.getEnergyList().getEnergies().put(energy, otherEnergyCount + thisEnergyCount);
            this.getEnergyList().getEnergies().put(energy, 0);
        }
    }

    @Override
    public void replacePokemon(IPokemon other) {
        other.transferEnergy(this);
        if (other.getTrainer().getActivePokemon() == other) {
            other.getTrainer().changeActivePokemon(this);
        }
        else {
            int index = other.getTrainer().getBench().indexOf(other);
            other.getTrainer().getBench().set(index, this);
        }
        other.getTrainer().addToDiscardPile(other);
    }

    @Override
    public void associateCard(ICard card) {
        associatedCards.add(card);
    }

}
