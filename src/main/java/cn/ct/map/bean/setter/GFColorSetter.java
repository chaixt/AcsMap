package cn.ct.map.bean.setter;

import java.awt.Color;
import java.awt.Graphics2D;

import cn.ct.map.bean.IGDraw;

public class GFColorSetter implements IGDraw {
	private Color color = Color.WHITE;
	private IGDraw drawObj;
	
	public GFColorSetter(IGDraw drawObj) {
		super();
		this.drawObj = drawObj;
	}

	public GFColorSetter(Color color, IGDraw drawObj) {
		super();
		this.color = color;
		this.drawObj = drawObj;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		if(color != null){
			this.color = color;
		}
	}

	public IGDraw getDrawObj() {
		return drawObj;
	}

	public void setDrawObj(IGDraw drawObj) {
		this.drawObj = drawObj;
	}

	@Override
	public Graphics2D draw(Graphics2D g2d, int width, int height) {
		if(g2d != null && color != null){
			g2d.setColor(color);
		}
		if(drawObj != null){
			drawObj.draw(g2d, width, height);
		}
		return g2d;
	}

}
