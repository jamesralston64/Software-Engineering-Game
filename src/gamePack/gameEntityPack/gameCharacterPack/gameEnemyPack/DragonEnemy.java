package gamePack.gameEntityPack.gameCharacterPack.gameEnemyPack;

import gamePack.gameEntityPack.gameCharacterBehavior.*;
import gamePack.gameEntityPack.gameWeaponPack.*;

public class DragonEnemy extends ConcreteGameEnemy {

	public DragonEnemy() {
		super();
		this.setName("DragonEnemy");
		this.setMaxHealth(500);
		this.setHealth(500);
		this.setStrength(50);
		this.setAttackWeapon(new FireBreath());
		this.setAttack(new DragonAttack());
		this.addCombatState(new AISingleTarget());

	}

}// end class
