package gamePack.gameEntityPack.gameCharacterPack;

import java.util.ArrayList;

import gamePack.gameEntityPack.GameEntityInterface;
import gamePack.gameEntityPack.gameArtifactPack.GameArtifactInterface;
import gamePack.gameEntityPack.gameCharacterBehavior.AttackInterface;
import gamePack.gameEntityPack.gameWeaponPack.GameWeaponInterface;
import gamePack.gameStatePack.gameCombatState.CombatStateInterface;

public interface GameCharacterInterface extends GameEntityInterface {
	@Override
	public String getName();

	@Override
	public void setName(String name);

	public void attack(GameCharacterInterface enemy);

	public void defend();

	public void takeDamage(int damage);

	public int getStrength();

	public void setStrength(int strength);

	public int getDefense();

	public void setDefense(int defense);

	public void setAttackWeapon(GameWeaponInterface weapon);

	public GameWeaponInterface getAttackWeapon();

	public void setAttack(AttackInterface theAttack);

	public AttackInterface getAttack();

	public void setSpeed(int speed);

	public int getSpeed();

	public ArrayList<GameWeaponInterface> getWeapons();

	public void addWeapon(GameWeaponInterface weapon);

	public void addCombatState(CombatStateInterface combatState);

	public int getHealth();

	public void setHealth(int health);

	public boolean isDead();

	public void addItem(GameArtifactInterface item);

	public void changeWeapon();

	public int getMaxHealth();

	public ArrayList<CombatStateInterface> getCombatStates();

	public GameArtifactInterface chooseItem();

	public void useItem(GameArtifactInterface theItem);

	public void setState(CombatStateInterface theState);

	public void restore();

	public void runState();

	public void getCombatChoice();

	public void chooseTarget(ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes);

	public void setTargets(ArrayList<GameCharacterInterface> targets);

	public ArrayList<GameCharacterInterface> getTargets();

	public void setCurrentItem(GameArtifactInterface item);

	public GameArtifactInterface getCurrentItem();

	public CombatStateInterface getCurrentState();

	public void clearTargets();

}
