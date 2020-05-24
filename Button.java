import javax.swing.JComponent;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * button for door
 *
 */
public class Button extends JComponent
{
	int num = 1;
	public Button(int x, int y, int width, int height)
	{
		setLocation(x, y);
		
		setSize(width, height);
	}
	public int getNum()
	{
		return num;
	}
}
