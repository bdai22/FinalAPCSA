/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * chained spike ball obstacle
 *
 */
public class CeilingSpikeBall extends Obstacles
{
	int num = 2; 
	public CeilingSpikeBall(int x, int y, int width, int height)
	{
		setLocation(x, y);
		
		setSize(width, height);
	}
	public int getNum()
	{
		return num;
	}
}
