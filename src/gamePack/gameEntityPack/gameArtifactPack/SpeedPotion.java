package gamePack.gameEntityPack.gameArtifactPack;

import java.util.Random;

import gamePack.gameEntityPack.GameEntityInterface;
import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class SpeedPotion implements GameArtifactInterface {
	private int numUses;
	public SpeedPotion(int n, String name) {
		this.numUses = n;
	}// end StrengthPotion

	@Override
	public void use(ConcreteCharacter c) {
		if (this.numUses > 0) {
			new Random();

			// return r.nextInt(high - low) + low;
		} // end if

		else {
			MainWindow.updateTextArea("Potion is empty!\n");
			// return 0;
		} // end else
	}// end use

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setView(String view) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void spawn() {
		// TODO Auto-generated method stub

	}

	@Override
	public void kill() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(GameEntityInterface o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void restore() {
		// TODO Auto-generated method stub

	}
}
