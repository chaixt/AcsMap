package cn.ct.map.bean;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TextStyle {
	private Font font;
	private Color bcolor;
	private Color color;

	public TextStyle() {
		super();
	}

	public TextStyle(Font font) {
		super();
		this.font = font;
	}

	public TextStyle(Color color) {
		super();
		this.color = color;
	}

	public TextStyle(Font font, Color color) {
		super();
		this.font = font;
		this.color = color;
	}

	public TextStyle(Font font, Color bcolor, Color color) {
		super();
		this.font = font;
		this.bcolor = bcolor;
		this.color = color;
	}

	public Font getFont() {
		return font;
	}

	public TextStyle setFont(Font font) {
		this.font = font;
		return this;
	}

	public Color getBcolor() {
		return bcolor;
	}

	public TextStyle setBcolor(Color bcolor) {
		this.bcolor = bcolor;
		return this;
	}

	public Color getColor() {
		return color;
	}

	public TextStyle setColor(Color color) {
		this.color = color;
		return this;
	}

	public void setGraphics2D(Graphics2D g2d) {
		if (g2d != null) {
			if (font != null) {
				g2d.setFont(font);
			}
			if (color != null) {
				g2d.setColor(color);
			}
			if (bcolor != null) {
				g2d.setBackground(bcolor);
			}
		}
	}
}
