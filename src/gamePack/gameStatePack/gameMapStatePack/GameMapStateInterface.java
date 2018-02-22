package gamePack.gameStatePack.gameMapStatePack;

import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.GamePlayerInterface;
import gamePack.gameStatePack.GameStateInterface;

public interface GameMapStateInterface extends GameStateInterface {
	public void display();

	void updateDisplay();

	void enterMap();

	void exitMap();

	@Override
	public void setPlayer(GamePlayerInterface player);
}
