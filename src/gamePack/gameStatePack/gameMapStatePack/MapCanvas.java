package gamePack.gameStatePack.gameMapStatePack;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
//import java.nio.file.Files;
//import java.nio.file.LinkOption;
//import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class MapCanvas extends Canvas {

	private static final long serialVersionUID = 4487681749374778705L;

	MediaTracker mt = new MediaTracker(this);

	Image offscreen;
	Dimension offscreenSize;

	static Graphics2D offGraphics;

	ArrayList<EntityCanvas> entities;
	int mtCount = 0;

	MapCanvas(EntityCanvas... entities) {

		super();

		this.entities = new ArrayList<>();

		for (EntityCanvas entity : entities) {
			this.entities.add(entity);
			entity.initEntity();
			for (Image entityImage : entity.entityImgs)
				mt.addImage(entityImage, mtCount++);
		}

		initBg();

		try {
			for (int k = 0; k < mtCount; k++)
				mt.waitForID(k);

		} catch (java.lang.InterruptedException e) {
			MainWindow.updateTextArea("Couldn't load one of the images\n");
		}
	}

	// public static void main(String args[]) {
	// JFrame mainFrame = new JFrame("Graphics demo");
	// mainFrame.getContentPane().add(new MapCanvas());
	// mainFrame.pack();
	// mainFrame.setVisible(true);
	// }

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1115, 715);
	}

	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public void paint(Graphics g) {
		update(g);
	}

	@Override
	public void update(Graphics g) {
		Dimension d = getSize();
		if ((offscreen == null) || (d.width != offscreenSize.width) || (d.height != offscreenSize.height)) {

			int w = this.getWidth(), h = this.getHeight();
			this.setBounds(0, 0, w, h);
			d.setSize(w, h);

			// offscreen = createImage(d.width, d.height);
			offscreen = createImage(w, h);
			offscreenSize = d;
			offGraphics = (Graphics2D) offscreen.getGraphics();
			offGraphics.setFont(new Font("Helvetica", Font.BOLD, 18));

		}
		bgPaint();

		for (EntityCanvas c : entities)
			if (c != null && c.gameCharacter != null && !c.gameCharacter.isDead())
				c.entityPaint();
		bgMaskPaint();
		/*
		 * for(EntityCanvas c: entities) c.entityPaint();
		 */

		g.drawImage(offscreen, 0, 0, null);
		g.dispose();
	}

	private static final String worldMapPathStr = "image/worldMap.gif", gameMapPathStr = "image/gameMap.jpg",
			snowMapPathStr = "image/snowMap.png", townMapPathStr = "image/townMap.jpg",
			volcanoMapPathStr = "image/volcanoMap.jpg";
	static ArrayList<String> mapPaths = new ArrayList<>(
			Arrays.asList(worldMapPathStr, gameMapPathStr, snowMapPathStr, townMapPathStr, volcanoMapPathStr));
	static Image mapImgs[] = new Image[mapPaths.size()];

	static int worldMap = 0, gameMap = 1, snowMap = 2, townMap = 3;

	public static int volcanoMap = 4;

	public static int mapState = gameMap;

	ArrayList<Integer> mapStates = new ArrayList<>(Arrays.asList(worldMap, gameMap, snowMap, townMap, volcanoMap));

	// private static BufferedImage bgMask = new BufferedImage(WIDTH, HEIGHT,
	// BufferedImage.TYPE_INT_ARGB);
	private static BufferedImage bgMask = new BufferedImage(1400, 800, BufferedImage.TYPE_INT_ARGB);
	private static final Graphics2D bgMaskG2D = bgMask.createGraphics();

	void initBg() {
		// if(Files.exists(Paths.get(bgPathStr), LinkOption.NOFOLLOW_LINKS)) {
		// bg = Toolkit.getDefaultToolkit().createImage(bgPathStr);
		// mt.addImage(bg, 0);
		// }
		// else {
		// System.out.println(bgPathStr+" was not found");
		// System.exit(-1);
		// }

		int i = 0;
		for (String path : mapPaths) {
			//if (Files.exists(Paths.get(path), LinkOption.NOFOLLOW_LINKS)) {
				mapImgs[i] = Toolkit.getDefaultToolkit().createImage(path);
				mt.addImage(mapImgs[i], mtCount);
/*			} else {
				MainWindow.updateTextArea(path + " was not found\n");
				//System.exit(-1);
			}*/
			i++;
			mtCount++;

		}
	}

	void bgPaint() {
		offGraphics.setColor(getBackground());
		offGraphics.fill(getBounds());
		offGraphics.drawImage(mapImgs[mapState], 0, 0, getWidth(), getHeight(), null);
	}

	private void bgMaskPaint() {

		/* Draw the grey rectangle */
		bgMaskG2D.setColor(Color.GRAY);
		bgMaskG2D.fillRect(0, 0, getWidth(), getHeight());

		/* Enable Anti-Alias */
		bgMaskG2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		/* Clear the circle away */
		bgMaskG2D.setComposite(AlphaComposite.Clear);
		int ovalX = MainWindow.knight0_Canvas.getEntityCurX();
		int ovalY = MainWindow.knight0_Canvas.getEntityCurY();
		int ovalRadius = MainWindow.knight0_Canvas.entityVisibleRadius;/*
																		 * entities
																		 * .get(
																		 * 0).
																		 * entityImgs
																		 * [0].
																		 * getWidth
																		 * (this
																		 * )*2;
																		 */
		bgMaskG2D.fillOval(ovalX - ovalRadius, ovalY - ovalRadius, 2 * ovalRadius, 2 * ovalRadius);
		bgMaskG2D.setComposite(AlphaComposite.Src);

		offGraphics.drawImage(bgMask, 0, 0, bgMask.getWidth(this), bgMask.getHeight(this), this);
	}

	/*
	 * static final int PLAYERSTOPPED = 0, PLAYERMOVING = 1, PLAYERATTACKING =
	 * 2;
	 * 
	 * private static final ArrayList<Integer> entityStates = new
	 * ArrayList<>(Arrays.asList( PLAYERSTOPPED, PLAYERMOVING, PLAYERATTACKING
	 * ));
	 * 
	 * private static final String playerStoppedPathStr = "image/stopped.gif",
	 * playerAttackingPathStr = "image/attacking.gif", playerMovingPathStr =
	 * "image/moving.gif";
	 * 
	 * private static final ArrayList<String> playerImgPaths = new
	 * ArrayList<>(Arrays.asList( playerStoppedPathStr, playerMovingPathStr,
	 * playerAttackingPathStr )); private static int playerAngle = 0; private
	 * static Image[] playerImgs = new Image[3]; private static AffineTransform
	 * playerAffine;// = new AffineTransform();
	 * 
	 * private static int playerState; private static boolean playerFrozen =
	 * true;
	 * 
	 * void initPlayer() {
	 * 
	 * int i = 0; for(String path: playerImgPaths) {
	 * if(Files.exists(Paths.get(path), LinkOption.NOFOLLOW_LINKS)) {
	 * playerImgs[i] = Toolkit.getDefaultToolkit().createImage(path);
	 * mt.addImage(playerImgs[i], i+1); } else { System.out.println(path+
	 * " was not found"); System.exit(-1); } i++; }
	 * 
	 * AffineTransform newAffine = new AffineTransform(); int tx =
	 * MainWindow.getPlayerCurX()-playerImgs[playerState].getWidth(this)/2; int
	 * ty =
	 * MainWindow.getPlayerCurY()-playerImgs[playerState].getHeight(this)/2;
	 * newAffine.translate(tx, ty); double radians = 2.0 * Math.PI*(1.0 -
	 * (double) playerAngle/360); newAffine.rotate(radians);
	 * setPlayerAffine(newAffine); }
	 * 
	 * void playerPaint() { offGraphics.drawImage(playerImgs[playerState],
	 * getPlayerAffine(), this); }
	 * 
	 * synchronized void setPlayerState(int s) { while (playerFrozen) try {
	 * playerState = 0; this.wait(); } catch (InterruptedException e) {
	 * e.printStackTrace(); } playerState = s; AffineTransform newAffine = new
	 * AffineTransform(); int tx =
	 * MainWindow.getPlayerCurX()-playerImgs[playerState].getWidth(this)/2; int
	 * ty =
	 * MainWindow.getPlayerCurY()-playerImgs[playerState].getHeight(this)/2;
	 * newAffine.translate(tx, ty); double radians = 2.0 * Math.PI*(1.0 -
	 * (double) playerAngle/360); newAffine.rotate(radians);
	 * setPlayerAffine(newAffine); repaint(); }
	 * 
	 * public static synchronized void freezePlayer(){ playerFrozen = true; }
	 * 
	 * public synchronized void thawPlayer() { playerFrozen = false;
	 * notifyAll(); }
	 * 
	 * synchronized static AffineTransform getPlayerAffine() { return
	 * playerAffine; }
	 * 
	 * synchronized static void setPlayerAffine(AffineTransform playerAffine) {
	 * MapCanvas.playerAffine = playerAffine; }
	 * 
	 * static final int ENEMYSTOPPED = 0, ENEMYMOVING = 1, ENEMYATTACKING = 2;
	 * //static final int[] ENEMYSTOPPEDBOUNDS = {0,0,75,35};
	 * 
	 * private static final ArrayList<Integer> enemyStates = new
	 * ArrayList<>(Arrays.asList( ENEMYSTOPPED, ENEMYMOVING, ENEMYATTACKING ));
	 * 
	 * private static final String enemyStoppedPathStr = "image/stopped.gif",
	 * enemyAttackingPathStr = "image/attacking.gif", enemyMovingPathStr =
	 * "image/moving.gif"; //enemyImgsPathStr =
	 * "image/Genesis 32X SCD - Aladdin - SnakeEnemy.gif";
	 * 
	 * private static final ArrayList<String> enemyImgPaths = new
	 * ArrayList<>(Arrays.asList( enemyStoppedPathStr, enemyMovingPathStr,
	 * enemyAttackingPathStr )); private static AffineTransform enemyAffine;// =
	 * new AffineTransform(); private Image[] enemyImgs = new Image[3]; private
	 * int enemyState; private static boolean enemyFrozen = true; private double
	 * enemyAngle = 0;
	 * 
	 * void initEnemy() {
	 * 
	 * int j = 0; for(String path: enemyImgPaths) {
	 * if(Files.exists(Paths.get(path), LinkOption.NOFOLLOW_LINKS)) {
	 * enemyImgs[j] = Toolkit.getDefaultToolkit().createImage(path);
	 * mt.addImage(enemyImgs[j], j+3); } else { System.out.println(path+
	 * " was not found"); System.exit(-1); } j++; }
	 * 
	 * AffineTransform newAffine = new AffineTransform(); int tx =
	 * MainWindow.getEnemyCurX()-enemyImgs[enemyState].getWidth(this)/2; int ty
	 * = MainWindow.getEnemyCurY()-enemyImgs[enemyState].getHeight(this)/2;
	 * newAffine.translate(tx, ty); double radians = 2.0 * Math.PI*(1.0 -
	 * (double) playerAngle/360); newAffine.rotate(radians);
	 * setEnemyAffine(newAffine); }
	 * 
	 * 
	 * void enemyPaint() { offGraphics.drawImage(enemyImgs[enemyState],
	 * getEnemyAffine(), this); }
	 * 
	 * synchronized void setEnemyState(int s) { while (enemyFrozen) try {
	 * enemyState = 0; this.wait(); } catch (InterruptedException e) {
	 * e.printStackTrace(); } enemyState = s; AffineTransform newAffine = new
	 * AffineTransform(); int tx =
	 * MainWindow.getEnemyCurX()-enemyImgs[enemyState].getWidth(this)/2; int ty
	 * = MainWindow.getEnemyCurY()-enemyImgs[enemyState].getHeight(this)/2;
	 * newAffine.translate(tx, ty); double radians = 2.0 * Math.PI*(1.0 -
	 * (double) enemyAngle/360); newAffine.rotate(radians);
	 * setEnemyAffine(newAffine); repaint(); }
	 * 
	 * public synchronized static void freezeEnemy(){ enemyFrozen = true; }
	 * 
	 * public synchronized void thawEnemy() { enemyFrozen = false; notifyAll();
	 * }
	 * 
	 * synchronized static AffineTransform getEnemyAffine() { return enemyAffine
	 * = new AffineTransform(); //took awhile to find this for some reason
	 * return enemyAffine; }
	 * 
	 * synchronized static void setEnemyAffine(AffineTransform enemyAffine) {
	 * MapCanvas.enemyAffine = enemyAffine; }
	 * 
	 * 
	 */

}