package gamePack.gameStatePack.gameMapStatePack;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import gamePack.gameEntityPack.gameCharacterPack.GameCharacterInterface;
import gamePack.gameEntityPack.gameCharacterPack.gameEnemyPack.DragonEnemy;
import gamePack.gameEntityPack.gameCharacterPack.gameEnemyPack.SnakeEnemy;
import gamePack.gameEntityPack.gameCharacterPack.gameEnemyPack.TrollEnemy;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.GamePlayerInterface;
import gamePack.gameEntityPack.gameCharacterPack.gamePlayerPack.KnightPlayer;
import gamePack.gameStatePack.GameStateContext;
import gamePack.gameStatePack.gameCombatState.CombatShenanigans;

public class EntityCanvas {

	int id;

	Boolean isEntityMoving = false;
	Integer EntityID = 0, entityInitX = 200, entityInitY = 600, entityCurX = entityInitX, entityCurY = entityInitY,
			entityMoveSleepMillis = 10, entityStepPixels = 2, entityVisibleRadius = 200, entityCollisionRadius = 20;
	GameCharacterInterface gameCharacter;

	public EntityCanvas(int id) {
		this.id = id;
	}

	// *********** BEGIN KNIGHT CODE ****************
	final int knightStopped = 0, knightMoving = 1, knightAttacking = 2;
	final String knightStoppedPathStr = "image/knightStopped.gif", knightAttackingPathStr = "image/knightAttacking.gif",
			knightMovingPathStr = "image/knightMoving.gif";
	synchronized void setKnightState(int s) {
		while (entityFrozen || this.gameCharacter.isDead())
			try {
				entityState = knightStopped;
				MainWindow.mapCanvas.repaint();
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		entityState = s;
		// int tx0 = (int) getEntityAffine().getTranslateX(), ty0 = (int)
		// getEntityAffine().getTranslateY();
		AffineTransform newAffine = new AffineTransform();
		int tx = this.getEntityCurX() - entityImgs[entityState].getWidth(null) / 2;
		int ty = this.getEntityCurY() - entityImgs[entityState].getHeight(null) / 2;
		newAffine.translate(tx, ty);
		// if((tx-tx0)>0)
		// newAffine.scale(1, -1);
		// else
		// newAffine.scale(-1, -1);
		double radians = 2.0 * Math.PI * (1.0 - (double) entityAngle / 360);
		newAffine.rotate(radians);
		setEntityAffine(newAffine);
		notifyAll();
	}
	static Thread makeKnight(EntityCanvas entity) {
		entity.gameCharacter = new KnightPlayer();
		entity.setIsEntityMoving(false);
		entity.entityInitX = 200;
		entity.entityInitY = 600;
		entity.setEntityCurX(entity.entityInitX);
		entity.setEntityCurY(entity.entityInitY);
		entity.entityMoveSleepMillis = 10;
		entity.entityStepPixels = 2;
		entity.entityVisibleRadius = 200;
		entity.entityCollisionRadius = 20;
		return new Thread(new Runnable() {
			@Override
			public synchronized void run() {
				if (entity != null)
					while (true) {
						try {
							// mapCanvas.entities.get(id).setKnightState(0);
							// mapCanvas.repaint();
							// Thread.sleep(100);

							entity.setKnightState(1);
							MainWindow.mapCanvas.repaint();
							wait(100);

							entity.setKnightState(2);
							MainWindow.mapCanvas.repaint();
							wait(100);
						} catch (java.lang.InterruptedException e) {
						}
					}
			}
		});
	}
	// *********** END KNIGHT CODE ****************

	// *********** BEGIN SNAKE CODE ****************
	final int snakeStopped = 3, snakeMoving = 4, snakeAttacking = 5;
	final String snakeStoppedPathStr = "image/snake0.png", snakeAttackingPathStr = "image/snake1.png",
			snakeMovingPathStr = "image/snake2.png";
	synchronized void setSnakeState(int s) {
		while (entityFrozen || this.gameCharacter.isDead())
			try {
				entityState = snakeStopped;
				MainWindow.mapCanvas.repaint();
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		entityState = s;
		AffineTransform newAffine = new AffineTransform();
		int tx = this.getEntityCurX() - entityImgs[entityState].getWidth(null) / 2;
		int ty = this.getEntityCurY() - entityImgs[entityState].getHeight(null) / 2;
		newAffine.translate(tx, ty);
		double radians = 2.0 * Math.PI * (1.0 - (double) entityAngle / 360);
		newAffine.rotate(radians);
		setEntityAffine(newAffine);
		notifyAll();
	}
	static Thread makeSnake(EntityCanvas entity) {
		entity.gameCharacter = new SnakeEnemy();
		entity.gameCharacter.setStrength(entity.gameCharacter.getStrength()*GameStateContext.getDifficulty());
		entity.setIsEntityMoving(false);
		entity.entityInitX = 200;
		entity.entityInitY = 200;
		entity.setEntityCurX(entity.entityInitX);
		entity.setEntityCurY(entity.entityInitY);
		entity.entityMoveSleepMillis = 30;
		entity.entityStepPixels = 2;
		entity.entityVisibleRadius = 200;
		entity.entityCollisionRadius = 10;
		return new Thread(new Runnable() {
			@Override
			public synchronized void run() {
				if (entity != null)
					while (!entity.gameCharacter.isDead()) {
						try {
							// mapCanvas.entities.get(id).setSnakeState(3);
							// mapCanvas.repaint();
							// Thread.sleep(100);

							entity.setSnakeState(4);
							MainWindow.mapCanvas.repaint();
							wait(100);

							entity.setSnakeState(5);
							MainWindow.mapCanvas.repaint();
							wait(100);
						} catch (java.lang.InterruptedException e) {
						}
					}
			}
		});
	}
	static void makeSnakes(ArrayList<EntityCanvas> entityCanvases, ArrayList<Thread> entityThreads) {
		for (int i = 0; i < entityCanvases.size(); i++) {
			EntityCanvas curCanvas = entityCanvases.get(i);
			Thread curThread = entityThreads.get(i);
			if (!MainWindow.mapCanvas.entities.contains(curCanvas)) {
				curCanvas = new EntityCanvas(MainWindow.getNewEntityID());
				MainWindow.mapCanvas.entities.add(curCanvas);
				curCanvas.initEntity();
				for (Image entityImage : curCanvas.entityImgs)
					MainWindow.mapCanvas.mt.addImage(entityImage, MainWindow.mapCanvas.mtCount++);
				curThread = EntityCanvas.makeSnake(curCanvas);
				MainWindow.entityThreads.add(curThread);
				curThread.start();
			}
			curCanvas.setEntityCurX((int) (Math.random() * MainWindow.mapCanvas.getWidth()));
			curCanvas.setEntityCurY((int) (Math.random() * MainWindow.mapCanvas.getHeight()));
		}
	}
	// *********** END SNAKE CODE ****************

	// *********** BEGIN DRAGON CODE ****************
	final int dragonStopped = 6, dragonMoving0 = 7, dragonMoving1 = 8, dragonAttacking = 9;
	final String dragonStoppedPathStr = "image/dragon0.png", dragonAttackingPathStr = "image/dragon1.png",
			dragonMovingUpFlapPathStr = "image/dragon2.png", dragonMovingDownFlapPathStr = "image/dragon3.png";
	synchronized void setDragonState(int s) {
		while (entityFrozen || this.gameCharacter.isDead())
			try {
				entityState = dragonStopped;
				MainWindow.mapCanvas.repaint();
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		entityState = s;
		AffineTransform newAffine = new AffineTransform();
		int tx = this.getEntityCurX() - entityImgs[entityState].getWidth(null) / 2;
		int ty = this.getEntityCurY() - entityImgs[entityState].getHeight(null) / 2;
		newAffine.translate(tx, ty);
		double radians = 2.0 * Math.PI * (1.0 - (double) entityAngle / 360);
		newAffine.rotate(radians);
		setEntityAffine(newAffine);
		notifyAll();
	}
	static Thread makeDragon(EntityCanvas entity) {
		entity.gameCharacter = new DragonEnemy();
		entity.gameCharacter.setStrength(entity.gameCharacter.getStrength()*GameStateContext.getDifficulty());
		entity.setIsEntityMoving(false);
		entity.entityInitX = 500;
		entity.entityInitY = 500;
		entity.setEntityCurX(entity.entityInitX);
		entity.setEntityCurY(entity.entityInitY);
		entity.entityMoveSleepMillis = 30;
		entity.entityStepPixels = 2;
		entity.entityVisibleRadius = 400;
		entity.entityCollisionRadius = 30;
		return new Thread(new Runnable() {
			@Override
			public synchronized void run() {
				if (entity != null)
					while (!entity.gameCharacter.isDead()) {
						try {
							// mapCanvas.entities.get(id).setDragonState(6);
							// mapCanvas.repaint();
							// Thread.sleep(100);

							entity.setDragonState(7);
							MainWindow.mapCanvas.repaint();
							wait(100);

							entity.setDragonState(8);
							MainWindow.mapCanvas.repaint();
							wait(100);

							entity.setDragonState(9);
							MainWindow.mapCanvas.repaint();
							wait(100);

						} catch (java.lang.InterruptedException e) {
						}
					}
			}
		});
	}
	static void makeDragons(ArrayList<EntityCanvas> entityCanvases, ArrayList<Thread> entityThreads) {
		for (int i = 0; i < entityCanvases.size(); i++) {
			EntityCanvas curCanvas = entityCanvases.get(i);
			Thread curThread = entityThreads.get(i);
			if (!MainWindow.mapCanvas.entities.contains(curCanvas)) {
				curCanvas = new EntityCanvas(MainWindow.getNewEntityID());
				MainWindow.mapCanvas.entities.add(curCanvas);
				curCanvas.initEntity();
				for (Image entityImage : curCanvas.entityImgs)
					MainWindow.mapCanvas.mt.addImage(entityImage, MainWindow.mapCanvas.mtCount++);
				curThread = EntityCanvas.makeDragon(curCanvas);
				MainWindow.entityThreads.add(curThread);
				curThread.start();
			}
			curCanvas.setEntityCurX((int) (Math.random() * MainWindow.mapCanvas.getWidth()));
			curCanvas.setEntityCurY((int) (Math.random() * MainWindow.mapCanvas.getHeight()));
		}
	}
	// *********** END DRAGON CODE ****************

	// *********** BEGIN TROLL CODE ****************
	final int trollStopped = 10, trollMoving = 11, trollAttacking = 12;
	final String trollStoppedPathStr = "image/troll0.png", trollAttackingPathStr = "image/troll1.png",
			trollMovingPathStr = "image/troll2.png";
	synchronized void setTrollState(int s) {
		while (entityFrozen || this.gameCharacter.isDead())
			try {
				entityState = trollStopped;
				MainWindow.mapCanvas.repaint();
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		entityState = s;
		AffineTransform newAffine = new AffineTransform();
		int tx = this.getEntityCurX() - entityImgs[entityState].getWidth(null) / 2;
		int ty = this.getEntityCurY() - entityImgs[entityState].getHeight(null) / 2;
		newAffine.translate(tx, ty);
		double radians = 2.0 * Math.PI * (1.0 - (double) entityAngle / 360);
		newAffine.rotate(radians);
		setEntityAffine(newAffine);
		notifyAll();
	}
	static Thread makeTroll(EntityCanvas entity) {
		entity.gameCharacter = new TrollEnemy();
		entity.gameCharacter.setStrength(entity.gameCharacter.getStrength()*GameStateContext.getDifficulty());
		entity.setIsEntityMoving(false);
		entity.entityInitX = 500;
		entity.entityInitY = 500;
		entity.setEntityCurX(entity.entityInitX);
		entity.setEntityCurY(entity.entityInitY);
		entity.entityMoveSleepMillis = 30;
		entity.entityStepPixels = 2;
		entity.entityVisibleRadius = 400;
		entity.entityCollisionRadius = 30;
		return new Thread(new Runnable() {
			@Override
			public synchronized void run() {
				if (entity != null)
					while (!entity.gameCharacter.isDead()) {
						try {
							// mapCanvas.entities.get(id).settrollState(10);
							// mapCanvas.repaint();
							// Thread.sleep(100);

							entity.setTrollState(11);
							MainWindow.mapCanvas.repaint();
							wait(100);

							entity.setTrollState(12);
							MainWindow.mapCanvas.repaint();
							wait(100);
						} catch (java.lang.InterruptedException e) {
						}
					}
			}
		});
	}
	static void makeTrolls(ArrayList<EntityCanvas> entityCanvases, ArrayList<Thread> entityThreads) {
		for (int i = 0; i < entityCanvases.size(); i++) {
			EntityCanvas curCanvas = entityCanvases.get(i);
			Thread curThread = entityThreads.get(i);
			if (!MainWindow.mapCanvas.entities.contains(curCanvas)) {
				curCanvas = new EntityCanvas(MainWindow.getNewEntityID());
				MainWindow.mapCanvas.entities.add(curCanvas);
				curCanvas.initEntity();
				for (Image entityImage : curCanvas.entityImgs)
					MainWindow.mapCanvas.mt.addImage(entityImage, MainWindow.mapCanvas.mtCount++);
				curThread = EntityCanvas.makeTroll(curCanvas);
				MainWindow.entityThreads.add(curThread);
				curThread.start();
			}
			curCanvas.setEntityCurX((int) (Math.random() * MainWindow.mapCanvas.getWidth()));
			curCanvas.setEntityCurY((int) (Math.random() * MainWindow.mapCanvas.getHeight()));
		}
	}
	// *********** END TROLL CODE ****************
	
	
	

	// final ArrayList<Integer> entityStates = new
	// ArrayList<>(Arrays.asList(knightStopped, knightMoving, knightAttacking,
	// snakeStopped, snakeMoving, snakeAttacking, dragonStopped, dragonMoving0,
	// dragonMoving1, dragonAttacking,
	// trollStopped, trollMoving, trollAttacking));

	final ArrayList<String> entityImgPaths = new ArrayList<>(Arrays.asList(knightStoppedPathStr, knightMovingPathStr,
			knightAttackingPathStr, snakeStoppedPathStr, snakeAttackingPathStr, snakeMovingPathStr,
			dragonStoppedPathStr, dragonAttackingPathStr, dragonMovingUpFlapPathStr, dragonMovingDownFlapPathStr,
			trollStoppedPathStr, trollAttackingPathStr, trollMovingPathStr));

	int entityAngle = 0;
	Image[] entityImgs = new Image[entityImgPaths.size()];
	AffineTransform entityAffine;

	int entityState;
	boolean entityFrozen = true;

	void initEntity() {

		int i = 0;
		for (String path : entityImgPaths) {
			if (Files.exists(Paths.get(path), LinkOption.NOFOLLOW_LINKS)) {
				entityImgs[i] = Toolkit.getDefaultToolkit().createImage(path);
			} else {
				MainWindow.updateTextArea(path + " was not found\n");
				MainWindow.updateTextArea("Working Directory = " +
			              System.getProperty("user.dir"));
				//System.exit(-1);
			}
			i++;
		}

		AffineTransform newAffine = new AffineTransform();
		int tx = this.getEntityCurX() - entityImgs[entityState].getWidth(null) / 2;
		int ty = this.getEntityCurY() - entityImgs[entityState].getHeight(null) / 2;
		newAffine.translate(tx, ty);
		double radians = 2.0 * Math.PI * (1.0 - (double) entityAngle / 360);
		newAffine.rotate(radians);
		setEntityAffine(newAffine);
	}

	void entityPaint() {
		/*
		 * String state =
		 * GameStateContext.getState().getClass().getSimpleName();
		 * if(!this.gameCharacter.isDead() && state.contains("MapState"))
		 */
		MapCanvas.offGraphics.drawImage(entityImgs[entityState], getEntityAffine(), null);
	}

	public synchronized void freezeEntity() {
		this.entityFrozen = true;
	}

	public synchronized void thawEntity() {
		entityFrozen = false;
		notifyAll();
	}

	synchronized AffineTransform getEntityAffine() {
		return this.entityAffine;
	}

	synchronized void setEntityAffine(AffineTransform entityAffine) {
		this.entityAffine = entityAffine;
	}

	void moveEntity(int x, int y) {
		EntityCanvas entity = this;
		final Thread mover = new Thread(new Runnable() {
			@Override
			public void run() {
				setIsEntityMoving(true);
				int x0 = getEntityCurX();
				int y0 = entity.getEntityCurY();

				double dx = MainWindow.getXClicked() - x0;
				double dy = MainWindow.getYClicked() - y0;
				double ds = Math.sqrt(dx * dx + dy * dy);
				//double m = dy / dx;
				//double b = MainWindow.getYClicked() - m * MainWindow.getXClicked();
				int xr = (int) Math.round(x0 + entity.entityStepPixels * (dx / ds)),
						yr = (int) Math.round(y0 + entity.entityStepPixels * (dy / ds));
				while (ds > entity.entityStepPixels && !MainWindow.isGamePaused()) {
					entity.thawEntity();
					setEntityCurX(xr);
					setEntityCurY(yr);
					x0 = entity.getEntityCurX();
					y0 = entity.getEntityCurY();
					dx = MainWindow.getXClicked() - x0;
					dy = MainWindow.getYClicked() - y0;
					ds = Math.sqrt(dx * dx + dy * dy);
					//m = dy / dx;
					//b = MainWindow.getYClicked() - m * MainWindow.getXClicked();
					xr = (int) Math.round((x0 + entity.entityStepPixels * (dx / ds)));
					yr = (int) Math.round((y0 + entity.entityStepPixels * (dy / ds)));
					try {
						Thread.sleep(entity.entityMoveSleepMillis);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				entity.setIsEntityMoving(false);
				entity.freezeEntity();
			}
		});
		if (!entity.getIsEntityMoving() && !MainWindow.isGamePaused())
			mover.start();
	}

	void pursueEntity(EntityCanvas srcEntity, EntityCanvas dstEntity) {
		Thread pursuer = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!MainWindow.isGamePaused() && !srcEntity.gameCharacter.isDead()) {
					if (distance(srcEntity.getEntityCurX(), srcEntity.getEntityCurY(), dstEntity.getEntityCurX(),
							dstEntity.getEntityCurY()) < srcEntity.entityVisibleRadius) {
						setIsEntityMoving(true);
						double ds;
						int x0 = srcEntity.getEntityCurX();
						int y0 = srcEntity.getEntityCurY();
						double dx = dstEntity.getEntityCurX() - x0;
						double dy = dstEntity.getEntityCurY() - y0;
						ds = Math.sqrt(dx * dx + dy * dy);
						//double m = dy / dx;
						//double b = dstEntity.getEntityCurY() - m * dstEntity.getEntityCurX();
						int xr = (int) Math.round(x0 + srcEntity.entityStepPixels * (dx / ds)),
								yr = (int) Math.round(y0 + srcEntity.entityStepPixels * (dy / ds));
						while (ds < srcEntity.entityVisibleRadius
								&& ds > (srcEntity.entityCollisionRadius + dstEntity.entityCollisionRadius)
								&& srcEntity.getIsEntityMoving() && !MainWindow.isGamePaused()) {
							srcEntity.thawEntity();
							setEntityCurX(xr);
							setEntityCurY(yr);
							x0 = srcEntity.getEntityCurX();
							y0 = srcEntity.getEntityCurY();
							dx = dstEntity.getEntityCurX() - x0;
							dy = dstEntity.getEntityCurY() - y0;
							ds = Math.sqrt(dx * dx + dy * dy);
							//m = dy / dx;
							//b = dstEntity.getEntityCurY() - m * dstEntity.getEntityCurX();
							xr = (int) Math.round((x0 + srcEntity.entityStepPixels * (dx / ds)));
							yr = (int) Math.round((y0 + srcEntity.entityStepPixels * (dy / ds)));
							try {
								Thread.sleep(srcEntity.entityMoveSleepMillis);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

						if (ds <= (srcEntity.entityCollisionRadius + dstEntity.entityCollisionRadius)) {
							synchronized (srcEntity.isEntityMoving) {
								if (srcEntity.isEntityMoving == true)
									MainWindow.txtrTextarea_1.append("entity" + srcEntity.id + ", "
											+ srcEntity.gameCharacter.getName() + " engaged you.\n");
								srcEntity.setIsEntityMoving(false);
								srcEntity.freezeEntity();
							}
							setEntityCurX(dstEntity.entityInitX);
							setEntityCurY(dstEntity.entityInitY);
							MainWindow.setGamePaused(true);

							MainWindow.btnPause.getAction().putValue("NAME", "PLAY");
							MainWindow.btnPause.getAction().putValue("SHORT_DESCRIPTION", "PLAY GAME");
							MainWindow.btnPause.setText("PLAY");

							MainWindow.updateTextArea(" XP=" + ((GamePlayerInterface) dstEntity.gameCharacter).getExperience()
									+ " profileName=" + ((GamePlayerInterface) dstEntity.gameCharacter).getProfileInfo() + "\n");
							ArrayList<GameCharacterInterface> players = new ArrayList<>(Arrays.asList(dstEntity.gameCharacter)),
									enemies = new ArrayList<>(Arrays.asList(srcEntity.gameCharacter));
							CombatShenanigans combatShenanigans = new CombatShenanigans(players, enemies);
							/*
							 * combatShenanigans.addPlayer((GamePlayerInterface)
							 * (dstEntity.gameCharacter));
							 * combatShenanigans.addEnemy(srcEntity.
							 * gameCharacter);
							 */
							GameStateContext.setState(combatShenanigans);
							GameStateContext.getGameStateContext().run();

							MainWindow.setMapIsVisible(false);
						}

						dstEntity.setIsEntityMoving(false);
						dstEntity.freezeEntity();
					}
				}
			}
		});
		if (!getIsEntityMoving() && !MainWindow.isGamePaused() && !srcEntity.gameCharacter.isDead())
			pursuer.start();
	}

	private Boolean getIsEntityMoving() {
		synchronized (isEntityMoving) {
			return isEntityMoving;
		}
	}

	private void setIsEntityMoving(Boolean isEntityMoving) {
		synchronized (this.isEntityMoving) {
			this.isEntityMoving = isEntityMoving;
		}
	}

	Integer getEntityCurX() {
		synchronized (entityCurX) {
			return entityCurX;
		}
	}

	void setEntityCurX(int curX) {
		synchronized (entityCurX) {
			entityCurX = curX;
		}
	}

	Integer getEntityCurY() {
		synchronized (entityCurY) {
			return entityCurY;
		}
	}

	void setEntityCurY(int curY) {
		synchronized (entityCurY) {
			entityCurY = curY;
		}
	}

	protected int distance(int enemyCurX, int enemyCurY, int playerCurX, int playerCurY) {
		return (int) Math.sqrt(Math.pow(playerCurX - enemyCurX, 2) + Math.pow(playerCurY - enemyCurY, 2));
	}

	// ************************

	// static Boolean isPlayer0_Moving = false;
	// static Integer player0_ID = 0,
	// player0_InitX = 200, player0_InitY = 600,
	// player0_CurX = player0_InitX, player0_CurY = player0_InitY,
	// player0_MoveSleepMillis = 10, player0_StepPixels = 2,
	// player0_VisibleRadius = 200, player0_CollisionRadius = 20;
	//
	//
	//
	//
	// static Boolean isSnake0_Moving = false;
	// static Integer snake0_ID = 1,
	// snake0_InitX = 200, snake0_InitY = 200,
	// snake0_CurX = snake0_InitX, snake0_CurY = snake0_InitY,
	// snake0_MoveSleepMillis = 30, snake0_StepPixels = 2,
	// snake0_VisibleRadius = 200, snake0_CollisionRadius = 10;
	//
	//
	//
	//
	// static Boolean isDragon0_Moving = false;
	// static Integer dragon0_ID = 2,
	// dragon0_InitX = 500, dragon0_InitY = 500,
	// dragon0_CurX = dragon0_InitX, dragon0_CurY = dragon0_InitY,
	// dragon0_MoveSleepMillis = 30,dragon0_StepPixels = 2,
	// dragon0_VisibleRadius = 400, dragon0_CollisionRadius = 30;
	//
	//
	// static Boolean istroll0_Moving = false;
	// static Integer troll0_ID = 3, troll0_InitX = 100, troll0_InitY = 100,
	// troll0_CurX = troll0_InitX, troll0_CurY = troll0_InitY,
	// troll0_MoveSleepMillis = 30, troll0_StepPixels = 2,
	// troll0_VisibleRadius = 200, troll0_CollisionRadius = 20;

	/*
	 * private static ArrayList<Integer> entityID = new ArrayList<>(
	 * Arrays.asList(player0_ID, snake0_ID, dragon0_ID, troll0_ID)); private
	 * static ArrayList<Integer> entityCurX = new ArrayList<>(
	 * Arrays.asList(player0_CurX, snake0_CurX, dragon0_CurX, troll0_CurX));
	 * private static ArrayList<Integer> entityCurY = new ArrayList<>(
	 * Arrays.asList(player0_CurY, snake0_CurY, dragon0_CurY, troll0_CurY));
	 * private static ArrayList<Boolean> entityMoving = new ArrayList<>(
	 * Arrays.asList(isPlayer0_Moving, isSnake0_Moving, isDragon0_Moving,
	 * istroll0_Moving)); private static ArrayList<Integer> entityStepPixels =
	 * new ArrayList<>( Arrays.asList(player0_StepPixels, snake0_StepPixels,
	 * dragon0_StepPixels, troll0_StepPixels)); private static
	 * ArrayList<Integer> entityMoveSleepMillis = new
	 * ArrayList<>(Arrays.asList(player0_MoveSleepMillis,
	 * snake0_MoveSleepMillis, dragon0_MoveSleepMillis,
	 * troll0_MoveSleepMillis)); private static ArrayList<Integer>
	 * entityVisibleRadius = new ArrayList<>(
	 * Arrays.asList(player0_VisibleRadius, snake0_VisibleRadius,
	 * dragon0_VisibleRadius, troll0_VisibleRadius)); private static
	 * ArrayList<Integer> entityCollisionRadius = new
	 * ArrayList<>(Arrays.asList(player0_CollisionRadius,
	 * snake0_CollisionRadius, dragon0_CollisionRadius,
	 * troll0_CollisionRadius));
	 */

}
