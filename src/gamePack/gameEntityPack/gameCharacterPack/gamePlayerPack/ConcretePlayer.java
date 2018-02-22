package gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack;

import gamePack.gameEntityPack.GameEntityInterface;
import gamePack.gameEntityPack.gameArtifactPack.GameArtifactInterface;
import gamePack.gameEntityPack.gameArtifactPack.NullArtifact;
import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;
import gamePack.gameEntityPack.gameWeaponPack.GameWeaponInterface;
import gamePack.gameStatePack.gameCombatState.CombatStateInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;
import gamePack.gameStatePack.gameTextStatePack.TextInputState;

public class ConcretePlayer extends ConcreteCharacter implements GamePlayerInterface {
	private int experience = 0;
	private String profileInfo;

	@Override
	public GameWeaponInterface getWeaponChoice() {
		int choice;
		int i = 1;
		MainWindow.updateTextArea("Pick your weapon:" + "\n");

		for (GameWeaponInterface w : this.getWeapons())
			MainWindow.updateTextArea(i++ + ". " + w.getName() + "\n");

		choice = TextInputState.readInt();

		/*
		 * Scanner in = new Scanner(System.in); choice = in.nextInt();
		 * in.close();
		 */

		// choice = user.nextInt();

		return this.getWeapons().get(choice - 1);
	}

	@Override
	public void getCombatChoice() {
		int choice, i = 1;
		MainWindow.updateTextArea(this.getName() + " combat choice:" + "\n");

		for (CombatStateInterface combatState : this.getCombatStates()) {
			MainWindow.updateTextArea(i++ + ". " + combatState.getName() + "\n");
		}

		choice = TextInputState.readInt();

		/*
		 * Scanner in = new Scanner(System.in); choice = in.nextInt();
		 * in.close();
		 */

		// choice = user.nextInt();
		this.setState(this.getCombatStates().get(choice - 1));
	}

	@Override
	public GameArtifactInterface chooseItem() {
		int i = 1;
		int choice;

		for (GameArtifactInterface item : this.getItems())
			MainWindow.updateTextArea(i++ + ". " + item.getName() + "\n");

		choice = TextInputState.readInt();

		/*
		 * Scanner in = new Scanner(System.in); choice = in.nextInt();
		 * in.close();
		 */

		// choice = user.nextInt();

		if (choice < 1 || choice > this.getItems().size() + 1)
			return new NullArtifact();

		return this.getItems().get(choice - 1);
	}

	@Override
	public int compareTo(GameEntityInterface o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getProfileInfo() {
		// TODO Auto-generated method stub
		return this.profileInfo;
	}

	@Override
	public void setProfileInfo(String info) {
		this.profileInfo = info;

	}

	@Override
	public String getStats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStats(String stats) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setExperience(int experienceInt) {
		this.experience = experienceInt;

	}

	@Override
	public int getExperience() {
		return experience;
	}

}