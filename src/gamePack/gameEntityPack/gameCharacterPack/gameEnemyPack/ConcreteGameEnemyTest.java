package gamePack.gameEntityPack.gameCharacterPack.gameEnemyPack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.BeforeClass;

public class ConcreteGameEnemyTest {

	static ConcreteGameEnemy enemy;
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
		enemy = new ConcreteGameEnemy();
	}

	@After
	public void tearDown() throws Exception {
		// scanner.close();
	}

	// @Test
	// public void testDifficulty()
	// {
	// System.out.println("Enter difficulty value 10");
	// enemy.setDifficulty( scanner.nextInt() );
	//
	// assertEquals( 10, enemy.getDifficulty() );
	// }

}
