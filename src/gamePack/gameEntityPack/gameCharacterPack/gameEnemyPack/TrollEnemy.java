package gamePack.gameEntityPack.gameCharacterPack.gameEnemyPack;

import gamePack.gameEntityPack.gameCharacterBehavior.*;
import gamePack.gameEntityPack.gameWeaponPack.*;

public class TrollEnemy extends ConcreteGameEnemy {

	public TrollEnemy() {
		super();
		this.setName("Troll");
		this.setMaxHealth(50);
		this.setHealth(50);
		this.setStrength(5);
		this.setAttackWeapon(new BigStick());
		this.setAttack(new TrollAttack());
		this.addCombatState(new AISingleTarget());

	}

}// end class
