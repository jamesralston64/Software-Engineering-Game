package gamePack.gameStatePack.gameTextStatePack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import gamePack.gameStatePack.GameStateContext;

public class StartMenuTest {

	static GameStateContext gameStateContext;
	static Scanner scanner;
	static PrintStream printStream;
	static PrintStream errorLog;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InputStream in = System.in;
		OutputStream out = System.out;
		scanner = new Scanner(in);
		printStream = new PrintStream(out);
		try {
			errorLog = new PrintStream(new File("TestLogs/TestProfileInputErrorLog_" + System.currentTimeMillis()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// gameStateContext = new GameStateContext();
	}

	@After
	public void tearDown() throws Exception {
		// scanner.close();
	}

	@Test
	public void testRun() {
		gameStateContext.run();
	}

}
