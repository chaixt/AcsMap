package cn.ct.map.bean;

import java.awt.Graphics2D;

public class GFRectangle extends GPos implements IRectangle<Integer> {
	private int width;
	private int height;

	public GFRectangle() {
		super();
	}

	public GFRectangle(GFRectangle rectangle) {
		super(rectangle);
		if (rectangle != null) {
			width = rectangle.width;
			height = rectangle.height;
		}
	}

	public GFRectangle(GPos pos, int width, int height) {
		super(pos);
		this.width = width;
		this.height = height;
	}

	public GFRectangle(int x, int y, int width, int height) {
		super(x, y);
		this.width = width;
		this.height = height;
	}

	public GFRectangle(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}

	@Override
	public void setWidth(Integer width) {
		this.width = width;
	}

	@Override
	public void setHeight(Integer height) {
		this.height = height;
	}

	@Override
	public Integer getWidth() {
		return width;
	}

	@Override
	public Integer getHeight() {
		return height;
	}

	@Override
	public Graphics2D draw(Graphics2D g2d) {
		if(g2d != null){
			g2d.fillRect(getX(), getY(), width, height);
		}
		return g2d;
	}
}
