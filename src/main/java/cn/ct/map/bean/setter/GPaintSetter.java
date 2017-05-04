package cn.ct.map.bean.setter;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;

import cn.ct.map.bean.IGDraw;

public class GPaintSetter implements IGDraw {
	private Paint paint = Color.BLACK;
	private IGDraw drawObj;
	
	public GPaintSetter(IGDraw drawObj) {
		super();
		this.drawObj = drawObj;
	}

	public GPaintSetter(Paint paint, IGDraw drawObj) {
		super();
		this.paint = paint;
		this.drawObj = drawObj;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		if(paint != null){
			this.paint = paint;
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
		if(g2d != null && paint != null){
			g2d.setPaint(paint);
		}
		if(drawObj != null){
			drawObj.draw(g2d, width, height);
		}
		return g2d;
	}

}