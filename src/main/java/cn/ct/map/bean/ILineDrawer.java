package cn.ct.map.bean;

import java.awt.Graphics2D;

public interface ILineDrawer {
	public void draw(Graphics2D g2d, int sx, int sy, int ex, int ey, int idx);
}
