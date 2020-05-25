import javax.swing.ImageIcon;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * opposing ninjas
 *
 */
public class EnemyNinja extends Obstacles
{
	
	public EnemyNinja(int x, int y, int width, int height)
	{
		
		super(width, height, new ImageIcon("EnemyNinja.png"));
		setBounds(x, y, width, height);
		
	}
	
}
