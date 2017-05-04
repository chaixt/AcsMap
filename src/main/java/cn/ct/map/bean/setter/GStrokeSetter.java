package cn.ct.map.bean.setter;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

import cn.ct.map.bean.IGDraw;

public class GStrokeSetter implements IGDraw {
	private Stroke stroke = new BasicStroke();
	private IGDraw drawObj;
	
	public GStrokeSetter(IGDraw drawObj) {
		super();
		this.drawObj = drawObj;
	}

	public GStrokeSetter(Stroke stroke, IGDraw drawObj) {
		super();
		this.stroke = stroke;
		this.drawObj = drawObj;
	}

	public Stroke getStroke() {
		return stroke;
	}

	public void setStroke(Stroke stroke) {
		if(stroke != null){
			this.stroke = stroke;
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
		if(g2d != null && stroke != null){
			g2d.setStroke(stroke);
		}
		if(drawObj != null){
			drawObj.draw(g2d, width, height);
		}
		return g2d;
	}

}