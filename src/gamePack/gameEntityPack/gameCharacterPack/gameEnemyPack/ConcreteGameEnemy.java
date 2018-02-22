package gamePack.gameEntityPack.gameCharacterPack.gameEnemyPack;

import java.util.ArrayList;

import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;
import gamePack.gameEntityPack.gameWeaponPack.GameWeaponInterface;
import gamePack.gameStatePack.gameCombatState.CombatStateInterface;

public class ConcreteGameEnemy extends ConcreteCharacter {

	@Override
	public GameWeaponInterface getWeaponChoice() {
		int numWeapons;
		ArrayList<GameWeaponInterface> weapons = this.getWeapons();

		numWeapons = weapons.size();

		return weapons.get(ConcreteCharacter.random.nextInt(numWeapons));
	}

	@Override
	public void getCombatChoice() {
		int numStates;
		ArrayList<CombatStateInterface> theStates = this.getCombatStates();

		numStates = theStates.size();

		this.setState(theStates.get(Math.abs(ConcreteCharacter.random.nextInt(numStates))));
		// System.out.println("Chosen state is " +
		// this.getCurrentState().getName() );
	}

}