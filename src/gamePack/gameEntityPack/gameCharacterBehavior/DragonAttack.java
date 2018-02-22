package gamePack.gameEntityPack.gameCharacterBehavior;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class DragonAttack implements AttackInterface {

	@Override
	public void attack(GameCharacterInterface me, GameCharacterInterface you) {
		MainWindow.updateTextArea(
				me.getName() + " blows " + me.getAttackWeapon().getName() + " and burns " + you.getName() + "\n");
		you.takeDamage(me.getStrength());
	}

}
