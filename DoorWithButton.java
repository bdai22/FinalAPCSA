/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * door w/ button obstacle
 *
 */
public class DoorWithButton extends Obstacles
{
	int num = 1; 
	public DoorWithButton(int x, int y, int width, int height)
	{
		setLocation(x, y);
		
		setSize(width, height);
	}
	public int getNum()
	{
		return num;
	}
}
