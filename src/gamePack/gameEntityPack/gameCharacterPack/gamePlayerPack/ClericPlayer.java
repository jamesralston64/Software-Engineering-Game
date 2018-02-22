package gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack;

import gamePack.gameEntityPack.gameArtifactPack.Potion;
import gamePack.gameEntityPack.gameCharacterBehavior.NullAttack;
import gamePack.gameEntityPack.gameWeaponPack.NullWeapon;

public class ClericPlayer extends ConcretePlayer {

	public ClericPlayer() {
		super();
		this.setMaxHealth(300);
		this.setHealth(200);
		this.setStrength(15);
		this.setAttackWeapon(new NullWeapon());
		this.setDefendWeapon(this.getAttackWeapon());
		this.setAttack(new NullAttack());
		this.setName("Cleric");
		this.addWeapon(new NullWeapon());
		// this.addCombatState( new HealBehavior() );
		this.addItem(new Potion(5, "Potion"));
	}

}