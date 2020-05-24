import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * main frame
 *
 */
public class GameFrame extends JFrame
{
	
	private JLabel startPrompt;
	private boolean hasStarted;
	
	public GameFrame()
	{
		
		super("Ninja Run");
		setBounds(100, 100, 1920, 1080);
		setResizable(false);
		setLayout(null);
		
		hasStarted = false;
		
		BackgroundImage background = new BackgroundImage(getWidth(), getHeight());
		background.setLocation(0, 0);
		add(background);
		
		this.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e)
			{
				
				if (e.getKeyCode() == e.VK_SPACE && !hasStarted)
				{
					hasStarted = true;
					background.removeStart();
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
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
}
