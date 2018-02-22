package gamePack.gameEntityPack.gameCharacterBehavior;

import java.util.ArrayList;
import gamePack.gameEntityPack.gameCharacterPack.ConcreteCharacter;
import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameStatePack.gameCombatState.CombatShenanigans;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;
import gamePack.gameStatePack.gameTextStatePack.TextInputState;

public class HealBehavior implements SpecialBehaviorInterface {
	private CombatShenanigans theCombat;
	private int healPoints = 100;

	public HealBehavior(GameCharacterInterface actor, ArrayList<GameCharacterInterface> enemies2) {
	}

	@Override
	public void useSpecial(ConcreteCharacter me, ConcreteCharacter you) {
		me.setHealth(me.getHealth() + healPoints);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "heal power";
	}

	@Override
	public void run(GameCharacterInterface me) {
		for (GameCharacterInterface c : me.getTargets())
			c.setHealth(c.getHealth() + healPoints);
		;

		theCombat.printStatus();

	}

	@Override
	public void setCombat(CombatShenanigans theCombat) {
		this.theCombat = theCombat;

	}

	@Override
	public void setTargets(GameCharacterInterface me, ArrayList<GameCharacterInterface> friends, ArrayList<GameCharacterInterface> foes) {
		int i = 1;
		int choice;

		ArrayList<GameCharacterInterface> everyone = new ArrayList<>();
		ArrayList<GameCharacterInterface> target = new ArrayList<>();

		everyone.addAll(friends);
		everyone.addAll(foes);

		for (GameCharacterInterface c : everyone) {
			MainWindow.updateTextArea(i + ". " + c.getName() + "\n");
		}

		choice = TextInputState.readInt();

		// Scanner in = new Scanner(System.in);
		// choice = in.nextInt();
		// in.close();

		// choice = ConcreteCharacter.user.nextInt();
		target.add(everyone.get(choice - 1));
	}

}
