package gamePack.gameStatePack.gameTextStatePack;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.ConcretePlayer;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.GamePlayerInterface;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.KnightPlayer;
import gamePack.gameEntityPack.gameWeaponPack.GameWeaponInterface;
import gamePack.gameStatePack.EndGame;
import gamePack.gameStatePack.GameStateInterface;
import gamePack.gameStatePack.GameStateContext;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class ProfileInputState implements GameTextInputStateInterface {
	private GamePlayerInterface player = new ConcretePlayer();

	private Scanner scanner;
	private PrintStream printStream;
	private PrintStream gameErrorLog;


	/*
	 * public ProfileInputState(Scanner scanner, PrintStream printStream) {
	 * this.setScanner(scanner); this.setPrintStream(printStream); try {
	 * this.gameErrorLog = new PrintStream(new
	 * File("GameData/ProfileInputErrorLog_"+System.currentTimeMillis())); }
	 * catch (FileNotFoundException e) { e.printStackTrace(); } }
	 * 
	 * public ProfileInputState(Scanner scanner, PrintStream printStream,
	 * PrintStream gameErrorLog) { this.setScanner(scanner);
	 * this.setPrintStream(printStream); this.gameErrorLog = gameErrorLog; }
	 */

	public ProfileInputState() {
		this.setScanner(new Scanner(System.in));
		this.setPrintStream(new PrintStream(System.out));
		/*
		 * try { this.gameErrorLog = new PrintStream(new
		 * File("GameData/ProfileInputErrorLog_"+System.currentTimeMillis())); }
		 * catch (FileNotFoundException e) { e.printStackTrace(); }
		 */
	}

	@Override
	public String readWord() {
		MainWindow.updateTextArea("ProfileInputState.readWord()\n");
		String res = getScanner().next().trim();
		return res;
	}

	@Override
	public String readLine() {
		// MainWindow.updateTextArea("ProfileInputState.readLine()\n");
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
			MainWindow.updateTextArea("something didn't parse to an int\n");
			parsedInt = false;
		}
		while (!parsedInt) {
			something = getScanner().nextLine();
			try {
				num = Integer.parseInt(something);

				parsedInt = true; /* nfe skips this */
			} catch (NumberFormatException nfe) {
				MainWindow.updateTextArea("something didn't parse to an int\n");
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
			e.printStackTrace(gameErrorLog);
		}
		return res;
	}

	public void createProfile() {
		MainWindow.updateTextArea("enter new profileName\n");
		String profileName = readLine();

		boolean characterFound = false;
		String characterNameChoiceStr = "";
		ArrayList<GameCharacterInterface> defaultGameCharactersList = new ArrayList<>(Arrays.asList(new KnightPlayer()));
		while (!characterFound) {
			MainWindow.updateTextArea("select a characteer: " + defaultGameCharactersList + "\n");
			characterNameChoiceStr = readLine();
			for (GameCharacterInterface cur : defaultGameCharactersList)
				if (cur.getName().equals(characterNameChoiceStr)) {
					player = (GamePlayerInterface) cur;
					characterFound = true;
				}
		}

		ArrayList<GameWeaponInterface> defaultWeaponsList = new ArrayList<>();
		defaultWeaponsList.addAll(player.getWeapons());

		boolean weaponFound = false;
		String weaponNameChoiceStr = "";
		while (!weaponFound) {
			MainWindow.updateTextArea("select a weapon: " + defaultWeaponsList + "\n");
			weaponNameChoiceStr = readLine();
			for (GameWeaponInterface cur : defaultWeaponsList)
				if (cur.getName().equals(weaponNameChoiceStr)) {
					player.addWeapon(cur);
					weaponFound = true;
				}
		}

		MainWindow.updateTextArea("enter difficulty\n");
		int difficulty = readInt();
		int experience = 0;
		/*
		 * try { this.profileInputStream = new Scanner(new
		 * FileInputStream("GameData/ProfileSource_"+profileName));
		 * 
		 * profileFound = true; //fnfe skips this line }
		 * catch(FileNotFoundException fnfe) {
		 * //fnfe.printStackTrace(gameErrorLog); profileFound = false; }
		 * finally{ if(profileFound) profileInputStream.close(); }
		 */

		while ((SQLiteJDBC.selectProfile(profileName)) != null) {
			MainWindow.updateTextArea("profile source exists\n" + "enter new profileName\n");
			// getPrintStream().print("-->");
			profileName = readLine();
			/*
			 * try { this.profileInputStream = new Scanner(new
			 * FileInputStream("GameData/ProfileSource_"+profileName));
			 * 
			 * profileFound = true; //fnfe skips this line }
			 * catch(FileNotFoundException fnfe) { profileFound = false; }
			 * finally{ if(profileFound) profileOutputStream.close(); }
			 */

		}
		/*
		 * try { this.profileOutputStream = new
		 * PrintStream("GameData/ProfileSource_"+profileName);
		 */

		// this.profileOutputStream.println("***begin
		// ProfileSource_"+profileName+"***");
		/*
		 * MainWindow.updateTextArea("profileName: "+profileName+"\n" +
		 * "characterName: "+player.getName()+"\n" +"weaponName: "
		 * +player.getWeapons().get(0)+"\n" +"difficulty: "+difficulty+"\n" +
		 * "experience: "+experience+"\n" );
		 */

		SQLiteJDBC.insertProfile(profileName, characterNameChoiceStr, weaponNameChoiceStr, difficulty, experience);
		this.player = SQLiteJDBC.selectProfile(profileName);

		/*
		 * this.profileOutputStream.print("profileName: "+profileName+"\n" +
		 * "characterName: "+player.getName()+"\n" +"weaponName: "
		 * +player.getWeapons().get(0)+"\n" +"difficulty: "+difficulty+"\n" +
		 * "experience: "+experience+"\n" ); //this.profileOutputStream.println(
		 * "***end ProfileSource_"+profileName+"***");
		 * this.profileOutputStream.close(); } catch (FileNotFoundException e) {
		 * e.printStackTrace(); }
		 */

	}

	public void loadProfile() {
		MainWindow.updateTextArea("enter profileName to load\n");
		String profileName = readLine();
		if ((this.player = SQLiteJDBC.selectProfile(profileName)) == null) {
			MainWindow.updateTextArea("profile source does not exist\n");
			//			profileName = readLine();
		}
		else {
			MainWindow.updateTextArea(" XP=" + player.getExperience() + " profileName=" + player.getProfileInfo() + "\n");
			this.player.setExperience(0);
			MainWindow.updateTextArea(" XP=" + player.getExperience() + " profileName=" + player.getProfileInfo() + "\n");
		}

	}

	@Override
	public void openMenu() {
		int option = 999;
		do {
			MainWindow.updateTextArea("\n_____-----Game-----_____\n" + "Welcome, choose an option:\n"
					+ "1)  Create a new profile\n" 
					+ "2)  Load an existing profile\n"
					// + "3) readLine() --> Log\n"
					+ "0)  EXIT\n");
			option = readInt();
			switch (option) {
			case 1:
				createProfile();
				break;
			case 2:
				loadProfile();
				GameTextInputStateInterface newState = new StartMenu();
				newState.setPlayer(player);
				newState.setScanner(new Scanner(System.in));
				GameStateContext.setState(newState);
				break;
				/*
				 * case 3: gameErrorLog.println(readLine()); break;
				 */
			case 0:
				GameStateContext.setState(new EndGame());
				break;
			default:
			}
		} while (option != 0 && getPlayer() == null);
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

	@Override
	public void run(GameStateContext gameStateContext) {
		MainWindow.updateTextArea(GameStateContext.getState().getClass().getSimpleName());
		this.openMenu();
		gameStateContext.run();

	}

	private PrintStream getPrintStream() {
		return printStream;
	}

	@Override
	public void setPlayer(GamePlayerInterface player) {
		this.player = player;
	}

	@Override
	public GamePlayerInterface getPlayer() {
		return this.player;
	}

	@Override
	public void addEnemy(GameCharacterInterface enemy) {
		// TODO Auto-generated method stub

	}

}
