import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * main frame v1
 *
 */
public class GameFrame extends JFrame implements ActionListener
{
	
	private JLabel startPrompt;
	private BackgroundImage background;
	private boolean hasStarted;
	private Timer t;
	private int score;
	private PlayerNinja player;
	private ArrayList<Obstacles> currObs;
	private int velocity;
	private int objVelocity; 
	private boolean jumping;
	private boolean sliding;
	public GameFrame()
	{
		
		super("Ninja Run");
		setBounds(100, 100, 1920, 1080);
		setResizable(false);
		setLayout(null);
		
		hasStarted = false;
		score = 0;
		currObs = new ArrayList<Obstacles>();
		
		BackgroundImage bkgrnd = new BackgroundImage(getWidth(), getHeight());
		bkgrnd.setLocation(0, 0);
		add(bkgrnd);
		
		background = bkgrnd;
		
		player = new PlayerNinja(200, 700, 108, 122);
		background.addToGame(player);
		
		this.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e)
			{
				
				if(e.getKeyCode() == e.VK_SPACE && !hasStarted)
				{
					hasStarted = true;
					background.removeStart();
				}
				if(e.getKeyCode() == e.VK_W && player.getY() == 700)
				{
					velocity = -23;
					jumping = true;
				}
				if(e.getKeyCode() == e.VK_S && player.getY() == 700)
				{
					sliding = true; 
				}
			}

			public void keyReleased(KeyEvent e) {}
			
		});
		
		/*
         * Spawning code
         * DoorWithButton door;
         * CeilingSpikeBall spikeBall;
         * EnemyNinja enemyNinja;
         * Caltrops caltrops;
         * SpikeWall spikeWall;
         * int obstacle = (int)(Math.random()*5)+1;
         * if(obstacle == 1)
         * {
         *         door = new DoorWithButton(2000, , ,);
         *         add(door);
         * }
         * else if(obstacle == 2)
         * {
         *         spikeBall = new CeilingSpikeBall(2000, , ,);
         *         add(spikeBall);
         * }
         * else if(obstacle == 3)
         * {
         *         enemyNinja = new EnemyNinja(2000, , ,);
         *         add(enemyNinja);
         * }
         * else if(obstacle == 4)
         * {
         *         caltrops = new Caltrops(2000, , ,);
         *         add(caltrops);
         * }
         * else if(obstacle == 5)
         * {
         *         spikeWall = new SpikeWall(2000, , ,);
         *         add(SpikeWall);
         * }
         */
		
		t = new Timer(30, this);
		t.start();
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		if(jumping)
		{
			player.setLocation(200, player.getY() + velocity);
			velocity = velocity + 1;	
			if(player.getY() >= 700)
			{
				velocity = 0;
				player.setY(700);
				jumping = false; 
			}
		}
		player.update();
		
		if (hasStarted)
		{
			score++;
			background.updateScore(score);
		}
		
	}
	
}
