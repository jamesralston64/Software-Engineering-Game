package gamePack.gameStatePack.gameTextStatePack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import gamePack.gameStatePack.GameStateInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class ProfileInputTest {
	static GameStateInterface gameState;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		gameState = new ProfileInputState();
	}

	@After
	public void tearDown() throws Exception {
		// scanner.close();
	}

	@Test
	public void testReadWord() {
		MainWindow.updateTextArea("type \"word\" with a single trailing space and return\n");
		String res = ((ProfileInputState) gameState).readWord();
		assertEquals("word", res);
		((ProfileInputState) gameState).readLine();

		MainWindow.updateTextArea("type \"word\" with a single trailing space and return\n");
		res = ((ProfileInputState) gameState).readWord();
		((ProfileInputState) gameState).readLine();
		assertEquals("word", res);
	}

	@Test
	public void testReadLine() {
		MainWindow.updateTextArea("type \"word\" with or without trailing space and return\n");
		String res = ((ProfileInputState) gameState).readLine();
		assertEquals("word", res);

	}

	@Test
	public void testReadInt() {
		MainWindow.updateTextArea("type \"2\" followed by any garbage and return\n");
		String res = Integer.toString(((ProfileInputState) gameState).readInt());
		assertEquals("2", res);

	}

	@Test
	public void testReadChar() {
		MainWindow.updateTextArea("type \"a\" followed by any garbage and return\n");
		String res = Character.toString(((ProfileInputState) gameState).readChar());
		assertEquals("a", res);

	}

	@Test
	public void testOpenMenu() {
		((ProfileInputState) gameState).openMenu();
	}
}
