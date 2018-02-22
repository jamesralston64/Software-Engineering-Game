package gamePack.gameStatePack.gameCombatState;

import java.util.ArrayList;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class EndCombatState implements CombatStateInterface {
	private GameCharacterInterface player;

	public EndCombatState(GameCharacterInterface actor) {
		this.player = actor;
	}

	@Override
	public void run(GameCharacterInterface me) {
		player.restore();
		MainWindow.updateTextArea("Battle has ended\n");

	}

	@Override
	public String getName() {
		return "End combat state";
	}

	@Override
	public void setCombat(CombatShenanigans theCombat) {

	}

	@Override
	public void setTargets(GameCharacterInterface me, ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes) {

	}
}
