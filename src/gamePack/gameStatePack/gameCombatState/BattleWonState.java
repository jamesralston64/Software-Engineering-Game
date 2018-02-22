package gamePack.gameStatePack.gameCombatState;

import java.util.ArrayList;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class BattleWonState implements CombatStateInterface {

	private GameCharacterInterface actor;

	public BattleWonState(GameCharacterInterface thePlayer) {
		this.actor = thePlayer;
	}

	@Override
	public void run(GameCharacterInterface me) {
		MainWindow.updateTextArea(actor.getName() + " has defeated all opponents!\n");
		actor.setState(new EndCombatState(actor));

	}

	@Override
	public String getName() {
		return "Battle won state";
	}

	@Override
	public void setCombat(CombatShenanigans theCombat) {

	}

	@Override
	public void setTargets(GameCharacterInterface me, ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes) {

	}

}
