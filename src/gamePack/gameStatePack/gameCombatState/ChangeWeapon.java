package gamePack.gameStatePack.gameCombatState;

import java.util.ArrayList;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;

public class ChangeWeapon implements CombatStateInterface {

	public ChangeWeapon() {
	}

	public ChangeWeapon(GameCharacterInterface player, ArrayList<GameCharacterInterface> enemies2) {
	}

	@Override
	public String getName() {
		return "change weapon";
	}

	@Override
	public void run(GameCharacterInterface me) {
		;

	}

	@Override
	public void setCombat(CombatShenanigans theCombat) {

	}

	@Override
	public void setTargets(GameCharacterInterface me, ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes) {
		me.changeWeapon();
	}

}
