
package gamePack.gameStatePack.gameMapStatePack;

import java.io.IOException;
import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.GamePlayerInterface;
import gamePack.gameStatePack.GameStateInterface;
import gamePack.gameStatePack.GameStateContext;

public class GameMapState implements GameMapStateInterface {
	GameStateContext gameStateContext;

	private GamePlayerInterface player;

	@Override
	public synchronized void run(GameStateContext gameStateContext) {
		this.gameStateContext = gameStateContext;
		MainWindow.updateTextArea(GameStateContext.getState().getClass().getSimpleName() + "\n");
		MainWindow.updateTextArea(" XP=" + player.getExperience() + " profileName=" + player.getProfileInfo() + "\n");
		if(MainWindow.mapCanvas==null)
			MainWindow.makeMapPane();
		
		
		MainWindow.knight0_Canvas.gameCharacter = player;

		MapCanvas.mapState = MapCanvas.gameMap;
		MainWindow.setMapIsVisible(true);

		while (MainWindow.mapIsVisible())
			try {
				wait(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		try {
			System.in.close();// if text was entered during map state this will
								// clear the input stream
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * Scanner sc = new Scanner(System.in); if(sc.hasNextLine())
		 * sc.nextLine(); sc.close();
		 */
		/*
		 * GameTextInputStateInterface newState = new StartMenu();
		 * newState.setScanner(new Scanner(System.in));
		 * newState.setPlayer(player); gameStateContext.setState(newState);
		 */

		GameStateContext.getState().setPlayer(player);
		gameStateContext.run();
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
	public void display() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDisplay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterMap() {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitMap() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPlayer(GamePlayerInterface player) {
		this.player = player;
	}

	@Override
	public void addEnemy(GameCharacterInterface enemy) {
		// TODO Auto-generated method stub

	}

	@Override
	public GamePlayerInterface getPlayer() {
		// TODO Auto-generated method stub
		return this.player;
	}

}
