package gamePack.gameEntityPack.gameCharacterPack;

import java.util.ArrayList;
import java.util.Random;
import gamePack.gameEntityPack.GameEntityInterface;
import gamePack.gameEntityPack.gameArtifactPack.GameArtifactInterface;
import gamePack.gameEntityPack.gameCharacterBehavior.AttackInterface;
import gamePack.gameEntityPack.gameWeaponPack.GameWeaponInterface;
import gamePack.gameEntityPack.gameWeaponPack.NullWeapon;
import gamePack.gameStatePack.gameCombatState.CombatStateInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;
import gamePack.gameStatePack.gameTextStatePack.TextInputState;

public abstract class ConcreteCharacter implements GameCharacterInterface, DeadInterface, GameEntityInterface {
	// public static final Scanner user = new Scanner(System.in);
	public static final Random random = new Random();
	private CombatStateInterface currentState;
	private GameArtifactInterface currentItem;
	private String name;
	private GameWeaponInterface myAttackWeapon, myDefendWeapon;
	private AttackInterface myAttack;
	private DefendInterface myDefend;
	private ArrayList<CombatStateInterface> myCombatStates;
	private int health;
	private int maxHealth;
	private int strength;
	private int defense = 10;
	private int speed;
	private boolean isDead;
	private boolean isDefending = false;
	private ArrayList<GameWeaponInterface> myWeapons;
	private ArrayList<GameArtifactInterface> items;
	private ArrayList<GameCharacterInterface> targets;
	// private EntityCanvas blah;

