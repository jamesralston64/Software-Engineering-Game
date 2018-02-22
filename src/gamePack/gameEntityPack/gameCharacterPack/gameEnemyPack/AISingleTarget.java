package gamePack.gameEntityPack.gameCharacterPack.gameEnemyPack;

import java.util.ArrayList;

import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;
import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameStatePack.gameCombatState.SingleTargetCombat;

public class AISingleTarget extends SingleTargetCombat {
	@Override
	public void setTargets(GameCharacterInterface me, ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes) {
		int choice;
		ArrayList<GameCharacterInterface> target = new ArrayList<>();

		choice = ConcreteCharacter.random.nextInt(foes.size());
		target.add(foes.get(Math.abs(choice)));

		me.setTargets(target);
		// System.out.println(me.getName() + " has chosen " + choice + " as a
		// target");

	}

	@Override
	public String getName() {
		return "AI single target";
	}
}
