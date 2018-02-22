package gamePack.gameEntityPack.gameArtifactPack;

import gamePack.gameEntityPack.GameEntityInterface;
import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;

public class NullArtifact implements GameArtifactInterface {

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
	public void use(ConcreteCharacter character) {
		// TODO Auto-generated method stub
		// return 0;
	}

	@Override
	public void restore() {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(GameEntityInterface o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
