package gamePack.gameStatePack.gameTextStatePack;

import java.sql.*;

import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.GamePlayerInterface;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.KnightPlayer;
import gamePack.gameStatePack.GameStateContext;
import gamePack.gameStatePack.gameMapStatePack.MainWindow;

public class SQLiteJDBC {
	public static void main(String args[]) {

		//connectGameProfilesTable(); // connect
		createGameProfilesTable();// create

		String characterNameStr = "KightPlayer", weaponNameStr = "BigStick";
		int difficultyInt = 10, experienceInt = 0;
		insertProfile("Kevin", characterNameStr, weaponNameStr, difficultyInt, experienceInt);// insert
		insertProfile("Aaron", characterNameStr, weaponNameStr, difficultyInt, experienceInt);
		insertProfile("James", characterNameStr, weaponNameStr, difficultyInt, experienceInt);

		selectProfile("Kevin");// select
		updateProfile("Kevin", "EXPERIENCE", "5");// update
		deleteProfile("Kevin");// delete
		deleteProfile("Aaron");
		deleteProfile("James");
	}

/*	public static void connectGameProfilesTable() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:gameProfiles.db");
		} catch (Exception e) {
			MainWindow.updateTextArea(e.getClass().getName() + ": " + e.getMessage() + "\n");
			System.exit(0);
		}
		MainWindow.updateTextArea("Opened database successfully\n\n");
	}*/

	public static void createGameProfilesTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:gameProfiles.db");
			MainWindow.updateTextArea("Opened database successfully\n");

