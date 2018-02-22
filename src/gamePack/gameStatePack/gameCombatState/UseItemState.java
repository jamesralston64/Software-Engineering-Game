package gamePack.gameStatePack.gameCombatState;

import java.util.ArrayList;
import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;
import gamePack.gameStatePack.gameTextStatePack.TextInputState;

public class UseItemState implements CombatStateInterface {

	public UseItemState() {
	}

	public UseItemState(GameCharacterInterface player2, ArrayList<GameCharacterInterface> enemies2) {
	}

	@Override
	public void run(GameCharacterInterface me) {

		for (GameCharacterInterface c : me.getTargets()) {
			MainWindow.updateTextArea("using item on " + c.getName() + "\n");
			c.useItem(me.getCurrentItem());

		}

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Use item";
	}

	@Override
	public void setCombat(CombatShenanigans theCombat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTargets(GameCharacterInterface me, ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes) {
		int i = 1;
		int choice;

		me.setCurrentItem(me.chooseItem());

		MainWindow.updateTextArea("Item chosen was " + me.getCurrentItem() + "\n");
		ArrayList<GameCharacterInterface> everyone = new ArrayList<>();
		ArrayList<GameCharacterInterface> target = new ArrayList<>();

		everyone.addAll(friends);
		everyone.addAll(foes);

		MainWindow.updateTextArea("Who are you using the item on?" + "\n");

		for (GameCharacterInterface c : everyone) {
			MainWindow.updateTextArea(i++ + ". " + c.getName() + ": " + c.getHealth() + "/" + c.getMaxHealth() + "\n");
		}

		choice = TextInputState.readInt();

		/*
		 * Scanner in = new Scanner(System.in); choice = in.nextInt();
		 * in.close();
		 */

		// choice = ConcreteCharacter.user.nextInt();
		target.add(everyone.get(choice - 1));

		me.setTargets(target);
	}

}