	public ConcreteCharacter() {
		items = new ArrayList<>();
		myCombatStates = new ArrayList<>();
		myWeapons = new ArrayList<>();
		targets = new ArrayList<>();
		this.setAttackWeapon(new NullWeapon());
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

	@Override
	public void setHealth(int health) {
		if (health <= 0) {
			this.health = 0;
			this.setDead(true);
			return;
		}

		this.health = health;
		if (this.health > this.getMaxHealth())
			this.health = this.getMaxHealth();
	}

	@Override
	public void takeDamage(int damage) {
		int damageTaken = damage;

		if (this.isDefending()) {
			damageTaken -= defense;
			if (damageTaken < 0)
				damageTaken = 0;
		}

		this.setHealth(this.getHealth() - damageTaken);

		if (this.isDead())
			MainWindow.updateTextArea(this.getName() + " has fallen\n");
	}

	@Override
	public int getHealth() {
		return this.health;
	}

	@Override
	public void attack(GameCharacterInterface you) {
		// MainWindow.updateTextArea("attacking " + you.getName() );
		if (this.isDead())
			return;
		myAttack.attack(this, you);
	}

	@Override
	public void defend() {
		this.isDefending = true;
		MainWindow.updateTextArea(this.getName() + " is defending!\n");
		myDefendWeapon.weaponDefend(this);
	}

	@Override
	public int getStrength() {
		int totalStrength = this.strength;

		if (this.getAttackWeapon() != null)
			totalStrength += this.getAttackWeapon().getPower();
		return totalStrength;
	}

	@Override
	public void setStrength(int strength) {
		this.strength = strength;
	}

	@Override
	public int getDefense() {
		return this.defense;
	}

	@Override
	public void setDefense(int defense) {
		this.defense = defense;
	}

	@Override
	public void setDead(boolean dead) {
		this.isDead = dead;

	}

	@Override
	public boolean isDead() {
		return this.isDead;
	}

	@Override
	public void checkDead() {
		if (this.getHealth() <= 0)
			this.setDead(true);
	}

	@Override
	public void setAttackWeapon(GameWeaponInterface w) {
		this.myAttackWeapon = w;
	}

	public void setDefendWeapon(GameWeaponInterface w) {
		this.myDefendWeapon = w;
	}

	@Override
	public GameWeaponInterface getAttackWeapon() {
		return myAttackWeapon;
	}

	public void setDefending(boolean defending) {
		this.isDefending = defending;
	}

	public boolean isDefending() {
		return this.isDefending;
	}

	@Override
	public void setAttack(AttackInterface attack) {
		this.myAttack = attack;
	}

	public void setDefend(DefendInterface defend) {
		this.myDefend = defend;
	}

	@Override
	public AttackInterface getAttack() {
		return myAttack;
	}

	public DefendInterface getDefend() {
		return myDefend;
	}

	@Override
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public int getSpeed() {
		return this.speed;
	}

	public int compareSpeed(GameCharacterInterface other) {
		if (this.getSpeed() < other.getSpeed())
			return -1;

		return 1;
	}

	@Override
	public int getMaxHealth() {
		return this.maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;

	}

	@Override
	public ArrayList<GameWeaponInterface> getWeapons() {
		return myWeapons;
	}

	@Override
	public void addWeapon(GameWeaponInterface w) {
		myWeapons.add(w);
	}

	@Override
	public void changeWeapon() {
		GameWeaponInterface newWeapon = getWeaponChoice();
		this.setAttackWeapon(newWeapon);

	}

	public abstract GameWeaponInterface getWeaponChoice();

	@Override
	public void addCombatState(CombatStateInterface combatState) {
		myCombatStates.add(combatState);
	}

	public void setCurrentCombatBehavior() {
		// currentState = getCombatChoice();
	}

	@Override
	public void setState(CombatStateInterface newState) {
		currentState = newState;
	}

	@Override
	public CombatStateInterface getCurrentState() {
		return currentState;
	}

	@Override
	public ArrayList<CombatStateInterface> getCombatStates() {
		return this.myCombatStates;
	}

	@Override
	public void runState() {
		if (this.isDead())
			return;
		currentState.run(this);
	}

	@Override
	public void addItem(GameArtifactInterface item) {
		if (items == null)
			items = new ArrayList<>();
		else {
			items.add(item);
		}
	}

	@Override
	public void useItem(GameArtifactInterface item) {
		if (item != null) {
			MainWindow.updateTextArea("using " + item.getName() + "\n");
			item.use(this);
		}
	}

	@Override
	public GameArtifactInterface chooseItem() {
		int i = 1;
		int choice;

		if (items.size() < 1) {
			MainWindow.updateTextArea("No items" + "\n");
			return null;
		}

		for (GameArtifactInterface item : items)
			MainWindow.updateTextArea(i++ + ". " + item.getName() + "\n");

		choice = TextInputState.readInt();

		/*
		 * Scanner in = new Scanner(System.in); choice = in.nextInt();
		 * in.close();
		 */

		// choice = user.nextInt();

		if (choice < 1 || choice > items.size() + 1)
			return null;

		return items.get(choice - 1);
	}

	@Override
	public String getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setView(String view) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void spawn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	public void enemyCombat(ConcreteCharacter target) {
		this.attack(target);

	}

	@Override
	public void restore() {
		this.setHealth(maxHealth);
		for (GameArtifactInterface artifact : items)
			artifact.restore();
	}

	public ArrayList<GameArtifactInterface> getItems() {
		return items;
	}

	@Override
	public int compareTo(GameEntityInterface other) {
		return ((GameCharacterInterface) this).getSpeed() - ((GameCharacterInterface) other).getSpeed();
	}

	@Override
	public ArrayList<GameCharacterInterface> getTargets() {
		return targets;
	}

	@Override
	public void setTargets(ArrayList<GameCharacterInterface> targets) {
		this.targets = new ArrayList<>();
		this.targets.addAll(targets);
	}

	@Override
	public void chooseTarget(ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes) {
		this.currentState.setTargets(this, friends, foes);
	}

	@Override
	public GameArtifactInterface getCurrentItem() {
		return currentItem;
	}

	@Override
	public void setCurrentItem(GameArtifactInterface item) {
		currentItem = item;
	}

	@Override
	public void clearTargets() {
		this.getTargets().clear();
	}
}
