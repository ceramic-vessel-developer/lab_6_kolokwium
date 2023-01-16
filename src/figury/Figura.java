/**
 * 
 */
package figury;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.Random;

/**
 * @author tb
 *
 */
public abstract class Figura implements Runnable, ActionListener/*, Shape*/ {

	// wspolny bufor
	protected Graphics2D buffer;
	protected Area area;
	// do wykreslania
	protected Shape shape;
	// przeksztalcenie obiektu
	protected AffineTransform aft;

	// przesuniecie
	private int dx, dy;
	private int delay;
	private int width;
	private int height;
	private Color clr;

	protected static final Random rand = new Random();

	public Figura(Graphics2D buf, int del, int w, int h) {
		delay = del;
		buffer = buf;
		width = w;
		height = h;

		dx = AnimPanel.gx;
		dy = AnimPanel.gy;


		clr = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));

	}

	@Override
	public void run() {
		while (true) {
			// przygotowanie nastepnego kadru
			shape = nextFrame();
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
		}
	}

	protected Shape nextFrame() {
		area = new Area(area);
		aft = new AffineTransform();
		Rectangle bounds = area.getBounds();
		int cx = bounds.x + bounds.width;
		int cy = bounds.y + bounds.height;
		// update grawitacji
		dx = AnimPanel.gx;
		dy = AnimPanel.gy;
		// zatrzymanie
		if (cx+dx -bounds.width< 0 || cx+dx > width)
			dx = 0;
		if (cy+dy - bounds.height< 0 || cy+dy > height)
			dy = 0;

		// konstrukcja przeksztalcenia
		aft.translate(cx, cy);
		aft.translate(-cx, -cy);
		aft.translate(dx, dy);
		// przeksztalcenie obiektu
		area.transform(aft);
		return area;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// wypelnienie obiektu
		buffer.setColor(clr.brighter());
		buffer.fill(shape);
		// wykreslenie ramki
		buffer.setColor(clr.darker());
		buffer.draw(shape);
	}

}
