package cn.ct.map.bean.graphics;

import java.awt.Graphics2D;

import cn.ct.map.bean.IGDraw;

public class GString extends GPos implements IGDraw {
	private String string;
	
	public GString() {
		super();
	}

	public GString(String string) {
		super();
		this.string = string;
	}

	public GString(GPos pos, String string) {
		super(pos);
		this.string = string;
	}
	public GString(int x, int y, String string) {
		super(x, y);
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public Graphics2D draw(Graphics2D g2d, int width, int height) {
		if(g2d != null && string != null){
			g2d.drawString(string, getX(), getY());
		}
		return g2d;
	}

}
