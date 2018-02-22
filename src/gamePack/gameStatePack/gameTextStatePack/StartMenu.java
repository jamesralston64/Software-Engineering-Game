package gamePack.gameStatePack.gameTextStatePack;

import java.io.PrintStream;
import java.util.Scanner;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.GamePlayerInterface;
import gamePack.gameStatePack.EndGame;
import gamePack.gameStatePack.GameStateInterface;
import gamePack.gameStatePack.GameStateContext;
import gamePack.gameStatePack.gameMapStatePack.GameMapState;
import gamePack.gameStatePack.gameMapStatePack.GameMapStateInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class StartMenu implements GameTextInputStateInterface {
	private GamePlayerInterface player;
	private Scanner scanner;
	private PrintStream printStream;
	private PrintStream gameErrorLog;
	public StartMenu() {
		this.setScanner(new Scanner(System.in));
		this.setPrintStream(new PrintStream(System.out));
		/*
		 * try { this.setGameErrorLog(new PrintStream(new
		 * File("GameData/StartMenuErrorLog_"+System.currentTimeMillis()))); }
		 * catch (FileNotFoundException e) { e.printStackTrace(); }
		 */
	}
	/*
	 * public StartMenu(Scanner scanner, OutputStream printStream) {
	 * this.setScanner(scanner); this.setPrintStream(new
	 * PrintStream(printStream)); try { this.setGameErrorLog(new PrintStream(new
	 * File("GameData/StartMenuErrorLog_"+System.currentTimeMillis()))); } catch
	 * (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * public StartMenu(Scanner scanner, OutputStream printStream, PrintStream
	 * gameErrorLog, GamePlayerInterface player) { this.setScanner(scanner);
	 * this.setPrintStream(new PrintStream(printStream));
	 * this.setGameErrorLog(gameErrorLog); this.setPlayer(player); }
	 */

	@Override
	public void run(GameStateContext gameStateContext) {
		MainWindow.updateTextArea(GameStateContext.getState().getClass().getSimpleName() + "\n");
		MainWindow.updateTextArea(" XP=" + player.getExperience() + " profileName=" + player.getProfileInfo() + "\n");
		this.openMenu();
		// gameStateContext.setState(new EndGame());
		gameStateContext.run();
	}

	@Override
	public String readWord() {
		// getPrintStream().println("ProfileInputState.readWord()");
		String res = getScanner().next().trim();
		return res;
	}

	@Override
	public String readLine() {
		// printStream.println("ProfileInputState.readLine()");
		String res = getScanner().nextLine().trim();
		return res;
	}

	@Override
	public int readInt() {
		int num = 0;
		boolean parsedInt = true;
		String something = getScanner().nextLine();
		try {
			num = Integer.parseInt(something);
		} catch (NumberFormatException nfe) {
			MainWindow.updateTextArea("something didn't parse to an int");
			parsedInt = false;
		}
		while (!parsedInt) {
			something = getScanner().nextLine();
			try {
				num = Integer.parseInt(something);

				parsedInt = true; /* nfe skips this */
			} catch (NumberFormatException nfe) {
				MainWindow.updateTextArea("something didn't parse to an int");
				parsedInt = false;
			}
		}
		return num;
	}

	@Override
	public char readChar() {
		char res = '?';
		try {
			res = getScanner().nextLine().trim().charAt(0);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace(getGameErrorLog());
		}
		return res;
	}

	@Override
	public void openMenu() {
		int option = 999;
		do {
			MainWindow.updateTextArea("\n_____-----Game-----_____\n" + "\nchoose an option:\n" + "1)  play game\n"
					+ "2)  back\n" + "0)  EXIT\n");
			option = readInt();
			switch (option) {
			case 1:
				GameMapStateInterface mapState = new GameMapState();
				mapState.setPlayer(player);
				GameStateContext.setState(mapState);
				// gameStateContext.run();
				// gamePack.gameEntityPack.gameLocalMapPack.DefaultWindow.main(null);

				break;
			case 2:
				GameTextInputStateInterface profileInput = new ProfileInputState();
				profileInput.setScanner(new Scanner(System.in));
				profileInput.setPlayer(player);
				GameStateContext.setState(profileInput);
				// gameStateContext.run();
				break;
			case 0:
				GameStateContext.setState(new EndGame());
				// System.out.println("\nThank you for playing the game\n\n");
				break;
			default:
			}
		} while (option != 0 && GameStateContext.getState().getClass().getSimpleName().equals("StartMenu"));
	}

	public Scanner getScanner() {
		return scanner;
	}

	@Override
	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public PrintStream getPrintStreamOut() {
		return getPrintStream();
	}

	public void setPrintStream(PrintStream printStream) {
		this.printStream = printStream;
	}

	public PrintStream getGameErrorLog() {
		return gameErrorLog;
	}

	public void setGameErrorLog(PrintStream gameErrorLog) {
		this.gameErrorLog = gameErrorLog;
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
	public void closeMenu() {
		// TODO Auto-generated method stub

	}

	private PrintStream getPrintStream() {
		return printStream;
	}

/*	private PrintStream getProfileOutputStream() {
		return profileOutputStream;
	}

	private void setProfileOutputStream(PrintStream profileOutputStream) {
		this.profileOutputStream = profileOutputStream;
	}

	private Scanner getProfileInputStream() {
		return profileInputStream;
	}

	private void setProfileInputStream(Scanner profileInputStream) {
		this.profileInputStream = profileInputStream;
	}*/

	@Override
	public GamePlayerInterface getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(GamePlayerInterface player) {
		this.player = player;
	}

	@Override
	public void addEnemy(GameCharacterInterface enemy) {
		// TODO Auto-generated method stub

	}

}
