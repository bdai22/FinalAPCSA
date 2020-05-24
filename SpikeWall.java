/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * spike wall obstacle
 *
 */
public class SpikeWall extends Obstacles
{
	int num = 4; 
	public SpikeWall(int x, int y, int width, int height)
	{
		setLocation(x, y);
		
		setSize(width, height);
	}
	public int getNum()
	{
		return num;
	}
}
