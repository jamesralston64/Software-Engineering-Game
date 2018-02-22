package gamePack.gameEntityPack.gameWeaponPack;

import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;

public class GreatHammer implements GameWeaponInterface {

	private String name = "GreatHammer";

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
		return 35;
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
