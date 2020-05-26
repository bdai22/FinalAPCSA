import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * background v1
 *
 */
public class BackgroundImage extends JPanel
{
	
	private JLabel labelContainer;
	private JLabel startPrompt;
	private JLabel score;
	private JLabel ground;
	private ArrayList<JLabel> gameOverScreen;

	public BackgroundImage(int w, int h)
	{
		
		setSize(w, h);
		setLayout(null);
		
		gameOverScreen = new ArrayList<JLabel>();
		
		JLabel label = new JLabel(new ImageIcon("NinjaBackground.jpg"));
		label.setBounds(0, 0, getWidth(), getHeight());
		add(label);
		
		labelContainer = label;
		
		startPrompt = new JLabel("Press 'SPACE' to start!");
		startPrompt.setBounds(650, 100, 1000, 250);
		startPrompt.setFont(new Font("", Font.PLAIN, 60));
		startPrompt.setForeground(Color.BLACK);
		label.add(startPrompt);
		
		ground = new JLabel(new ImageIcon("Ground 1.png"));
		ground.setBounds(0, 0, 1920, 1080);
		label.add(ground);
		
		setVisible(true);
		
	}
	
	public void removeStart()
	{
		labelContainer.remove(startPrompt);
		score = new JLabel("0");
		score.setBounds(900, 100, 1000, 250);
		score.setFont(new Font("", Font.PLAIN, 60));
		score.setForeground(Color.BLACK);
		labelContainer.add(score);
		
		for (int i = 0; i < gameOverScreen.size(); i++)
		{
			labelContainer.remove(gameOverScreen.get(i));
			gameOverScreen.remove(i);
			i--;
		}
	}
	
	public void addToGame(JComponent jc)
	{
		labelContainer.add(jc);
	}
	
	public void removeFromGame(JComponent jc)
	{
		labelContainer.remove(jc);
	}
	
	public void updateScore(int score)
	{
		this.score.setText(Integer.toString(score));
		repaint();
	}
	
	public void displayGameOver(int score, int timesJumped, int timesSlid, int timesUsedStars, int timesKilledEnemy, int timesDestroyedWall)
	{
		
		labelContainer.remove(this.score);
		
		JLabel gOver = new JLabel("GAME OVER");
		gOver.setBounds(765, 80, 1000, 250);
		gOver.setFont(new Font("", Font.PLAIN, 65));
		gOver.setForeground(Color.BLACK);
		labelContainer.add(gOver);
		gameOverScreen.add(gOver);
		
		JLabel gOverPrompt = new JLabel("Press 'SPACE' to try again!");
		gOverPrompt.setBounds(800, 135, 1000, 250);
		gOverPrompt.setFont(new Font("", Font.PLAIN, 25));
		gOverPrompt.setForeground(Color.BLACK);
		labelContainer.add(gOverPrompt);
		gameOverScreen.add(gOverPrompt);
		
		JLabel scoreL = new JLabel("Score: " + score);
		scoreL.setBounds(800, 250, 1000, 250);
		scoreL.setFont(new Font("", Font.PLAIN, 40));
		scoreL.setForeground(Color.BLACK);
		scoreL.setAlignmentX(LEFT_ALIGNMENT);
		labelContainer.add(scoreL);
		gameOverScreen.add(scoreL);
		
		JLabel jumpL = new JLabel("Times Jumped: " + timesJumped);
		jumpL.setBounds(800, 325, 1000, 250);
		jumpL.setFont(new Font("", Font.PLAIN, 40));
		jumpL.setForeground(Color.BLACK);
		jumpL.setAlignmentX(LEFT_ALIGNMENT);
		labelContainer.add(jumpL);
		gameOverScreen.add(jumpL);
		
		JLabel slidL = new JLabel("Times Slid: " + timesSlid);
		slidL.setBounds(800, 400, 1000, 250);
		slidL.setFont(new Font("", Font.PLAIN, 40));
		slidL.setForeground(Color.BLACK);
		slidL.setAlignmentX(LEFT_ALIGNMENT);
		labelContainer.add(slidL);
		gameOverScreen.add(slidL);
		
		JLabel starsL = new JLabel("Ninja Stars Used: " + timesUsedStars);
		starsL.setBounds(800, 475, 1000, 250);
		starsL.setFont(new Font("", Font.PLAIN, 40));
		starsL.setForeground(Color.BLACK);
		starsL.setAlignmentX(LEFT_ALIGNMENT);
		labelContainer.add(starsL);
		gameOverScreen.add(starsL);
		
		JLabel enemyL = new JLabel("Enemies Killed: " + timesKilledEnemy);
		enemyL.setBounds(800, 550, 1000, 250);
		enemyL.setFont(new Font("", Font.PLAIN, 40));
		enemyL.setForeground(Color.BLACK);
		enemyL.setAlignmentX(LEFT_ALIGNMENT);
		labelContainer.add(enemyL);
		gameOverScreen.add(enemyL);
		
		JLabel wallsL = new JLabel("Walls Destroyed: " + timesDestroyedWall);
		wallsL.setBounds(800, 625, 1000, 250);
		wallsL.setFont(new Font("", Font.PLAIN, 40));
		wallsL.setForeground(Color.BLACK);
		wallsL.setAlignmentX(LEFT_ALIGNMENT);
		labelContainer.add(wallsL);
		gameOverScreen.add(wallsL);
		
	}
	
}
