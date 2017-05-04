package cn.ct.map.bean;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public interface IGDraw {
	public Graphics2D draw(Graphics2D g2d, int width, int height);
	
	public default void drawStringLU(Graphics2D g2d, String str, int x, int y){
		Rectangle2D sr = g2d.getFontMetrics().getStringBounds(str, g2d);
		g2d.drawString(str, x, (float)(y + sr.getHeight()));
	}
	
	public default void drawStringLC(Graphics2D g2d, String str, int x, int y){
		Rectangle2D sr = g2d.getFontMetrics().getStringBounds(str, g2d);
		g2d.drawString(str, x, (float)(y + sr.getHeight() / 2));
	}
}
