package gamePack.gameStatePack.gameCombatState;

import java.util.ArrayList;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;

public interface CombatStateInterface {
	public void run(GameCharacterInterface me);

	public String getName();

	public void setTargets(GameCharacterInterface me, ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes);

	public void setCombat(CombatShenanigans theCombat);
}
