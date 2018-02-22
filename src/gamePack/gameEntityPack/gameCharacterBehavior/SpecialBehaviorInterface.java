package gamePack.gameEntityPack.gameCharacterBehavior;

import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;
import gamePack.gameStatePack.gameCombatState.CombatStateInterface;

public interface SpecialBehaviorInterface extends CombatStateInterface {
	public void useSpecial(ConcreteCharacter me, ConcreteCharacter you);

	@Override
	public String getName();
}
