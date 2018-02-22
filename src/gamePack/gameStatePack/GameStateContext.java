package gamePack.gameStatePack;

public class GameStateContext {
	private static GameStateInterface gameState;
	private static GameStateContext gameStateContext;
	private static int difficulty;

	private GameStateContext() {
		gameState = new StartGame();
		GameStateContext.gameStateContext = this;
	}

	public static void setState(final GameStateInterface newState) {
		if (GameStateContext.getGameStateContext() == null)
			GameStateContext.gameStateContext = new GameStateContext();
		GameStateContext.gameState = newState;
	}

	public static GameStateInterface getState() {
		if (GameStateContext.gameState == null) {
			GameStateContext.gameStateContext = new GameStateContext();
		}
		return GameStateContext.gameState;
	}

	public void run() {
		gameState.run(this);
	}

	public static GameStateContext getGameStateContext() {
		if (GameStateContext.gameStateContext == null) {
			GameStateContext.gameStateContext = new GameStateContext();
		}
		return GameStateContext.gameStateContext;
	}

	public static void setDifficulty(int difficultyInt) {
		GameStateContext.difficulty = difficultyInt;

	}

	public static int getDifficulty() {
		return GameStateContext.difficulty;
	}
}