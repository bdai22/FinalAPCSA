import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * main player ninja v1
 *
 */
public class PlayerNinja extends JComponent
{
	
	private int currSprite;
	private JLabel showSprite;
	private ArrayList<ImageIcon> sprites;
	
	public PlayerNinja(int x, int y, int width, int height)
	{
		
		setLocation(x, y);
		setSize(width, height);
		sprites = new ArrayList<ImageIcon>();
		for (int i = 1; i <= 6; i++)
		{
			sprites.add(new ImageIcon("Run " + i + ".png"));
		}
		currSprite = 0;
		showSprite = new JLabel(sprites.get(currSprite));
		showSprite.setBounds(0, 0, width, height);
		add(showSprite);
		
	}
	
	public void update()
	{
		currSprite++;
		if (currSprite == 6)
		{
			currSprite = 0;
		}
		showSprite.setIcon(sprites.get(currSprite));
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
