package cn.ct.map.bean.graphics;

import java.awt.Graphics2D;

import cn.ct.map.bean.IGDraw;
import cn.ct.map.bean.IPos;

public class GPos implements IPos<Integer>, IGDraw {
	private int x;
	private int y;

	public GPos() {
		super();
	}
	public GPos(GPos pos) {
		super();
		if(pos != null){
			this.x = pos.x;
			this.y = pos.y;
		}
	}

	public GPos(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	@Override
	public void setX(Integer x) {
		this.x = x;
	}

	@Override
	public void setY(Integer y) {
		this.y = y;
	}

	@Override
	public Integer getX() {
		return x;
	}

	@Override
	public Integer getY() {
		return y;
	}
	@Override
	public Graphics2D draw(Graphics2D g2d, int width, int height) {
		if(g2d != null){
			g2d.drawRect(x, y, 1, 1);
		}
		return g2d;
	}
}
