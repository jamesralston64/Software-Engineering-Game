package gamePack.gameEntityPack.gameCharacterPack.gameEnemyPack;

import gamePack.gameEntityPack.gameCharacterBehavior.NullAttack;
import gamePack.gameEntityPack.gameWeaponPack.NullWeapon;

public class SnakeEnemy extends ConcreteGameEnemy {
	public SnakeEnemy() {
		super();
		this.setName("SnakeEnemy");
		this.setMaxHealth(20);
		this.setHealth(20);
		this.setStrength(5);
		this.setAttackWeapon(new NullWeapon());
		this.setAttack(new NullAttack());
		this.addCombatState(new AISingleTarget());
	}
}
