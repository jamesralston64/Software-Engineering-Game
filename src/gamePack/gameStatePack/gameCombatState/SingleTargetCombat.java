package gamePack.gameStatePack.gameCombatState;

import java.util.ArrayList;
import java.util.Scanner;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;
import gamePack.gameStatePack.gameTextStatePack.TextInputState;

public class SingleTargetCombat implements CombatStateInterface {
	Scanner user;
	

	/*
	 * public SingleTargetCombat(GameCharacterInterface actor, ArrayList<GameCharacterInterface>
	 * enemies) { this.actor = actor; this.characters = enemies;
	 * 
	 * }
	 */

	/*private GameCharacterInterface getChoice() {
		int i = 1;
		int choice;

		MainWindow.updateTextArea("Choose a target:\n");

		for (GameCharacterInterface c : this.characters) {
			MainWindow.updateTextArea(i++ + ". " + c.getName() + "\n");
			if (c.isDead() == true)
				MainWindow.updateTextArea(" (dead)\n");
			MainWindow.updateTextArea("\n");

		}

		choice = TextInputState.readInt();

		
		 * Scanner in = new Scanner(System.in); choice = in.nextInt();
		 * in.close();
		 

		// choice = ConcreteCharacter.user.nextInt();

		return characters.get(choice - 1);

	}*/

	@Override
	public String getName() {
		return "Single target ";
	}

	@Override
	public void run(GameCharacterInterface me) {
		for (GameCharacterInterface c : me.getTargets())
			me.attack(c);

	}

	@Override
	public void setCombat(CombatShenanigans theCombat) {

	}

	@Override
	public void setTargets(GameCharacterInterface me, ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes) {

		int i = 1;
		int choice;
		ArrayList<GameCharacterInterface> target = new ArrayList<>();

		MainWindow.updateTextArea("Choose a target:\n");

		for (GameCharacterInterface c : foes) {
			MainWindow.updateTextArea(i++ + ". " + c.getName() + ": " + c.getHealth() + "/" + c.getMaxHealth() + "\n");
			if (c.isDead() == true)
				MainWindow.updateTextArea(" (dead)\n");
			MainWindow.updateTextArea("\n");

		}

		choice = TextInputState.readInt();

		/*
		 * Scanner in = new Scanner(System.in); choice = in.nextInt();
		 * in.close();
		 */

		// choice = ConcreteCharacter.user.nextInt();
		target.add(foes.get(choice - 1));

		me.setTargets(target);

	}

}
