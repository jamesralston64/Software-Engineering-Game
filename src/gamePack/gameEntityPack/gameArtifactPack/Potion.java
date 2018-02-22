package gamePack.gameEntityPack.gameArtifactPack;

import java.util.Random;

import gamePack.gameEntityPack.GameEntityInterface;
import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class Potion implements GameArtifactInterface {
	private int numUses, maxUses;
	private String name;

	public Potion(int n, String name) {
		this.numUses = this.maxUses = n;
		this.name = name;
	}// end Potion

	@Override
	public void use(ConcreteCharacter character) {
		int val;

		if (this.numUses > 0) {
			Random r = new Random();
			int high = character.getMaxHealth() - character.getHealth();
			int low = 10;

			if (high - low == 0)
				return;

			val = r.nextInt(Math.abs(high - low)) + low;
			character.setHealth(character.getHealth() + Math.abs(val));
			numUses--;
		} // end if

		else {
			MainWindow.updateTextArea("Potion is empty.\n");
			// return 0;
		} // end else

	}// end use

	public int getNumUses() {
		return this.numUses;
	}// end getNumUses

	@Override
	public String getName() {
		return this.name;
	}// end getName

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
	public void restore() {
		this.numUses = this.maxUses;

	}

	@Override
	public int compareTo(GameEntityInterface o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
