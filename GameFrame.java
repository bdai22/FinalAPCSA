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
	private ArrayList<NinjaStar> stars;
	
	private int velocity;
	private boolean jumping;
	private boolean sliding;
	private int timeSlid;
	private int slideTimeMs = 750;
	private boolean threwStar;
	private int timeThrewStar;
	private int ninjaStarTimeMs = 1500;
	private boolean died;
	
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
		stars = new ArrayList<NinjaStar>();
		jumping = false;
		sliding = false;
		timeSlid = 0;
		threwStar = false;
		timeThrewStar = 0;
		died = false;
		
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
				
				if (e.getKeyCode() == e.VK_SPACE && !hasStarted && !died)
				{
					hasStarted = true;
					background.removeStart();
					spawnNewObs();
				}
				else if (e.getKeyCode() == e.VK_SPACE && !hasStarted && died)
				{
					//unfinished restart game
				}
				else if (e.getKeyCode() == e.VK_SPACE && hasStarted && !threwStar)
				{
					shootStar();
					threwStar = true;
					timeThrewStar = 0;
				}
				else if (e.getKeyCode() == e.VK_W && player.getY() == 720 && !jumping && !sliding && !died)
				{
					velocity = -23;
					jumping = true;
					player.setAction("Jump");
				}
				else if (e.getKeyCode() == e.VK_S && player.getY() == 720 && !sliding && !jumping && !died)
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
		
		if (threwStar) //star cooldown
		{
			timeThrewStar++;
			if (timeThrewStar * gameTickRateMs >= ninjaStarTimeMs)
			{
				threwStar = false;
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
					background.removeFromGame(currObs.get(i));
					currObs.remove(i);
					i--;
				}
				else
				{
					currObs.get(i).setX(currObs.get(i).getX() - gameSpeed);
					currObs.get(i).update();
					if (player.isTouching(currObs.get(i)) && !(currObs.get(i) instanceof Planks))
					{
						if (currObs.get(i) instanceof EnemyNinja)
						{
							EnemyNinja n = (EnemyNinja)currObs.get(i);
							if (!n.isKilled()) {
								died = true;
							}
						}
						else {
							died = true;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < stars.size(); i++) //update shurikens
		{
			if (hasStarted)
			{
				stars.get(i).update(gameSpeed);
				for (int j = 0; j < currObs.size(); j++)
				{
					if (currObs.get(j) instanceof EnemyNinja && stars.get(i).isTouching(currObs.get(j)))
					{
						EnemyNinja n = (EnemyNinja)currObs.get(j);
						if (!n.isKilled())
						{
							n.kill();
							background.removeFromGame(stars.get(i));
							stars.remove(stars.get(i));
							i--;
						}
					}
//					else if ()
//					{
//						//shuriken touch weakspot
//					}
				}
			}
			else
			{
				background.removeFromGame(stars.get(i));
				stars.remove(stars.get(i));
				i--;
			}
		}
		
		if (died) //game over
		{
			hasStarted = false;
			player.setAction("Dead");
		}
		
	}
	
	public void shootStar()
	{
		NinjaStar star = new NinjaStar(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 30, 30);
		background.addToGame(star);
		stars.add(star);
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
		Caltrops caltrop4;
		Caltrops caltrop5;
		SpikeWall spikeWall;
		SpikeWall spikeWall2;
		SpikeWall spikeWall3;
		Planks woodWall;
		Planks woodWall2;
		Planks woodWall3;
		int obstacle = 3;//(int)(Math.random()*5+1);
		/*if(obstacle == 1)
		{
		        woodWall = new WoodWall(2000, , ,);
		        currObs.add(woodWall);
		        background.addToGame(woodWall);
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
		else*/ if(obstacle == 3)
		{
		        enemyNinja = new EnemyNinja(2000, 720, 145, 145);
		        currObs.add(enemyNinja);
		        background.addToGame(enemyNinja);
		        if(score > 1000)
		        {
		        	enemyNinja1 = new EnemyNinja(2125, 720, 145, 145);
		        	currObs.add(enemyNinja1);
		        	background.addToGame(enemyNinja1);
		        }
		        if(score > 2000)
		        {
		        	enemyNinja2 = new EnemyNinja(2250, 720, 145, 145);
		        	currObs.add(enemyNinja2);
		        	background.addToGame(enemyNinja2);
		        }
		}
		else if(obstacle == 4)
		{
		        caltrop = new Caltrops(2000, 780, 110, 110);
		        currObs.add(caltrop);
		        background.addToGame(caltrop);
		        if(score > 1000)
		        {
		        	caltrop2 = new Caltrops(2075, 780, 110, 110);
		        	currObs.add(caltrop2);
		       	 	background.addToGame(caltrop2);
		        }
		        if(score > 2000)
		        {
		        	caltrop3 = new Caltrops(2150, 780, 110, 110);
		        	currObs.add(caltrop3);
		        	background.addToGame(caltrop3);
		        }
		        if (score > 3000)
		        {
		        	caltrop4 = new Caltrops(2225, 780, 110, 110);
		        	currObs.add(caltrop4);
		        	background.addToGame(caltrop4);
		        }
		        if (score > 4000)
		        {
		        	caltrop5 = new Caltrops(2300, 780, 110, 110);
		        	currObs.add(caltrop5);
		        	background.addToGame(caltrop5);
		        }
		}
		else if(obstacle == 5)
		{
		        spikeWall = new SpikeWall(2000, 700, 150, 150);
		        currObs.add(spikeWall);
		        background.addToGame(spikeWall);
		        if(score > 1000)
		        {
		        	spikeWall2 = new SpikeWall(2100, 700, 150, 150);
		        	currObs.add(spikeWall2);
		        	background.addToGame(spikeWall2);
		        }
		        if(score > 2000)
		        {
		        	spikeWall3 = new SpikeWall(2200, 700, 150, 150);
		        	currObs.add(spikeWall3);
		        	background.addToGame(spikeWall3);
		        }
		}

	}
	
}
