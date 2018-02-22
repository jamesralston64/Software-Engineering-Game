package gamePack.gameStatePack.gameCombatState;

import java.util.ArrayList;
import java.util.Scanner;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;

public class AllTargetCombat implements CombatStateInterface {

	Scanner user;
	public AllTargetCombat() {
	}

	public AllTargetCombat(GameCharacterInterface player, ArrayList<GameCharacterInterface> enemies) {

	}

	@Override
	public void run(GameCharacterInterface me) {
		for (GameCharacterInterface c : me.getTargets())
			me.attack(c);

		// actor.setState( new EnemyCombat(actor, characters));
		// theCombat.printStatus();

	}

	@Override
	public String getName() {
		return "All target ";
	}

	@Override
	public void setCombat(CombatShenanigans theCombat) {

	}

	@Override
	public void setTargets(GameCharacterInterface me, ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes) {
		me.setTargets(foes);

	}

}
