package gamePack.gameEntityPack.gameWeaponPack;

import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;

public class WeaponOfTheGods implements GameWeaponInterface {
	private int power = 1000000;

	@Override
	public void weaponAttack(ConcreteCharacter me, ConcreteCharacter you) {
		// TODO Auto-generated method stub

	}

	@Override
	public void weaponDefend(ConcreteCharacter me) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPower() {
		// TODO Auto-generated method stub
		return this.power;
	}

	@Override
	public String getName() {
		return "WeaponOfTheGods";
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
