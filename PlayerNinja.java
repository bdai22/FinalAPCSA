import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * main player ninja
 *
 */
public class PlayerNinja extends JComponent
{
	
	private int currSprite;
	private JLabel showSprite;
	private ArrayList<JLabel> sprites;
	
	public PlayerNinja(int x, int y, int width, int height)
	{
		
		setLocation(x, y);
		setSize(width, height);
		sprites = new ArrayList<JLabel>();
		for (int i = 1; i <= 6; i++)
		{
			sprites.add(new JLabel(new ImageIcon("Run " + i + ".png")));
		}
		for (int i = 5; i >= 2; i--)
		{
			sprites.add(new JLabel(new ImageIcon("Run " + i + ".png")));
		}
		currSprite = 0;
		showSprite = sprites.get(currSprite);
		showSprite.setBounds(0, 0, width, height);
		add(showSprite);
		
	}
	
	public void update()
	{
		currSprite++;
		if (currSprite == 10)
		{
			currSprite = 0;
		}
		showSprite = sprites.get(currSprite);
		repaint();
	}
	
	public boolean collision(Obstacles block)
	{
		
		if (this.getBounds().intersects(block.getBounds()))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
}
