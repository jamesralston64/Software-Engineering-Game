package gamePack.gameStatePack;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.GamePlayerInterface;

public interface GameStateInterface {
	void nextTurn();

	void executeTurn(GameCharacterInterface character);

	void prelude();

	void interlude();

	void cutScene();

	void exitGame();

	void exitGame(GamePlayerInterface player);

	void enterState(GameStateInterface state);

	void run(GameStateContext gameStateContext);

	void setPlayer(GamePlayerInterface gamePlayer);

	GamePlayerInterface getPlayer();

	void addEnemy(GameCharacterInterface enemy);
}
