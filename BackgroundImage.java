import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * background
 *
 */
public class BackgroundImage extends JPanel
{
	
	private JLabel labelContainer;
	private JLabel startPrompt;
	private JLabel score;

	public BackgroundImage(int w, int h)
	{
		
		setSize(w, h);
		setLayout(null);
		
		JLabel label = new JLabel(new ImageIcon("NinjaBackground.jpg"));
		label.setBounds(0, 0, getWidth(), getHeight());
		add(label);
		
		labelContainer = label;
		
		startPrompt = new JLabel("Press 'SPACE' to start!");
		startPrompt.setBounds(650, 100, 1000, 250);
		startPrompt.setFont(new Font("", Font.PLAIN, 60));
		startPrompt.setForeground(Color.WHITE);
		label.add(startPrompt);
		
		setVisible(true);
		
	}
	
	public void removeStart()
	{
		labelContainer.remove(startPrompt);
		score = new JLabel("0");
		score.setBounds(900, 100, 1000, 250);
		score.setFont(new Font("", Font.PLAIN, 60));
		score.setForeground(Color.WHITE);
		labelContainer.add(score);
	}
	
	public void updateScore(int score)
	{
		this.score.setText(Integer.toString(score));
		repaint();
	}
	
}
