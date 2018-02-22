package gamePack.gameStatePack.gameCombatState;

import java.util.ArrayList;

import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;
import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;

public class EnemyCombat implements CombatStateInterface {

	private ConcreteCharacter thePlayer;
	private ArrayList<ConcreteCharacter> theEnemies;
	public EnemyCombat() {
	}

	public EnemyCombat(ConcreteCharacter player, ArrayList<ConcreteCharacter> enemies) {
		thePlayer = player;
		theEnemies = enemies;
		this.run();
	}

	// @Override
	public void run() {
		for (ConcreteCharacter c : theEnemies)
			c.attack(thePlayer);

		// thePlayer.setState(new InitialCombatState(thePlayer, theEnemies) );
		// thePlayer.runState();

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCombat(CombatShenanigans theCombat) {

	}

	@Override
	public void setTargets(GameCharacterInterface me, ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run(GameCharacterInterface me) {
		// TODO Auto-generated method stub

	}

}
