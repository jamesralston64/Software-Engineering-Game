package gamePack.gameStatePack.gameCombatState;

import java.util.ArrayList;
import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameEntityPack.gameCharacterPack.gameEnemyPack.SnakeEnemy;
import gamePack.gameEntityPack.gameCharacterPack.gameEnemyPack.TrollEnemy;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.KnightPlayer;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;
import gamePack.gameStatePack.gameTextStatePack.TextInputState;

public class Combat {
	public static void main(String[] args) {
		ArrayList<GameCharacterInterface> enemies, players;
		// Scanner user = new Scanner(System.in);
		String again = "y";
		CombatShenanigans theCombat;

		while (again.compareTo("y") == 0) {
			players = new ArrayList<>();
			players.add(new KnightPlayer());
			players.add(new KnightPlayer());
			players.add(new KnightPlayer());
			players.get(0).setSpeed(1001);

			enemies = new ArrayList<>();

			enemies.add(new TrollEnemy());
			enemies.add(new TrollEnemy());
			enemies.add(new SnakeEnemy());

			enemies.get(0).setSpeed(1000);

			enemies.size();

			// player.addCombatState(new SingleTargetCombat(player, enemies));
			// player.addCombatState(new AllTargetCombat(player, enemies));
			// player.addCombatState(new ChangeWeapon(player, enemies));
			// player.addCombatState(new HealBehavior(player, enemies));
			// player.addCombatState(new UseItemState(player, enemies) );

			theCombat = new CombatShenanigans(players, enemies);

			theCombat.doCombat();
			MainWindow.updateTextArea("===============================\n");

			MainWindow.updateTextArea("Play again? (y/n): \n");
			again = TextInputState.readLine();
			// again = user.next();
		}

		// user.close();
	}

}
