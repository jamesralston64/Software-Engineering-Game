package gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;

public interface GamePlayerInterface extends GameCharacterInterface {
	public String getProfileInfo();

	public void setProfileInfo(String info); // name, config,
												// lastSavedateTime...

	public String getStats();

	public void setStats(String stats); // survivability, experience, narrative
										// completion, difficulty, handicap...
	// public void attack(GameCharacterInterface target);

	public void setExperience(int experienceInt);

	public int getExperience();
}
