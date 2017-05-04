package cn.ct.map.bean;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public interface IRectangle<T extends Number> extends IPos<T>, ICalculatePos<T>{
	public void setWidth(T width);
	public void setHeight(T height);
	public T getWidth();
	public T getHeight();

	@Override
	public default Rectangle2D calculate(Graphics2D g2d, int width, int height) {
		Number x = getX();
		Number y = getY();
		Number w = getWidth();
		Number h = getHeight();
		if(x != null && y != null & w != null && h != null){
			return new Rectangle(getX().intValue(), getY().intValue(), getWidth().intValue(), getHeight().intValue());
		}
		return null;
	}
}
