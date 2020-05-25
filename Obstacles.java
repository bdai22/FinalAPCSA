import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * superclass obstacle v1
 * 
 */
public abstract class Obstacles extends JComponent
{
	
	private JLabel showImage;
	
	public Obstacles(int w, int h, ImageIcon img)
	{
		showImage = new JLabel(img);
		showImage.setBounds(0, 0, w, h);
		add(showImage);
	}
	
	public void setImgIcon(ImageIcon img)
	{
		showImage.setIcon(img);
	}
	
	public void update()
	{
		repaint();
	}
	
	public void setX(int x)
	{
		setLocation(x, getY());
	}
	
}
