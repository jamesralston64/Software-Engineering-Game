package gamePack.gameStatePack;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.GamePlayerInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;
import gamePack.gameStatePack.gameTextStatePack.SQLiteJDBC;

public class EndGame implements FinalStateInterface {

	private GamePlayerInterface player;

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
	public synchronized void run(GameStateContext gameStateContext) {
		MainWindow.updateTextArea(GameStateContext.getState().getClass().getSimpleName() + "\n"
				+ "\nThank you for playing the game\nPROGRAM TERMINATING\n");
		if (player != null) {
			GamePlayerInterface oldPlayer = SQLiteJDBC.selectProfile(player.getProfileInfo());
			int oldXP = oldPlayer.getExperience(), newXP = player.getExperience();
			if (newXP > oldXP)
				SQLiteJDBC.updateProfile(player.getProfileInfo(), "EXPERIENCE",
						Integer.toString(player.getExperience()));
			SQLiteJDBC.selectProfile(player.getProfileInfo());
			MainWindow.updateTextArea(
					"oldXP=" + oldXP + " newXP=" + newXP + " profileName" + player.getProfileInfo() + "\n");
		}
		try {
			
			for(int dt = 1, t0 = 10, t = t0; t>0; t-=dt) {
				MainWindow.updateTextArea(t+"\n");
				wait(dt*1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.exit(0);
	}

	@Override
	public void gameSave() {
		// TODO Auto-generated method stub

	}

	@Override
	public void gameShutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPlayer(GamePlayerInterface gamePlayer) {
		this.player = gamePlayer;

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
