package gamePack.gameEntityPack.gameBehaviorPack;

import gamePack.gameEntityPack.GameEntityInterface;

public interface GameBehaviorInterface extends GameEntityInterface {
	public String getBehavior();

	void setBehavior(GameBehaviorInterface behavior);

	public void executeBehavior();

	public void executeBehavior(GameEntityInterface ge1, GameEntityInterface ge2);
}
