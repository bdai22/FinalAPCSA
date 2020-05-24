import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * main player ninja
 *
 */
public class PlayerNinja extends JComponent
{
	
	public PlayerNinja(int x, int y, int width, int height)
	{
		
		setLocation(x, y);
		setSize(width, height);
		
	}
	
	public boolean collision(Obstacles block)
	{
		
		if(this.getBounds().intersects(block.getBounds()))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
}
