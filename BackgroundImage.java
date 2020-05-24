import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * background
 *
 */
public class BackgroundImage extends JPanel
{

	public BackgroundImage(int w, int h)
	{
		
		setSize(w, h);
		setLayout(null);
		
		try
		{
			
			BufferedImage bImg = ImageIO.read(new File("NinjaBackground.jpg"));
			JLabel label = new JLabel(new ImageIcon(bImg));
			label.setBounds(0, 0, getWidth(), getHeight());
			add(label);
			
		}
		catch (IOException e) {}
		//
		setVisible(true);
		
	}
	
}
