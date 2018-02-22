package gamePack.gameEntityPack.gameArtifactPack;

import gamePack.gameEntityPack.GameEntityInterface;
import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;

public interface GameArtifactInterface extends GameEntityInterface {
	public void use(ConcreteCharacter character);

	public void restore();
}
