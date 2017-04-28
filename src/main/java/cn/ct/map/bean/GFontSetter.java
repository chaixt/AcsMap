package cn.ct.map.bean;

import java.awt.Font;
import java.awt.Graphics2D;

public class GFontSetter implements IGDraw {
	private Font font = new Font("宋体", Font.PLAIN, 16);
	private IGDraw drawObj;
	
	public GFontSetter(IGDraw drawObj) {
		super();
		this.drawObj = drawObj;
	}

	public GFontSetter(Font font, IGDraw drawObj) {
		super();
		this.font = font;
		this.drawObj = drawObj;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		if(font != null){
			this.font = font;
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
		if(g2d != null && font != null){
			g2d.setFont(font);
		}
		if(drawObj != null){
			drawObj.draw(g2d);
		}
		return g2d;
	}

}
