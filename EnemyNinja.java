/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * opposing ninjas
 *
 */
public class EnemyNinja extends Obstacles
{
	int num = 3; 
	public EnemyNinja(int x, int y, int width, int height)
	{
		setLocation(x, y);
		
		setSize(width, height);
	}
	public int getNum()
	{
		return num;
	}
}
