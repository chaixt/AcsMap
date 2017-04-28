package cn.ct.map.bean;

import java.awt.Color;
import java.awt.Graphics2D;

public class GBColorSetter implements IGDraw {
	private Color color = Color.WHITE;
	private IGDraw drawObj;
	
	public GBColorSetter(IGDraw drawObj) {
		super();
		this.drawObj = drawObj;
	}

	public GBColorSetter(Color color, IGDraw drawObj) {
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
	public Graphics2D draw(Graphics2D g2d) {
		if(g2d != null && color != null){
			g2d.setBackground(color);
		}
		if(drawObj != null){
			drawObj.draw(g2d);
		}
		return g2d;
	}

}