			stmt = c.createStatement();
			String sql = "CREATE TABLE GAMEPROFILES " + "(PROFILENAME		TEXT PRIMARY KEY	NOT NULL, "
					+ " CHARACTERNAME		TEXT				NOT NULL, "
					+ " WEAPONNAME			TEXT				NOT NULL, " + " DIFFICULTY			INT, "
					+ " EXPERIENCE			INT)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
			MainWindow.updateTextArea("Table created successfully\n\n");
		} catch (Exception e) {
			MainWindow.updateTextArea(e.getClass().getName() + ": " + e.getMessage() + "\n");
			// System.exit(0);
		}
		
	}

	public static Boolean insertProfile(String profileNameStr, String characterNameStr, String weaponNameStr,
			int difficultyInt, int experienceInt) {
		createGameProfilesTable();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:gameProfiles.db");
			c.setAutoCommit(false);
			MainWindow.updateTextArea("Opened database successfully\n");

			stmt = c.createStatement();
			String sql = "INSERT INTO GAMEPROFILES (PROFILENAME,CHARACTERNAME,WEAPONNAME,DIFFICULTY,EXPERIENCE) "
					+ "VALUES ('" + profileNameStr + "', '" + characterNameStr + "', '" + weaponNameStr + "', "
					+ difficultyInt + "," + experienceInt + " );";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
			MainWindow.updateTextArea("Records inserted successfully\n\n");
			return true;
		} catch (Exception e) {
			MainWindow.updateTextArea(e.getClass().getName() + ": " + e.getMessage() + "\n");
			return false;
			// System.exit(0);
		}
		
	}

	public static GamePlayerInterface selectProfile(String profileNameStr) {
		GamePlayerInterface player = null;
		Connection c = null;
		Statement stmt = null;
		String profileNameStr1 = "";
		String characterNameStr = "";
		String weaponNameStr = "";
		int difficultyInt = 0;
		int experienceInt = 0;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:gameProfiles.db");
			c.setAutoCommit(false);
			MainWindow.updateTextArea("Opened database successfully\n");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM GAMEPROFILES where PROFILENAME='" + profileNameStr + "';");
			while (rs.next()) {
				profileNameStr1 = rs.getString("profilename");
				characterNameStr = rs.getString("charactername");
				weaponNameStr = rs.getString("weaponname");
				difficultyInt = rs.getInt("difficulty");
				experienceInt = rs.getInt("experience");

				MainWindow.updateTextArea("PROFILENAME = " + profileNameStr1 + "\n" + "CHARACTERNAME = "
						+ characterNameStr + "\n" + "WEAPONNAME = " + weaponNameStr + "\n" + "DIFFICULTY = "
						+ difficultyInt + "\n" + "EXPERIENCE = " + experienceInt + "\n\n");
			}
			rs.close();
			stmt.close();
			c.close();
			
			
		} catch (Exception e) {
			MainWindow.updateTextArea(e.getClass().getName() + ": " + e.getMessage() + "\n");
			return null;
			// System.exit(0);
		}
		
		if (! profileNameStr1.isEmpty()) {
			player = new KnightPlayer();
			player.setProfileInfo(profileNameStr1);
			GameStateContext.setDifficulty(difficultyInt);
			player.setExperience(experienceInt);
			MainWindow.updateTextArea("Select profile operation done successfully\n\n");
		}
		return player;
	}

	public static Boolean updateProfile(String profileNameStr, String attributeKeyStr, String newValueStr) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:gameProfiles.db");
			c.setAutoCommit(false);
			MainWindow.updateTextArea("Opened database successfully\n");

			stmt = c.createStatement();
			String sql = "UPDATE GAMEPROFILES set '" + attributeKeyStr + "' = '" + newValueStr + "' where PROFILENAME='"
					+ profileNameStr + "';";
			stmt.executeUpdate(sql);
			c.commit();

			ResultSet rs = stmt.executeQuery("SELECT * FROM GAMEPROFILES where PROFILENAME='" + profileNameStr + "';");
			while (rs.next()) {
				String profileNameResStr = rs.getString("profilename");
				String characterNameResStr = rs.getString("charactername");
				String weaponNameResStr = rs.getString("weaponname");
				int difficultyResInt = rs.getInt("difficulty");
				int experienceResInt = rs.getInt("experience");

				MainWindow.updateTextArea("PROFILENAME = " + profileNameResStr + "\n" + "CHARACTERNAME = "
						+ characterNameResStr + "\n" + "WEAPONNAME = " + weaponNameResStr + "\n" + "DIFFICULTY = "
						+ difficultyResInt + "\n" + "EXPERIENCE = " + experienceResInt + "\n\n");
			}
			rs.close();
			stmt.close();
			c.close();
			MainWindow.updateTextArea("Update operation done successfully\n\n");
			return true;
		} catch (Exception e) {
			MainWindow.updateTextArea(e.getClass().getName() + ": " + e.getMessage() + "\n");
			return false;
			// System.exit(0);
		}
		
	}

	public static Boolean deleteProfile(String profileNameStr) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:gameProfiles.db");
			c.setAutoCommit(false);
			MainWindow.updateTextArea("Opened database successfully\n");

			stmt = c.createStatement();
			String sql = "DELETE from GAMEPROFILES where PROFILENAME='" + profileNameStr + "';";
			stmt.executeUpdate(sql);
			c.commit();

			ResultSet rs = stmt.executeQuery("SELECT * FROM GAMEPROFILES;");
			while (rs.next()) {
				String profileNameResStr = rs.getString("profilename");
				String characterNameResStr = rs.getString("charactername");
				String weaponNameResStr = rs.getString("weaponname");
				int difficultyResInt = rs.getInt("difficulty");
				int experienceResInt = rs.getInt("experience");

				MainWindow.updateTextArea("PROFILENAME = " + profileNameResStr + "\n" + "CHARACTERNAME = "
						+ characterNameResStr + "\n" + "WEAPONNAME = " + weaponNameResStr + "\n" + "DIFFICULTY = "
						+ difficultyResInt + "\n" + "EXPERIENCE = " + experienceResInt + "\n\n");
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			MainWindow.updateTextArea(e.getClass().getName() + ": " + e.getMessage() + "\n");
			return false;
			// System.exit(0);
		}
		MainWindow.updateTextArea("Delete operation done successfully\n\n");
		return true;
	}
}