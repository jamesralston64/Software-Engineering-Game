package gamePack.gameEntityPack.gameWeaponPack;

import gamePack.gameEntityPack.gameCharacterBehavior.AttackInterface;
import gamePack.gameEntityPack.gameCharacterBehavior.AxeAttack;
import gamePack.gameEntityPack.gameCharacterBehavior.AxeDefend;
import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;
import gamePack.gameEntityPack.gameCharacterPack.DefendInterface;

public class BattleAxe implements GameWeaponInterface {
	private int power = 10;
	private AttackInterface axeAttack;
	private DefendInterface axeDefend;
	private String name = "BattleAxe";

	public BattleAxe() {
		axeAttack = new AxeAttack();
		axeDefend = new AxeDefend();
	}

	@Override
	public int getPower() {
		return power;
	}

	@Override
	public void weaponAttack(ConcreteCharacter me, ConcreteCharacter you) {
		axeAttack.attack(me, you);
	}

	@Override
	public void weaponDefend(ConcreteCharacter me) {
		axeDefend.defend(me);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
