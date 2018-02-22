package gamePack.gameEntityPack.gameCharacterBehavior;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class TrollAttack implements AttackInterface {

	@Override
	public void attack(GameCharacterInterface me, GameCharacterInterface you) {
		MainWindow.updateTextArea(me.getName() + " wildly thrashes his " + me.getAttackWeapon().getName() + " and hits "
				+ you.getName() + "\n");
		you.takeDamage(me.getStrength());
	}

}
