package gamePack.gameEntityPack;

public interface GameEntityInterface extends Comparable<GameEntityInterface> {

	@Override
	public String toString();

	public String getName();

	void setName(String name);

	public String getView();

	void setView(String view);

	public void updateView();

	void spawn();

	void kill();

	public void save();

	public void load();

}
