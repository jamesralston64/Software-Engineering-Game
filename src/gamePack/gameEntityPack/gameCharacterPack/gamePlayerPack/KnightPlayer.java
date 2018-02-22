package gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack;

import gamePack.gameEntityPack.gameArtifactPack.Potion;
import gamePack.gameEntityPack.gameCharacterBehavior.NullAttack;
import gamePack.gameEntityPack.gameWeaponPack.BattleAxe;
import gamePack.gameEntityPack.gameWeaponPack.BigStick;
import gamePack.gameEntityPack.gameWeaponPack.GreatHammer;
import gamePack.gameEntityPack.gameWeaponPack.NullWeapon;
import gamePack.gameEntityPack.gameWeaponPack.WeaponOfTheGods;
import gamePack.gameStatePack.gameCombatState.AllTargetCombat;
import gamePack.gameStatePack.gameCombatState.ChangeWeapon;
import gamePack.gameStatePack.gameCombatState.SingleTargetCombat;
import gamePack.gameStatePack.gameCombatState.UseItemState;

public class KnightPlayer extends ConcretePlayer {
	public KnightPlayer() {
		this.setDefault();
	}

	public void setDefault() {
		this.setName("KnightPlayer");
		this.setAttack(new NullAttack());
		this.setMaxHealth(300);
		this.restore();
		this.setStrength(40);

		this.addWeapon(new BigStick());
		this.addWeapon(new GreatHammer());
		this.addWeapon(new BattleAxe());
		this.addWeapon(new WeaponOfTheGods());
		this.setAttackWeapon(new NullWeapon());

		this.addCombatState(new AllTargetCombat());
		this.addCombatState(new SingleTargetCombat());
		this.addCombatState(new ChangeWeapon());
		this.addCombatState(new UseItemState());

		this.addItem(new Potion(5, "Potion"));
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
