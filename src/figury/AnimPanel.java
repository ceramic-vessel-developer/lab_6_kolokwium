package figury;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] corner_temp_1;
	private int[] corner_temp_2;

	static int gx = 0;
	static int gy = 1;

	// bufor
	Image image;
	// wykreslacz ekranowy
	Graphics2D device;
	// wykreslacz bufora
	Graphics2D buffer;

	private int delay = 70;

	private Timer timer;

	private static int numer = 0;

	public AnimPanel() {
		super();
		setBackground(Color.WHITE);
		timer = new Timer(delay, this);
	}

	public void initialize() {
		int width = getWidth();
		int height = getHeight();

		image = createImage(width, height);
		buffer = (Graphics2D) image.getGraphics();
		buffer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		device = (Graphics2D) getGraphics();
		device.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	public void addFigure(){
		Figura fig =new Rectangle(buffer, delay, getWidth(), getHeight(), corner_temp_1[0], corner_temp_1[1],Math.abs(corner_temp_1[1]-corner_temp_2[1]),Math.abs(corner_temp_1[0]-corner_temp_2[0]));
		timer.addActionListener(fig);
		new Thread(fig).start();
	}

	void animate() {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		device.drawImage(image, 0, 0, null);
		buffer.clearRect(0, 0, getWidth(), getHeight());
	}

	public int[] getCorner_temp_1() {
		return corner_temp_1;
	}

	public void setCorner_temp_1(int[] corner_temp_1) {
		this.corner_temp_1 = corner_temp_1;
	}

	public void setCorner_temp_2(int[] corner_temp_2) {
		this.corner_temp_2 = corner_temp_2;
	}

	public void set_gravity(Gravity g){
		if(g == Gravity.UP){
			gx = 0;
			gy = -2;
		}else if (g == Gravity.DOWN){
			gx = 0;
			gy = 2;

		}else if (g == Gravity.RIGHT){
			gx = 2;
			gy = 0;

		}else if (g == Gravity.LEFT){
			gx = -2;
			gy = 0;
		}
	}
}
