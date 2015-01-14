package ModifiedPanels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Panel that will show an image in background, 
 * all the other components that will be placed in this panel will get set opaque in order to show the image. * 
 * @author MatteoOrzes
 * source. found some useful suggests here "http://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel"
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final Image image;
	private final int height;
	private final int width;
	/**
	 * Constructor for the class ImagePanel. 
	 * @param filename the path of the Image.
	 * @param dim the future panel dimension. Needed in order to resize the Image to the panel.
	 */
	public ImagePanel(final String filename, final Dimension dim) {
		super();
		this.image = new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(filename)).getImage();
		this.height = (int) dim.getHeight();
		this.width = (int) dim.getWidth();
	}
	// override of the paintComponent method of JPanel to paint the image resized. 
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, width, height, null);
	}
}