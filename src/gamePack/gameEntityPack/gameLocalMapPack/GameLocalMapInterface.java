package gamePack.gameEntityPack.gameLocalMapPack;

import java.util.ArrayList;

import gamePack.gameEntityPack.GameEntityInterface;
import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.GamePlayerInterface;

public interface GameLocalMapInterface extends GameEntityInterface {
	void setCharacters(ArrayList<GameCharacterInterface> characters);

	public ArrayList<GameCharacterInterface> getCharacters();

	void setPlayers(ArrayList<GamePlayerInterface> player);

	public ArrayList<GamePlayerInterface> getPlayers();

	public int getLocalX(GameEntityInterface entity);

	void setLocalX(int x, GameEntityInterface entity);

	public int getLocalY(GameEntityInterface entity);

	void setLocalY(int y, GameEntityInterface entity);

	public double distanceTo(GameEntityInterface entity);
}
