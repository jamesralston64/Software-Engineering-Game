package gamePack.gameStatePack.gameMapStatePack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import gamePack.gameStatePack.GameStateInterface;
import gamePack.gameStatePack.GameStateContext;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class MainWindow {
	static MainWindow window;

	private static JFrame frame;
	private static JTextField txtTextfield;
	private static TextFieldStreamer textFieldStreamer;
	private static JTextArea txtrTextarea;
	static JTextArea txtrTextarea_1;
	private static JScrollPane scrollPane;
	private static JScrollPane scrollPane_1;
	private static JLayeredPane layeredPane;
	static Thread knight0_Thread, knight1_Thread, knight2_Thread, snake0_Thread, snake1_Thread, snake2_Thread,
	troll0_Thread, troll1_Thread, troll2_Thread, dragon0_Thread, dragon1_Thread, dragon2_Thread, portal0_Thread,
	portal1_Thread, portal2_Thread;
	static ArrayList<Thread> entityThreads = new ArrayList<Thread>();

	static EntityCanvas knight0_Canvas, knight1_Canvas, knight2_Canvas;
	static EntityCanvas snake0_Canvas, snake1_Canvas, snake2_Canvas;
	static EntityCanvas dragon0_Canvas, dragon1_Canvas, dragon2_Canvas; // 3
	// dragons
	// for
	// crazy
	// mode
	static EntityCanvas troll0_Canvas, troll1_Canvas, troll2_Canvas;

	static ArrayList<EntityCanvas> entityCanvasList = new ArrayList<>(Arrays.asList(knight0_Canvas, snake0_Canvas));

	static MapCanvas mapCanvas;

	private static Integer xClicked = 0;
	private static Integer yClicked = 0;

	private static Boolean isGamePaused = true;
	static JButton btnPause;
	Action pauseAction = new PauseButtonSwingAction();

	public static Boolean mapIsVisible = new Boolean(false);

	private static Integer entityIDCount = 0;

	private static Boolean mapIsNew = true;

	// static GameStateContext gameStateContext;

	public MainWindow() {


		initialize();



	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.GRAY);
		frame.setBounds(0, 0, 1000, 1000);
		frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		makeTextPane();
		/*makeMapPane();*/
		frame.setVisible(true);


	}

	private void makeTextPane() {
		txtrTextarea = new JTextArea();
		txtrTextarea.setEditable(false);
		txtrTextarea.setText("textArea_0 ");
		frame.getContentPane().add(txtrTextarea, BorderLayout.NORTH);
		txtrTextarea.setColumns(10);
		txtrTextarea.setRows(1);

		txtTextfield = new JTextField();

		textFieldStreamer = new TextFieldStreamer(txtTextfield);

		txtTextfield.addActionListener(textFieldStreamer);
		DefaultCaret caret1 = (DefaultCaret) txtTextfield.getCaret();
		caret1.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		System.setIn(textFieldStreamer);

		txtTextfield.setText("textField_0");
		frame.getContentPane().add(txtTextfield, BorderLayout.SOUTH);
		txtTextfield.setColumns(10);

		scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.EAST);

		txtrTextarea_1 = new JTextArea();
		scrollPane.setViewportView(txtrTextarea_1);
		txtrTextarea_1.setEditable(false);
		txtrTextarea_1.setText("textArea_1\n");
		txtrTextarea_1.setRows(3);
		txtrTextarea_1.setColumns(20);
	}

	static void makeMapPane() {
			mapCanvas = new MapCanvas();

			if (knight0_Canvas == null) {
				knight0_Canvas = new EntityCanvas(getNewEntityID());
				mapCanvas.entities.add(knight0_Canvas);
				knight0_Canvas.initEntity();
				for (Image entityImage : knight0_Canvas.entityImgs)
					mapCanvas.mt.addImage(entityImage, mapCanvas.mtCount++);
				knight0_Thread = EntityCanvas.makeKnight(knight0_Canvas);
				entityThreads.add(knight0_Thread);
				knight0_Thread.start();
			}
			knight0_Canvas.setEntityCurX(knight0_Canvas.entityInitX);
			knight0_Canvas.setEntityCurY(knight0_Canvas.entityInitY);

			entityThreads.add(portal0_Thread = MainWindow.makeGameMap2TownMapPortal(knight0_Canvas, 285, 271, MapCanvas.townMap));
			portal0_Thread.start();

			entityThreads.add(portal1_Thread = MainWindow.makeGameMap2SnowMapPortal(knight0_Canvas, 764, 209, MapCanvas.snowMap));
			portal1_Thread.start();

			entityThreads.add(
					portal2_Thread = MainWindow.makeGameMap2VolcanoMapPortal(knight0_Canvas, 862, 405, MapCanvas.volcanoMap));
			portal2_Thread.start();



			btnPause = new JButton("Pause");
			btnPause.setAction(MainWindow.window.pauseAction);
			scrollPane.setColumnHeaderView(btnPause);

			scrollPane_1 = new JScrollPane();
			frame.getContentPane().add(scrollPane_1, BorderLayout.CENTER);

			layeredPane = new JLayeredPane();
			scrollPane_1.setViewportView(layeredPane);
			layeredPane.setLayout(null);

			layeredPane.setLayer(mapCanvas, 0);
			mapCanvas.setBounds(0, 0, 1115, 715);

			// mapCanvas.setBounds(0, 0,
			// MapCanvas.mapImgs[mapCanvas.mapState].getWidth(null),
			// MapCanvas.mapImgs[mapCanvas.mapState].getHeight(null));
			layeredPane.add(mapCanvas);

			mapCanvas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (!isGamePaused()) {
						int x = e.getX(), y = e.getY();
						MainWindow.setXClicked(x);
						MainWindow.setYClicked(y);
						txtrTextarea.setText("textArea_0 " + x + ", " + y);
						txtrTextarea_1.append(x + ", " + y + '\n');
						knight0_Canvas.moveEntity(x, y);
						// pursueEntity(1, player0_ID);
					}
				}
			});

			txtTextfield.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN
							|| e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
						txtrTextarea_1.append(KeyEvent.getKeyText(e.getExtendedKeyCode()) + "\n");
						txtTextfield.setText("");
						Integer keyedStepScale = 20;
						int curX, curY, stepPixels = keyedStepScale * knight0_Canvas.entityStepPixels;

						curX = knight0_Canvas.entityCurX;
						curY = knight0_Canvas.entityCurY;
						if (!isGamePaused && curY >= 0 && curY < mapCanvas.getHeight() && curX >= 0
								&& curX < mapCanvas.getWidth()) {
							switch (e.getKeyCode()) {
							case KeyEvent.VK_UP:
								if (curY - stepPixels > 0)
									curY -= stepPixels;
								break;
							case KeyEvent.VK_DOWN:
								if (curY + stepPixels < mapCanvas.getHeight())
									curY += stepPixels;
								break;
							case KeyEvent.VK_LEFT:
								if (curX - stepPixels > 0)
									curX -= stepPixels;
								break;
							case KeyEvent.VK_RIGHT:
								if (curX + stepPixels < mapCanvas.getWidth())
									curX += stepPixels;
								break;
							default:
								txtrTextarea_1.append(KeyEvent.getKeyText(e.getExtendedKeyCode()) + "\n");
							}
						}
						knight0_Canvas.setEntityCurX(curX);
						knight0_Canvas.setEntityCurY(curY);
						knight0_Canvas.thawEntity();
						mapCanvas.update(mapCanvas.getGraphics());
						knight0_Canvas.freezeEntity();
						txtrTextarea.setText(" (" + curX + ", " + curY + ")");
					}
				}
			});
		


		frame.setVisible(true);
	}


	static Thread makeGameMap2TownMapPortal(EntityCanvas actorEntity, int x, int y,
			int newMapState/* , EntityCanvas[] newEntities */) {
		return new Thread(new Runnable() {
			@Override
			public synchronized void run() {
				while (true) {
					try {
						if (Math.abs(actorEntity.entityCurX - x) < 2 * actorEntity.entityCollisionRadius
								&& Math.abs(actorEntity.entityCurY - y) < 2 * actorEntity.entityCollisionRadius
								&& MapCanvas.mapState == MapCanvas.gameMap) {
							GameStateInterface newState = new TownMapState();
							// GameStateContext.getGameStateContext();
							GameStateContext.setState(newState);
							GameStateContext.getGameStateContext().run();
							break;
						}
						mapCanvas.repaint();
						wait(100);
					} catch (java.lang.InterruptedException e) {
					}
				}

			}
		});
	}

	static Thread makeGameMap2SnowMapPortal(EntityCanvas actorEntity, int x, int y, int newMapState) {
		return new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						if (Math.abs(actorEntity.entityCurX - x) < 2 * actorEntity.entityCollisionRadius
								&& Math.abs(actorEntity.entityCurY - y) < 2 * actorEntity.entityCollisionRadius
								&& MapCanvas.mapState == MapCanvas.gameMap) {
							MapCanvas.mapState = newMapState;

							GameStateInterface newState = new SnowMapState();
							GameStateContext.setState(newState);
							GameStateContext.getGameStateContext().run();
							break;
						}
						mapCanvas.repaint();
						Thread.sleep(100);
					} catch (java.lang.InterruptedException e) {
					}
				}

			}
		});
	}

	static Thread makeGameMap2VolcanoMapPortal(EntityCanvas actorEntity, int x, int y, int newMapState) {
		return new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						if (Math.abs(actorEntity.entityCurX - x) < 2 * actorEntity.entityCollisionRadius
								&& Math.abs(actorEntity.entityCurY - y) < 2 * actorEntity.entityCollisionRadius
								&& MapCanvas.mapState == MapCanvas.gameMap) {
							MapCanvas.mapState = newMapState;

							GameStateInterface newState = new VolcanoMapState();
							GameStateContext.setState(newState);
							GameStateContext.getGameStateContext().run();
							break;
						}
						mapCanvas.repaint();
						Thread.sleep(100);
					} catch (java.lang.InterruptedException e) {
					}
				}

			}
		});
	}

	public static synchronized void updateTextArea(final String text) {
		if (window == null)
			window = new MainWindow();
		//		synchronized (txtrTextarea_1) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// txtrTextarea_1.setText(text);
				txtrTextarea_1.append(text);
				txtTextfield.requestFocus();
				txtrTextarea_1.setCaretPosition(txtrTextarea_1.getDocument().getLength());

			}
		});
		//}
	}

	static int getXClicked() {
		synchronized (xClicked) {
			return xClicked;
		}
	}

	static void setXClicked(int xClicked) {
		synchronized (MainWindow.xClicked) {
			MainWindow.xClicked = xClicked;
		}
	}

	static int getYClicked() {
		synchronized (yClicked) {
			return yClicked;
		}
	}

	static void setYClicked(int yClicked) {
		synchronized (MainWindow.yClicked) {
			MainWindow.yClicked = yClicked;
		}
	}

	static boolean isGamePaused() {
		synchronized (isGamePaused) {
			return isGamePaused;
		}
	}

	static void setGamePaused(boolean isGamePaused) {
		synchronized (MainWindow.isGamePaused) {
			MainWindow.isGamePaused = isGamePaused;
		}
	}

	private class PauseButtonSwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 6138967167992441528L;

		public PauseButtonSwingAction() {
			putValue(NAME, "PLAY");
			putValue(SHORT_DESCRIPTION, "PLAY GAME?");
		}

		@Override
		public synchronized void actionPerformed(ActionEvent e) {
			txtTextfield.requestFocus();
			txtrTextarea_1.setCaretPosition(txtrTextarea_1.getDocument().getLength());
			if (!MainWindow.mapIsVisible() || !GameStateContext.getState().getClass().getSimpleName().contains("MapState"))
				return;
			if (getMapIsNew()) {
				setMapIsNew(false);
				/*
				 * player0_InitX =
				 * (int)(Math.random()*(double)canvas.getWidth()); player0_InitY
				 * = (int)(Math.random()*(double)canvas.getHeight());
				 */
				// player0_CurX = (int) (Math.random() * (double)
				// mapCanvas.getWidth());
				// player0_CurX = (int) (Math.random() * (double)
				// mapCanvas.getHeight());
				//
				// snake0_CurX = (int) (Math.random() * (double)
				// mapCanvas.getWidth());
				// snake0_CurX = (int) (Math.random() * (double)
				// mapCanvas.getHeight());
				//
				// dragon0_CurX = (int) (Math.random() * (double)
				// mapCanvas.getWidth());
				// dragon0_CurX = (int) (Math.random() * (double)
				// mapCanvas.getHeight());
				//
				// goblin0_CurX = (int) (Math.random() * (double)
				// mapCanvas.getWidth());
				// goblin0_CurX = (int) (Math.random() * (double)
				// mapCanvas.getHeight());

				/*
				 * setEntityCurX(srcId, snake0_InitX); setEntityCurY(srcId,
				 * snake0_InitY);
				 */

				knight0_Canvas.setEntityCurX(knight0_Canvas.entityInitX);
				knight0_Canvas.setEntityCurY(knight0_Canvas.entityInitY);
			}
			if (isGamePaused()) {
				setGamePaused(false);
				btnPause.setText("PAUSE");
				for (EntityCanvas c : mapCanvas.entities) {
					if (c.id != knight0_Canvas.id)
						c.pursueEntity(c, knight0_Canvas);
					c.thawEntity();
				}
				// mapCanvas.entities.get(player0_ID).thawEntity();
				// mapCanvas.entities.get(snake0_ID).thawEntity();
				mapCanvas.update(mapCanvas.getGraphics());

				for (EntityCanvas c : mapCanvas.entities) {
					c.freezeEntity();
				}
				// mapCanvas.entities.get(player0_ID).freezeEntity();
				// mapCanvas.entities.get(snake0_ID).freezeEntity();
				putValue(NAME, "PAUSE");
				putValue(SHORT_DESCRIPTION, "PAUSE GAME?");
				this.notifyAll();
			} else {
				setGamePaused(true);
				btnPause.setText("PLAY");
				putValue(NAME, "PLAY");
				putValue(SHORT_DESCRIPTION, "PLAY GAME");
				this.notifyAll();
			}
		}
	}

	public static boolean getMapIsNew() {
		synchronized (MainWindow.mapIsNew) {
			return mapIsNew;
		}
	}

	public static void setMapIsNew(boolean mapIsNew) {
		synchronized (MainWindow.mapIsNew) {
			MainWindow.mapIsNew = mapIsNew;
		}
	}

	static Integer getNewEntityID() {
		synchronized (MainWindow.entityIDCount) {
			return ++entityIDCount;
		}
	}

	public static boolean mapIsVisible() {
		synchronized (MainWindow.mapIsVisible) {
			return MainWindow.mapIsVisible;
		}
	}

	public static void setMapIsVisible(boolean mapIsVisible) {
		synchronized (MainWindow.mapIsVisible) {
			MainWindow.mapIsVisible = mapIsVisible;
		}

	}
}
