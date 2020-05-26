import javax.swing.ImageIcon;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * destructible planks obs
 *
 */
public class Planks extends Obstacles
{

	public Planks(int x, int y, int width, int height)
	{
		super(width, height, new ImageIcon("Planks.png"));
		setBounds(x, y, width, height);
	}
	
}
