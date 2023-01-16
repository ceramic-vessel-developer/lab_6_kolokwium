package figury;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Figura{
    public Rectangle(Graphics2D buf, int del, int w, int h, int x, int y, int height, int width) {
        super(buf, del, w, h);
        shape = new Rectangle2D.Double(x, y, width,height);
        aft = new AffineTransform();
        area = new Area(shape);
    }
}
