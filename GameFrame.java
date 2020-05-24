import javax.swing.JFrame;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * main frame
 *
 */
public class GameFrame extends JFrame
{
	
	public GameFrame()
	{
		
		setBounds(100, 100, 1400, 800);
		setResizable(false);
		setLayout(null);
		
		BackgroundImage background = new BackgroundImage(getWidth(), getHeight());
		background.setLocation(0, 0);
		add(background);//
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
}
