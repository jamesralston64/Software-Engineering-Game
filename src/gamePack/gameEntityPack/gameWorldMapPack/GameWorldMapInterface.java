package gamePack.gameEntityPack.gameWorldMapPack;

import java.util.ArrayList;

import gamePack.gameEntityPack.GameEntityInterface;
import gamePack.gameEntityPack.gameLocalMapPack.GameLocalMapInterface;

public interface GameWorldMapInterface extends GameEntityInterface {
	void setLocalMaps(ArrayList<GameLocalMapInterface> localMaps);

	public ArrayList<GameLocalMapInterface> getLocalMaps();

	public int getWorldX(GameEntityInterface entity);

	void setWorldX(int x, GameEntityInterface entity);

	public int getWorldY(GameEntityInterface entity);

	void setWorldY(int y, GameEntityInterface entity);
}