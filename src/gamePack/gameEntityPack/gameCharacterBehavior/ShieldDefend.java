package gamePack.gameEntityPack.gameCharacterBehavior;

import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;
import gamePack.gameEntityPack.gameCharacterPack.DefendInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class ShieldDefend implements DefendInterface {

	@Override
	public void defend(ConcreteCharacter me) {
		MainWindow.updateTextArea(me.getName() + " is defending!\n");

	}

}
