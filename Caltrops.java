/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * caltrops obstacle 
 *
 */
public class Caltrops extends Obstacles
{
	int num = 5; 
	public Caltrops(int x, int y, int width, int height)
	{
		setLocation(x, y);
		
		setSize(width, height);
	}
	public int getNum()
	{
		return num;
	}
}
