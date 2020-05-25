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
 * main frame v5
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
	private boolean jumping;
	private boolean sliding;
	private int timeSlid;
	private int slideTimeMs = 750;
	private boolean threwStar;
	private int ninjaStarTimeMs = 800;
	
	private int gameTickRateMs = 30;
	private int gameSpeed = 14;
	private int accuGameSpeed = 0;
	
	public GameFrame()
	{
		
		super("Ninja Run");
		setBounds(100, 100, 1920, 1080);
		setResizable(false);
		setLayout(null);
		
		hasStarted = false;
		score = 0;
		currObs = new ArrayList<Obstacles>();
		jumping = false;
		sliding = false;
		timeSlid = 0;
		threwStar = false;
		
		BackgroundImage bkgrnd = new BackgroundImage(getWidth(), getHeight());
		bkgrnd.setLocation(0, 0);
		add(bkgrnd);
		
		background = bkgrnd;
		
		player = new PlayerNinja(200, 720, 108, 122);
		background.addToGame(player);
		
		this.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {}

			public void keyPressed(KeyEvent e)
			{
				
				if (e.getKeyCode() == e.VK_SPACE && !hasStarted)
				{
					hasStarted = true;
					background.removeStart();
					spawnNewObs();
				}
				else if (e.getKeyCode() == e.VK_SPACE && hasStarted)
				{
					//unfinished SHOOT SHURIEKN
				}
				else if (e.getKeyCode() == e.VK_W && player.getY() == 720 && !jumping && !sliding)
				{
					velocity = -23;
					jumping = true;
					player.setAction("Jump");
				}
				else if (e.getKeyCode() == e.VK_S && player.getY() == 720 && !sliding && !jumping)
				{
					sliding = true;
					player.setAction("Slide");
					timeSlid = 0;
				}
			}

			public void keyReleased(KeyEvent e) {}
			
		});
		
		t = new Timer(gameTickRateMs, this);
		t.start();
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		
		if (jumping) //jump
		{
			player.setY(player.getY() + velocity);
			velocity = velocity + 1;	
			if (player.getY() >= 720)
			{
				velocity = 0;
				player.setY(720);
				jumping = false;
				player.setAction("Run");
			}
		}
		
		if (sliding) //slide
		{
			timeSlid++;
			if (timeSlid * gameTickRateMs >= slideTimeMs)
			{
				player.setAction("Run");
				sliding = false;
			}
		}
		
		player.update(); //anims
		
		if (score > 500) //update speed of game for difficulty
		{
			gameSpeed = 14 + (score - 500) / 500;
		}
		
		if (hasStarted) //update score
		{
			score++;
			background.updateScore(score);
		}
		
		if (hasStarted)
		{
			accuGameSpeed += gameSpeed; //update game obstacles
			if (accuGameSpeed > 1720)
			{
				accuGameSpeed = 0;
				spawnNewObs();
			}
			for (int i = 0; i < currObs.size(); i++)
			{
				if (currObs.get(i).getX() + currObs.get(i).getWidth() < 0)
				{
					currObs.remove(i);
					i--;
				}
				else
				{
					currObs.get(i).setX(currObs.get(i).getX() - gameSpeed);
					currObs.get(i).update();
				}
			}
		}
		
	}
	
	public void spawnNewObs()
	{
		
        CeilingSpikeBall spikeBall;
        CeilingSpikeBall spikeBall2;
        CeilingSpikeBall spikeBall3;
        EnemyNinja enemyNinja;
        EnemyNinja enemyNinja1;
        EnemyNinja enemyNinja2;
        Caltrops caltrop;
        Caltrops caltrop2;
		Caltrops caltrop3;
		SpikeWall spikeWall;
		SpikeWall spikeWall2;
		SpikeWall spikeWall3;
		Planks planks;
		Planks planks2;
		Planks planks3;
		int obstacle = 5;//(int)(Math.random()*5+1);
		/*if(obstacle == 1)
		{
		        planks = new Planks(2000, , ,);
		        currObs.add(planks);
		        background.addToGame(planks);
		}
		else if(obstacle == 2)
		{
		        spikeBall = new CeilingSpikeBall(2000, , ,);
		        currObs.add(spikeBall);
		        background.addToGame(spikeBall);
		        if(score > 1000)
		        {
		        	spikeBall2 = new CeilingSpikeBall(2000, , ,);
		        	spikeBall2.add(spikeBall2);
		        	background.addToGame(spikeBall2);
		        }
		        if(score > 2000)
		        {
		        	spikeBall3 = new CeilingSpikeBall(2000, , ,);
		        	currObs.add(spikeBall3);
		        	background.addToGame(spikeBall3);
		        }
		}
		else if(obstacle == 3)
		{
		        enemyNinja = new EnemyNinja(2000, , ,);
		        currObs.add(enemyNinja);
		        background.addToGame(enemyNinja);
		        if(score > 1000)
		        {
		        	enemyNinja1 = new EnemyNinja(2000, , ,);
		        	currObs.add(enemyNinja1);
		        	background.addToGame(enemyNinja1);
		        }
		        if(score > 2000)
		        {
		        	enemyNinja2 = new EnemyNinja(2000, , ,);
		        	currObs.add(enemyNinja2);
		        	background.addToGame(enemyNinja2);
		        }
		}
		else if(obstacle == 4)
		{
		        caltrop = new Caltrops(2000, , ,);
		        currObs.add(caltrop);
		        background.addToGame(caltrop);
		        if(score > 1000)
		        {
		        	caltrop2 = new Caltrops(2000, , ,);
		        	currObs.add(caltrop2);
		       	 	background.addToGame(caltrop2);
		        }
		        if(score > 2000)
		        {
		        	caltrop3 = new Caltrops(2000, , ,);
		        	currObs.add(caltrop3);
		        	background.addToGame(caltrop3);
		        }
		}
		else*/ if(obstacle == 5)
		{
		        spikeWall = new SpikeWall(2000, 700, 150, 150);
		        currObs.add(spikeWall);
		        background.addToGame(spikeWall);
		        if(score > 1000)
		        {
		        	spikeWall2 = new SpikeWall(2150, 700, 150, 150);
		        	currObs.add(spikeWall2);
		        	background.addToGame(spikeWall2);
		        }
		        if(score > 2000)
		        {
		        	spikeWall3 = new SpikeWall(2300, 700, 150, 150);
		        	currObs.add(spikeWall3);
		        	background.addToGame(spikeWall3);
		        }
		}

	}
	
}
