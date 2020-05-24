import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * 
 * @author Ben Dai, Leon Li, Eric Han, Phillip Zhang
 * superclass obstacle
 *
 */
public abstract class Obstacles extends JComponent
{
	
	public abstract int setY();
	
	public abstract int setX();
	
	public abstract int getY();
	
	public abstract int getX();
	
	
}
