package cn.ct.map.bean.graphics;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import cn.ct.map.bean.ICalculatePos;
import cn.ct.map.bean.IGDraw;
import cn.ct.map.bean.TextStyle;

public class GString extends GPos implements IGDraw, ICalculatePos<Integer> {
	private TextStyle style;
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

	public GString(String string, TextStyle style) {
		super();
		this.style = style;
		this.string = string;
	}

	public GString(GPos pos, String string, TextStyle style) {
		super(pos);
		this.string = string;
		this.style = style;
	}
	public GString(int x, int y, String string, TextStyle style) {
		super(x, y);
		this.string = string;
		this.style = style;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
	
	public TextStyle getStyle() {
		return style;
	}

	public void setStyle(TextStyle style) {
		this.style = style;
	}

	public Rectangle2D calculate(Graphics2D g2d, int width, int height){
		if(g2d != null){
			if(this.style !=null){
				this.style.setGraphics2D(g2d);
			}
			return g2d.getFontMetrics().getStringBounds(this.string, g2d);
		}
		return null;
	}

	@Override
	public Graphics2D draw(Graphics2D g2d, int width, int height) {
		if(g2d != null && string != null){
			if(this.style !=null){
				this.style.setGraphics2D(g2d);
			}
			g2d.drawString(string, getX(), getY());
		}
		return g2d;
	}

	@Override
	public void setX(double x) {
		this.setX(new Integer((int)x));
	}

	@Override
	public void setY(double y) {
		this.setY(new Integer((int)y));
	}
}
