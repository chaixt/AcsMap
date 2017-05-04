package cn.ct.map.bean;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface ICalculatePos<T extends Number> extends IPos<T> {
	public Rectangle2D calculate(Graphics2D g2d, int width, int height);
	public void setX(double x);
	public void setY(double y);
}
