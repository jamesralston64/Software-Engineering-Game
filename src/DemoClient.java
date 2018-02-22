
import gamePack.gameStatePack.GameStateContext;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class DemoClient {
	public static void main(String[] args) {

		GameStateContext gameStateContext = GameStateContext.getGameStateContext();
		MainWindow.updateTextArea("DemoClient\n");
		gameStateContext.run();

	}
}