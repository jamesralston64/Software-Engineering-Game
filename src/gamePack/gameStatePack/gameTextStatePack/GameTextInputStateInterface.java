package gamePack.gameStatePack.gameTextStatePack;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.GamePlayerInterface;
import gamePack.gameStatePack.GameStateInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public interface GameTextInputStateInterface extends GameStateInterface {
	static PrintStream printStream = new PrintStream(System.out);
	static Scanner scanner = new Scanner(System.in);
	static File errorLogFile = new File("GameData/ProfileInputErrorLog_" + System.currentTimeMillis());
	static PrintStream gameErrorLog = new PrintStream(System.out);

	default String readWord() {
		MainWindow.updateTextArea("GameTextInputStateInterface.readWord()\n");
		String res = GameTextInputStateInterface.scanner.next().trim();
		return res;
	}

	default String readWord(Scanner scanner) {
		MainWindow.updateTextArea("GameTextInputStateInterface.readWord(Scanner scanner)\n");
		String res = scanner.next().trim();
		return res;
	}

	default String readLine() {
		MainWindow.updateTextArea("GameTextInputStateInterface.readLine()\n");
		String res = GameTextInputStateInterface.scanner.nextLine().trim();
		return res;
	}

	default String readLine(Scanner scanner) {
		MainWindow.updateTextArea("GameTextInputStateInterface.readLine(Scanner scanner)\n");
		String res = scanner.nextLine().trim();
		return res;
	}

	default int readInt() {
		int num = 0;
		boolean parsedInt = true;
		String something = GameTextInputStateInterface.scanner.nextLine();
		try {
			num = Integer.parseInt(something);
		} catch (NumberFormatException nfe) {
			MainWindow.updateTextArea("something didn't parse to an int\n");
			parsedInt = false;
		}
		while (!parsedInt) {
			something = GameTextInputStateInterface.scanner.nextLine();
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

	default int readInt(Scanner scanner) {
		int num = 0;
		boolean parsedInt = true;
		String something = scanner.nextLine();
		try {
			num = Integer.parseInt(something);
		} catch (NumberFormatException nfe) {
			MainWindow.updateTextArea("something didn't parse to an int\n");
			parsedInt = false;
		}
		while (!parsedInt) {
			something = scanner.nextLine();
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

	default char readChar() {
		char res = '?';
		try {
			res = GameTextInputStateInterface.scanner.nextLine().trim().charAt(0);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace(GameTextInputStateInterface.gameErrorLog);
		}
		return res;
	}

	default char readChar(Scanner scanner) {
		char res = '?';
		try {
			res = scanner.nextLine().trim().charAt(0);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace(GameTextInputStateInterface.gameErrorLog);
		}
		return res;
	}

	void openMenu();

	void closeMenu();
	// etc

	public void setScanner(Scanner scanner);

	@Override
	public void setPlayer(GamePlayerInterface player);

}
