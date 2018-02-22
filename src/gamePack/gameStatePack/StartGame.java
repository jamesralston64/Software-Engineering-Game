package gamePack.gameStatePack;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.GamePlayerInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;
import gamePack.gameStatePack.gameTextStatePack.ProfileInputState;

public class StartGame implements InitialStateInterface {

	GameStateContext gameStateContext;

	@Override
	public void run(GameStateContext gameStateContext) {
		this.gameStateContext = gameStateContext;
		MainWindow.updateTextArea(GameStateContext.getState().getClass().getSimpleName() + "\n");
		this.gameStateContext = gameStateContext;
		GameStateInterface gameState = new ProfileInputState();
		GameStateContext.setState(gameState);
		this.gameStateContext.run();
	}

	@Override
	public void gameRun() {

	}

	@Override
	public void nextTurn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void executeTurn(GameCharacterInterface character) {
		// TODO Auto-generated method stub

	}

	@Override
	public void prelude() {
		// TODO Auto-generated method stub

	}

	@Override
	public void interlude() {
		// TODO Auto-generated method stub

	}

	@Override
	public void cutScene() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitGame(GamePlayerInterface player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterState(GameStateInterface state) {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameBuild() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPlayer(GamePlayerInterface gamePlayer) {

	}

	@Override
	public void addEnemy(GameCharacterInterface enemy) {
		// TODO Auto-generated method stub

	}

	@Override
	public GamePlayerInterface getPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

}
