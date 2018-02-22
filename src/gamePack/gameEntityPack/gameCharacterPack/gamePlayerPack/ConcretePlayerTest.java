package gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ConcretePlayerTest {
	ConcretePlayer player = new ConcretePlayer();

	@BeforeClass
	public static void setupBeforeTest() throws Exception {

	}

	@Test
	public void testProfile() {
		player.setProfileInfo("Profile info");
		assertEquals(player.getProfileInfo(), "Profile info");
	}

	@Test
	public void testStats() {
		player.setStats("Profile Stats");
		assertEquals(player.getStats(), "Profile Stats");
	}

}
