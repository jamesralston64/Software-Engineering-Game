package gamePack.gameStatePack.gameCombatState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.GamePlayerInterface;
import gamePack.gameStatePack.EndGame;
import gamePack.gameStatePack.GameStateInterface;
import gamePack.gameStatePack.GameStateContext;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;
import gamePack.gameStatePack.gameMapStatePack.MapCanvas;
import gamePack.gameStatePack.gameTextStatePack.GameTextInputStateInterface;
import gamePack.gameStatePack.gameTextStatePack.StartMenu;

public class CombatShenanigans implements GameTextInputStateInterface {
	private ArrayList<GameCharacterInterface> thePlayers;
	private ArrayList<GameCharacterInterface> theEnemies;
	GameStateContext gameStateContext;

	@Override
	public void run(GameStateContext gameStateContext) {
		this.gameStateContext = gameStateContext;
		MainWindow.updateTextArea(GameStateContext.getState().getClass().getSimpleName() + "\n");

		MainWindow.updateTextArea(" XP=" + ((GamePlayerInterface) thePlayers.get(0)).getExperience() + " profileName="
				+ ((GamePlayerInterface) thePlayers.get(0)).getProfileInfo() + "\n");

		doCombat();
		/*
		 * GameTextInputStateInterface newState = new StartMenu();
		 * newState.setScanner(new Scanner(System.in));
		 * newState.setPlayer((GamePlayerInterface)getThePlayers().get(0));
		 * gameStateContext.setState(newState);
		 */
		gameStateContext.run();
	}

	public CombatShenanigans() {
		setThePlayers(new ArrayList<>());
		setTheEnemies(new ArrayList<>());
	}

	public CombatShenanigans(ArrayList<GameCharacterInterface> thePlayers, ArrayList<GameCharacterInterface> enemies) {
		this.setThePlayers(thePlayers);
		setTheEnemies(enemies);
	}

	public void printStatus() {
		MainWindow.updateTextArea("-------------------------------------------\n");
		for (GameCharacterInterface c : getThePlayers())
			MainWindow.updateTextArea(c.getName() + " HP: " + c.getHealth() + "/" + c.getMaxHealth() + "\n");

		MainWindow.updateTextArea("-------------------------------------------\n");

		for (GameCharacterInterface c : getTheEnemies())
			MainWindow.updateTextArea(c.getName() + " HP:" + c.getHealth() + "/" + c.getMaxHealth() + "\n");
		MainWindow.updateTextArea("-------------------------------------------\n");

	}

	public void doCombat() {
		ArrayList<GameCharacterInterface> everyone = new ArrayList<>();
		GamePlayerInterface thePlayer;
		while (true) {
			if (checkDeath(getTheEnemies())) {
				MainWindow.updateTextArea("Players are successful!\n");
				for (GameCharacterInterface character : getThePlayers()) {
					thePlayer = (GamePlayerInterface) character;
					thePlayer.setExperience(thePlayer.getExperience() + getTheEnemies().size()*getTheEnemies().get(0).getStrength());
				}
				// String stateStr =
				// GameStateContext.getState().getClass().getSimpleName();
				GameStateInterface newState;
				// MainWindow.updateTextArea("\"" + stateStr + "\"\n");

				if (MapCanvas.mapState == MapCanvas.volcanoMap)
					newState = new EndGame();
				else
					newState = new StartMenu();
				// newState.setScanner(new Scanner(System.in));
				newState.setPlayer((GamePlayerInterface) getThePlayers().get(0));
				GameStateContext.setState(newState);
				break;
			}

			if (checkDeath(getThePlayers())) {
				MainWindow.updateTextArea("Players have fallen :-(\n");
				GameStateContext.setState(new EndGame());
				break;
			}

			printStatus();

			for (GameCharacterInterface c : getTheEnemies()) {
				c.getCombatChoice();
				c.chooseTarget(getTheEnemies(), getThePlayers());
			}

			for (GameCharacterInterface c : getThePlayers()) {
				c.getCombatChoice();
				c.chooseTarget(getThePlayers(), getTheEnemies());
			}

			everyone.addAll(getThePlayers());
			everyone.addAll(getTheEnemies());

			Collections.sort(everyone);

			for (GameCharacterInterface c : everyone) {
				if (!c.isDead())
					c.runState();
				c.clearTargets();
			}
		}
		GameStateContext.getState().setPlayer((GamePlayerInterface) thePlayers.get(0));

	}

	public boolean checkDeath(ArrayList<GameCharacterInterface> theCharacters) {
		int numCharacters = theCharacters.size();
		int count = 0;
		for (GameCharacterInterface c : theCharacters)
			if (c.isDead())
				count++;
		return count >= numCharacters;
	}

	@Override
	public void nextTurn() {

	}

	@Override
	public void executeTurn(GameCharacterInterface character) {

	}

	@Override
	public void prelude() {

	}

	@Override
	public void interlude() {

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
	public void openMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setScanner(Scanner scanner) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPlayer(GamePlayerInterface player) {
		this.getThePlayers().add(player);

	}

	public ArrayList<GameCharacterInterface> getThePlayers() {
		return thePlayers;
	}

	public void setThePlayers(ArrayList<GameCharacterInterface> thePlayers) {
		this.thePlayers = thePlayers;
	}

	public ArrayList<GameCharacterInterface> getTheEnemies() {
		return theEnemies;
	}

	public void setTheEnemies(ArrayList<GameCharacterInterface> theEnemies) {
		this.theEnemies = theEnemies;
	}

	@Override
	public void addEnemy(GameCharacterInterface enemy) {
		this.getTheEnemies().add(enemy);

	}

	@Override
	public GamePlayerInterface getPlayer() {
		// TODO Auto-generated method stub
		return (GamePlayerInterface) thePlayers.get(0);
	}

}
