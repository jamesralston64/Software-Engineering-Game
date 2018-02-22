package gamePack.gameEntityPack.gameCharacterBehavior;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class AxeAttack implements AttackInterface {

	@Override
	public void attack(GameCharacterInterface me, GameCharacterInterface you) {
		if (you.isDead()) {
			MainWindow.updateTextArea(you.getName() + " is dead. You give him a disrespectful kick in the head\n");
			return;
		}
		MainWindow.updateTextArea(me.getName() + " is hitting " + you.getName() + " with his axe!\n");
		you.takeDamage(me.getStrength());

	}

}
