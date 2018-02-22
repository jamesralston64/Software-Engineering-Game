package gamePack.gameStatePack.gameCombatState;

import java.util.ArrayList;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class BattleLostState implements CombatStateInterface {

	private GameCharacterInterface actor;

	public BattleLostState(GameCharacterInterface thePlayer) {
		this.actor = thePlayer;
	}

	@Override
	public void run(GameCharacterInterface me) {
		MainWindow.updateTextArea(actor.getName() + " has fallen!\n");
		actor.setState(new EndCombatState(actor));

	}

	@Override
	public String getName() {
		return "Battle Lost CharacterStateInterface";
	}

	@Override
	public void setCombat(CombatShenanigans theCombat) {

	}

	@Override
	public void setTargets(GameCharacterInterface me, ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes) {

	}

}
